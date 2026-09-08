package com.university.booking.service;

import com.university.booking.dto.CategoryRequest;
import com.university.booking.model.Category;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    public Category createCategory(CategoryRequest request) {
        return null;
    }

    public List<Category> getAllCategories() {
        return null;
    }

    public void deleteCategory(String id) {
    }
}
