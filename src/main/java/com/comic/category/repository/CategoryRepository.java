package com.comic.category.repository;

import com.comic.category.entity.Category;
import com.comic.core.repository.BaseRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryRepository extends BaseRepository<Category, Long> {
}
