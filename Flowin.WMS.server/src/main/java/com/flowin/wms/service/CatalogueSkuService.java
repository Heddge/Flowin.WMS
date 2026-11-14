package com.flowin.wms.service;

import com.flowin.wms.dto.CatalogueSkuRequest;
import com.flowin.wms.dto.CatalogueSkuResponse;
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
        if (catalogueId == null || catalogueId < 1)
            throw new InvalidArgumentException(
                    "Value was null or less than zero.",
                    "catalogueId",
                    "placed in CatalogueSkuService::getSkuByIdDto(catalogueId).");

        return catalogueSkuMapper.toCatalogueSkuResponse(getSkuById(catalogueId));

    }

    @Transactional(readOnly = true)
    public List<CatalogueSkuResponse> getAllSkusDto()
    {
        return getAllSkus().stream()
                .map(catalogueSkuMapper::toCatalogueSkuResponse).toList();
    }

    public CatalogueSkuResponse postSkuToSkus(CatalogueSkuRequest catalogueSkuRequest) {
        if (catalogueSkuRequest == null)
            throw new InvalidArgumentException(
                    "InvalidArgumentException. Value was null.",
                    "catalogueSkuRequest",
                    "placed -> CatalogueSkuService::postSkuToSkus(CatalogueSkuRequest).");

        SkuCategory skuCategory = skuCategoryService.getCategoryByName(catalogueSkuRequest.skuCategoryName());

        if (skuCategory == null)
            throw new CategoryNotFoundException(
                    String.format("There isn`t category with name %s", catalogueSkuRequest.skuCategoryName()),
                    catalogueSkuRequest.skuCategoryName(),
                    "CatalogueSkuService::postToSkus"
            );

        CatalogueSku catalogueSku = new CatalogueSku(
                catalogueSkuRequest.skuName(), skuCategory,
                catalogueSkuRequest.volume(), catalogueSkuRequest.weight(),
                catalogueSkuRequest.height(), catalogueSkuRequest.width(),
                catalogueSkuRequest.length());

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
}
