package com.flowin.wms.service;

import com.flowin.wms.entity.SkuCategory;
import com.flowin.wms.repository.SkuCategoryRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkuCategoryService {
    private final SkuCategoryRepository repository;

    public SkuCategoryService(SkuCategoryRepository repo)
    {
        this.repository = repo;
    }

    public Optional<SkuCategory> getCategoryById(Integer categoryId)
    {
        if (categoryId == null)
            return Optional.empty();

        return repository.findById(categoryId);
    }

    public Optional<SkuCategory> getCategoryByName(String name)
    {
        if (name == null || name.isBlank())
            return Optional.empty();
        return repository.findBySkuCategoryName(name);
    }

    public List<SkuCategory> getAllCategories()
    {
        return repository.findAll();
    }
}
