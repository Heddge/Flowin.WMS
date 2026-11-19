package com.flowin.wms.service;

import com.flowin.wms.dto.SkuCategoryDto.*;
import com.flowin.wms.entity.SkuCategory;
import com.flowin.wms.exception.*;
import com.flowin.wms.mapper.SkuCategoryMapper;
import com.flowin.wms.repository.SkuCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
    public SkuCategoryResponse postSkuCategory(SkuCategoryRequest skuCategoryRequest)
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

    @Transactional
    public SkuCategoryResponse putSkuCategoryByName(String oldName, SkuCategoryRequest skuCategoryRequest)
    {
        if (oldName == null || oldName.isBlank())
            throw new InvalidArgumentException(
                    "Value was null or empty.",
                    "oldName",
                    "SkuCategoryService::putSkuCategoryByName(oldName, requestBody).");

        if (skuCategoryRequest == null)
            throw new InvalidArgumentException(
                    "Argument was null.",
                    "skuCategoryRequest",
                    "SkuCategoryService::putSkuCategoryByName"
            );

        SkuCategoryResponse previousCategory = getCategoryByName(oldName);

        SkuCategory request = new SkuCategory(previousCategory.skuCategoryId(), skuCategoryRequest.skuCategoryName());


        try {
            return mapper.toSkuCategoryResponse(repository.save(request));
        }
        catch (Exception ex)
        {
            throw new SavingFailException(
                    ex.getMessage(),
                    "skuCategoryRequest",
                    "SkuCategoryService::putSkuCategoryByName");
        }
    }


    @Transactional
    public SkuCategoryResponse putSkuCategoryById(Integer id, @Valid @RequestBody SkuCategoryRequest skuCategoryRequest)
    {
        if (id == null || id < 1)
            throw new InvalidArgumentException(
                    "Value was less than 1.",
                    "id",
                    "placed: SkuCategoryService::putSkuCategoryById(id, requestBody).");

        if (skuCategoryRequest == null)
            throw new InvalidArgumentException(
                    "Argument was null.",
                    "skuCategoryRequest",
                    "SkuCategoryService::putSkuCategoryById"
            );

        SkuCategoryResponse previousCategory = getCategoryById(id);

        SkuCategory request = new SkuCategory(previousCategory.skuCategoryId(), skuCategoryRequest.skuCategoryName());

        try {
            return mapper.toSkuCategoryResponse(repository.save(request));
        }
        catch (Exception ex)
        {
            throw new SavingFailException(
                    ex.getMessage(),
                    "skuCategoryRequest",
                    "SkuCategoryService::putSkuCategoryById");
        }
    }

    public SkuCategoryResponse deleteSkuCategoryById(Integer id)
    {
        if (id == null || id < 1)
            throw new InvalidArgumentException(
                    "Value was less than 1.",
                    "id",
                    "placed: SkuCategoryService::deleteSkuCategoryById(id).");

        SkuCategoryResponse response = getCategoryById(id);
        try {
            repository.deleteById(id);
            return response;
        }
        catch (Exception ex)
        {
            throw new DeletingFailException(
                    ex.getMessage(),
                    String.format("with id %d", id),
                    "SkuCategoryService::deleteSkuCategoryById");
        }
    }

    public SkuCategoryResponse deleteSkuCategoryByName(String name)
    {
        if (name == null || name.isBlank())
            throw new InvalidArgumentException(
                    "Value was null or empty.",
                    "name",
                    "SkuCategoryService::deleteSkuCategoryByName(oldName).");

        SkuCategoryResponse response = getCategoryByName(name);

        try {
            repository.deleteById(response.skuCategoryId());
            return response;
        }
        catch (Exception ex) {
            throw new DeletingFailException(
                    ex.getMessage(),
                    String.format("with name %s", name),
                    "SkuCategoryService::deleteSkuCategoryById");
        }
    }
}
