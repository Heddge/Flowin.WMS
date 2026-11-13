package com.flowin.wms.service;

import com.flowin.wms.entity.SkuCategory;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.repository.SkuCategoryRepository;
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
        if (categoryId == null || categoryId < 0)
            throw new InvalidArgumentException(
                    "Value was null or less than zero.",
                    "categoryId",
                    "placed -> SkuCategoryService::getCategoryById(categoryId).");

        return repository.findById(categoryId);
    }

    public Optional<SkuCategory> getCategoryByName(String name)
    {
        if (name == null || name.isBlank())
            throw new InvalidArgumentException(
                    "Value was null or empty.",
                    "name",
                    "placed -> SkuCategoryService::getCategoryByName(name).");

        return repository.findBySkuCategoryName(name);
    }

    public List<SkuCategory> getAllCategories()
    {
        return repository.findAll();
    }
}
