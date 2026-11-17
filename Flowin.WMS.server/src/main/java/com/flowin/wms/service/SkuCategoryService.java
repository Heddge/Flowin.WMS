package com.flowin.wms.service;

import com.flowin.wms.dto.SkuCategoryDto.*;
import com.flowin.wms.entity.SkuCategory;
import com.flowin.wms.exception.CategoryNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.exception.SavingFailException;
import com.flowin.wms.mapper.SkuCategoryMapper;
import com.flowin.wms.repository.SkuCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SkuCategoryService {
    private final SkuCategoryRepository repository;
    private final SkuCategoryMapper mapper;

    public SkuCategoryService(SkuCategoryRepository repo, SkuCategoryMapper _mapper)
    {
        this.repository = repo;
        this.mapper = _mapper;
    }

    @Transactional(readOnly = true)
    public SkuCategoryResponse getCategoryById(Integer categoryId)
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

        return mapper.toSkuCategoryResponse(result.get());
    }

    @Transactional(readOnly = true)
    public SkuCategoryResponse getCategoryByName(String name)
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

        return mapper.toSkuCategoryResponse(response);
    }

    @Transactional(readOnly = true)
    public List<SkuCategoryResponse> getAllCategories()
    {
        return repository.findAll().stream().map(mapper::toSkuCategoryResponse).toList();
    }

    @Transactional
    public SkuCategoryResponse postSkuCategory(@Valid @RequestBody SkuCategoryRequest skuCategoryRequest)
    {
        if (skuCategoryRequest == null)
            throw new InvalidArgumentException(
                    "Argument was null.",
                    "skuCategoryRequest",
                    "SkuCategoryService::postSkuCategory"
            );

        // TODO after adding roles add checking possibilities of user

        SkuCategory skuCategory = new SkuCategory(skuCategoryRequest.skuCategoryName());

        try {
            return mapper.toSkuCategoryResponse(repository.save(skuCategory));
        }
        catch (Exception ex)
        {
            throw new SavingFailException(
                    ex.getMessage(),
                    "skuCategoryRequest",
                    "SkuCategoryService::postSkuCategory");
        }
    }
}
