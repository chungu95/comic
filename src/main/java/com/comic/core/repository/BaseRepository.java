package com.comic.core.repository;

import com.comic.core.query.Pageable;
import com.comic.core.query.PagingQuery;
import com.comic.core.query.panache.PanachePageQueryBuilder;
import com.comic.core.query.panache.PanachePagedQuery;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class BaseRepository<E, I> implements PanacheRepositoryBase<E, I> {

    private PanachePageQueryBuilder queryBuilder;

    public E save(E entity) {
        if (isPersistent(entity)) {
            return getEntityManager().merge(entity);
        }
        persist(entity);
        return entity;
    }

    public <V> boolean exists(String fieldName, V value) {
        return count(fieldName, value) > 0;
    }

    public Pageable<E> search(PagingQuery pagingQuery) {
        PanacheQuery<E> panacheQuery = query(pagingQuery);
        long count = panacheQuery.count();
        List<E> items = panacheQuery.list();
        return Pageable.<E>builder().total(count).items(items).build();
    }

    public <T> Pageable<T> search(PagingQuery pagingQuery, Function<E, T> mapper) {
        PanacheQuery<E> panacheQuery = query(pagingQuery);
        long count = panacheQuery.count();
        List<T> items = panacheQuery.list().stream().map(mapper).collect(Collectors.toList());
        return Pageable.<T>builder().total(count).items(items).build();
    }

    public <V> Set<E> findIn(String fieldName, Set<V> values) {
        Parameters parameters = Parameters.with(fieldName, values);
        return find(String.format("%s in :%s", fieldName, fieldName), parameters)
                .stream()
                .collect(Collectors.toSet());
    }

    protected PanacheQuery<E> query(PagingQuery pagingQuery) {
        PanachePagedQuery panachePagedQuery = createPagedQuery(pagingQuery);
        PanacheQuery<E> panacheQuery;
        if (panachePagedQuery.getSort() != null) {
            panacheQuery = find(panachePagedQuery.getQuery(),
                    panachePagedQuery.getSort(),
                    panachePagedQuery.getParams());
        } else {
            panacheQuery = find(panachePagedQuery.getQuery(), panachePagedQuery.getParams());
        }
        if (panachePagedQuery.getPage() != null) {
            return panacheQuery.page(panachePagedQuery.getPage());
        }
        return panacheQuery;
    }

    private PanachePagedQuery createPagedQuery(PagingQuery pagingQuery) {
        return getQueryBuilder().build(pagingQuery);
    }

    private PanachePageQueryBuilder getQueryBuilder() {
        if (queryBuilder == null) {
            Type genericSuperClass = getClass().getGenericSuperclass();

            ParameterizedType parametrizedType = null;
            while (parametrizedType == null) {
                if ((genericSuperClass instanceof ParameterizedType)) {
                    parametrizedType = (ParameterizedType) genericSuperClass;
                } else {
                    genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
                }
            }

            Class<?> entityClass = (Class<?>) parametrizedType.getActualTypeArguments()[0];
            return new PanachePageQueryBuilder(
                    getEntityManager().getMetamodel().managedType(entityClass));
        }
        return queryBuilder;
    }
}
