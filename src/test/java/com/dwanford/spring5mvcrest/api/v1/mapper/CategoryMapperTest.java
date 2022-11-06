package com.dwanford.spring5mvcrest.api.v1.mapper;

import com.dwanford.spring5mvcrest.api.v1.model.CategoryDTO;
import com.dwanford.spring5mvcrest.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
    @Test
    void categoryToCategoryDTO() {
        //given
        Category category = new Category();
        category.setName("Foo");
        category.setId(1L);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(1L, categoryDTO.getId());
        assertEquals("Foo", categoryDTO.getName());
    }
}