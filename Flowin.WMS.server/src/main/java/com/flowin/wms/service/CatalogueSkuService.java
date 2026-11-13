package com.flowin.wms.service;

import com.flowin.wms.dto.CatalogueSkuRequest;
import com.flowin.wms.dto.CatalogueSkuResponse;
import com.flowin.wms.entity.CatalogueSku;
import com.flowin.wms.entity.SkuCategory;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.mapper.CatalogueSkuMapper;
import com.flowin.wms.repository.CatalogueSkuRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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

    public Optional<CatalogueSku> getSkuById(Integer catalogueId)
    {
        if (catalogueId == null || catalogueId < 0)
            throw new InvalidArgumentException(
                    "Value was null or less than zero.",
                    "catalogueId",
                    "placed -> CatalogueSkuService::getSkuById(catalogueId).");

        return repository.findById(catalogueId);
    }

    public List<CatalogueSku> getAllSkus()
    {
        return repository.findAll();
    }

    public Optional<CatalogueSkuResponse> getSkuByIdDto(Integer catalogueId)
    {
        if (catalogueId == null || catalogueId < 0)
            throw new InvalidArgumentException(
                    "Value was null or less than zero.",
                    "catalogueId",
                    "placed -> CatalogueSkuService::getSkuByIdDto(catalogueId).");

        return getSkuById(catalogueId).map(catalogueSkuMapper::toCatalogueSkuResponse);
    }

    public List<CatalogueSkuResponse> getAllSkusDto()
    {
        return getAllSkus().stream()
                .map(catalogueSkuMapper::toCatalogueSkuResponse).toList();
    }

    public Optional<CatalogueSkuResponse> postSkuToSkus(@Valid @RequestBody CatalogueSkuRequest catalogueSkuRequest) throws InvalidArgumentException {
        if (catalogueSkuRequest == null)
            throw new InvalidArgumentException(
                    "InvalidArgumentException. Value was null.",
                    "catalogueSkuRequest",
                    "placed -> CatalogueSkuService::postSkuToSkus(CatalogueSkuRequest).");

        Optional<SkuCategory> skuCategory = skuCategoryService.getCategoryByName(catalogueSkuRequest.skuCategoryName());

        if (skuCategory.isEmpty())
            return Optional.empty();

        CatalogueSku catalogueSku = new CatalogueSku(
                catalogueSkuRequest.skuName(), skuCategory.get(),
                catalogueSkuRequest.volume(), catalogueSkuRequest.weight(),
                catalogueSkuRequest.height(), catalogueSkuRequest.width(),
                catalogueSkuRequest.length()
        );

        return Optional.of(repository.save(catalogueSku)).map(catalogueSkuMapper::toCatalogueSkuResponse);
    }
}
