package com.inssolutions.articles.mapper;

import com.inssolutions.articles.dto.CategoryDTO;
import com.inssolutions.articles.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper CategoryMapper = Mappers.getMapper(CategoryMapper.class);

    Category mapToCategory(CategoryDTO categoryDTO);

    CategoryDTO mapToCategoryDTO(Category category);
}
