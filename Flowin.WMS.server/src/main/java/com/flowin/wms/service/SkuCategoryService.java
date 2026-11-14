package com.flowin.wms.service;

import com.flowin.wms.entity.SkuCategory;
import com.flowin.wms.exception.CategoryNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.repository.SkuCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SkuCategoryService {
    private final SkuCategoryRepository repository;

    public SkuCategoryService(SkuCategoryRepository repo)
    {
        this.repository = repo;
    }

    @Transactional(readOnly = true)
    public SkuCategory getCategoryById(Integer categoryId)
    {
        if (categoryId == null || categoryId < 1)
            throw new InvalidArgumentException(
                    "Value was null or less than zero.",
                    "categoryId",
                    "placed -> SkuCategoryService::getCategoryById(categoryId).");

        Optional<SkuCategory> result = repository.findById(categoryId);

        if (!result.isPresent())
            throw new CategoryNotFoundException(
                    String.format("There isn`t category with id %s", categoryId),
                    categoryId.toString(),
                    "SkuCategoryService::getCategoryById"
            );

        return result.get();
    }

    @Transactional(readOnly = true)
    public SkuCategory getCategoryByName(String name)
    {
        if (name == null || name.isBlank())
            throw new InvalidArgumentException(
                    "Value was null or empty.",
                    "name",
                    "placed -> SkuCategoryService::getCategoryByName(name).");


        SkuCategory response = repository.findBySkuCategoryName(name);

        if (response == null)
            throw new CategoryNotFoundException(
                    String.format("There isn`t category with name %s", name),
                    name,
                    "SkuCategoryService::getCategoryByName"
            );

        return response;
    }

    @Transactional(readOnly = true)
    public List<SkuCategory> getAllCategories()
    {
        return repository.findAll();
    }
}
