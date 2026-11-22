package com.flowin.wms.service;

import com.flowin.wms.dto.CatalogueSkuDto.CatalogueSkuRequest;
import com.flowin.wms.dto.CatalogueSkuDto.CatalogueSkuResponse;
import com.flowin.wms.dto.SkuCategoryDto;
import com.flowin.wms.entity.CatalogueSku;
import com.flowin.wms.entity.SkuCategory;
import com.flowin.wms.exception.CategoryNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.exception.SavingFailException;
import com.flowin.wms.exception.SkuNotFoundException;
import com.flowin.wms.mapper.CatalogueSkuMapper;
import com.flowin.wms.repository.CatalogueSkuRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.cfg.MapperBuilder;

import java.util.List;

@Service
@Transactional
public class CatalogueSkuService {
    private final CatalogueSkuRepository repository;
    private final CatalogueSkuMapper catalogueSkuMapper;
    private final SkuCategoryService skuCategoryService;

    public CatalogueSkuService(CatalogueSkuRepository repo, CatalogueSkuMapper catSkuMapper,
                               SkuCategoryService _skuCategoryService)
    {
        this.repository = repo;
        this.catalogueSkuMapper = catSkuMapper;
        this.skuCategoryService = _skuCategoryService;
    }

    @Transactional(readOnly = true)
    public CatalogueSku getSkuById(Integer catalogueId)
    {
        if (catalogueId == null || catalogueId < 1)
            throw new InvalidArgumentException(
                    "Value was null or less than zero.",
                    "catalogueId",
                    "placed -> CatalogueSkuService::getSkuById(catalogueId).");

        var response = repository.findById(catalogueId);
        if (response.isEmpty())
            throw new SkuNotFoundException(
                    String.format("There isn`t sku with id %s", catalogueId),
                    catalogueId.toString(),
                    "CatalogueSkuService::getSkuById(catalogueId)"
            );
        return response.get();
    }

    @Transactional(readOnly = true)
    public List<CatalogueSku> getAllSkus()
    {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public CatalogueSkuResponse getSkuByIdDto(Integer catalogueId)
    {
        return catalogueSkuMapper.toCatalogueSkuResponse(getSkuById(catalogueId));
    }

    @Transactional(readOnly = true)
    public List<CatalogueSkuResponse> getAllSkusDto()
    {
        return getAllSkus().stream()
                .map(catalogueSkuMapper::toCatalogueSkuResponse).toList();
    }

    @Transactional
    public CatalogueSkuResponse postSkuToSkus(CatalogueSkuRequest catalogueSkuRequest) {
        if (catalogueSkuRequest == null)
            throw new InvalidArgumentException(
                    "InvalidArgumentException. Value was null.",
                    "catalogueSkuRequest",
                    "placed -> CatalogueSkuService::postSkuToSkus(CatalogueSkuRequest).");

        SkuCategoryDto.SkuCategoryResponse skuCategory = skuCategoryService.getCategoryByName(catalogueSkuRequest.skuCategoryName());

        if (skuCategory == null)
            throw new CategoryNotFoundException(
                    String.format("There isn`t category with name %s", catalogueSkuRequest.skuCategoryName()),
                    catalogueSkuRequest.skuCategoryName(),
                    "CatalogueSkuService::postToSkus"
            );

        CatalogueSku catalogueSku = new CatalogueSku(
                catalogueSkuRequest.skuName(), new SkuCategory(skuCategory.skuCategoryName()),
                catalogueSkuRequest.volume(), catalogueSkuRequest.weight(),
                catalogueSkuRequest.height(), catalogueSkuRequest.width(),
                catalogueSkuRequest.length(), catalogueSkuRequest.isActive());

        try {
            return catalogueSkuMapper.toCatalogueSkuResponse(repository.save(catalogueSku));
        }
        catch (IllegalArgumentException | OptimisticLockingFailureException ex) {
            throw new SavingFailException(
                ex.getMessage(),
                "catalogueSku",
                "CatalogueSkuService::postSkuToSkus");
        }
    }

    @Transactional
    public CatalogueSkuResponse updateSku(Integer id, CatalogueSkuRequest request)
    {
        if (id == null || id < 1 || request == null)
            throw new InvalidArgumentException(
                    "Value was null or less than zero.",
                    "Id or RequestBody",
                    "placed in CatalogueSkuService::updateSku(Id, request).");

        var response_sku = repository.findById(id);

        if (response_sku.isEmpty())
            throw new SkuNotFoundException(
                    String.format("There isn`t sku with id %d", id),
                    String.format("sku with id: %d", id),
                    "CatalogueSkuService::updateSku"
            );

        var response_category = skuCategoryService.getCategoryEntityByName(request.skuCategoryName());
        var response_sku_to_update = response_sku.get();

        response_sku_to_update.setSkuCategory(response_category);
        response_sku_to_update.setHeight(request.height());
        response_sku_to_update.setLength(request.length());
        response_sku_to_update.setSkuName(request.skuName());
        response_sku_to_update.setVolume(request.volume());
        response_sku_to_update.setWeight(request.weight());
        response_sku_to_update.setWidth(request.width());

        return catalogueSkuMapper.toCatalogueSkuResponse(response_sku_to_update);
    }

    @Transactional
    public CatalogueSkuResponse deactivateSku(Integer id)
    {
        if (id == null || id < 1)
            throw new InvalidArgumentException(
                    "Value was null or less than zero.",
                    "Id",
                    "placed in CatalogueSkuService::deactivateSku(Id).");

        var response = repository.findById(id);

        if (response.isEmpty())
            throw new SkuNotFoundException(
                    String.format("There isn`t sku with id %d", id),
                    String.format("sku with id: %d", id),
                    "CatalogueSkuService::deactivateSku"
            );

        response.get().deactivate();
        return catalogueSkuMapper.toCatalogueSkuResponse(response.get());
    }
}
