package com.dwanford.spring5mvcrest.repositories;

import com.dwanford.spring5mvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
