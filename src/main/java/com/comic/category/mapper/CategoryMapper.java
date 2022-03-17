package com.comic.category.mapper;

import com.comic.category.domain.CategoryCreateRQ;
import com.comic.category.domain.CategoryRS;
import com.comic.category.domain.CategoryUpdateRQ;
import com.comic.category.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryRS map(Category category);

    Category map(CategoryCreateRQ categoryCreateRQ);

    @Mapping(target = "id", ignore = true)
    void merge(@MappingTarget Category category, CategoryUpdateRQ categoryUpdateRQ);
}
