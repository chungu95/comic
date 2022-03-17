package com.comic.category;

import com.comic.category.domain.CategoryCreateRQ;
import com.comic.category.domain.CategoryRS;
import com.comic.category.domain.CategoryUpdateRQ;
import com.comic.category.entity.Category;
import com.comic.category.exception.CategoryException;
import com.comic.category.mapper.CategoryMapper;
import com.comic.category.repository.CategoryRepository;
import com.comic.core.query.Pageable;
import com.comic.core.query.PagingQuery;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryRS create(CategoryCreateRQ categoryCreateRQ) {
        Category category = CategoryMapper.INSTANCE.map(categoryCreateRQ);
        return CategoryMapper.INSTANCE.map(categoryRepository.save(category));
    }

    @Transactional
    public CategoryRS update(Long id, CategoryUpdateRQ categoryUpdateRQ) {
        Category category = getById(id);
        CategoryMapper.INSTANCE.merge(category, categoryUpdateRQ);
        return CategoryMapper.INSTANCE.map(categoryRepository.save(category));
    }

    @Transactional
    public void delete(Long id) {
        Category category = getById(id);
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }

    public Pageable<CategoryRS> search(PagingQuery pagingQuery) {
        return categoryRepository.search(pagingQuery, CategoryMapper.INSTANCE::map);
    }

    private Category getById(Long id) {
        return categoryRepository.findByIdOptional(id).orElseThrow(CategoryException::categoryNotFound);
    }
}
