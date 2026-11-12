package com.flowin.wms.service;

import com.flowin.wms.dto.CatalogueSkuResponse;
import com.flowin.wms.entity.CatalogueSku;
import com.flowin.wms.mapper.CatalogueSkuMapper;
import com.flowin.wms.repository.CatalogueSkuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CatalogueSkuService {
    private final CatalogueSkuRepository repository;
    private final CatalogueSkuMapper catalogueSkuMapper;

    public CatalogueSkuService(CatalogueSkuRepository repo, CatalogueSkuMapper catSkuMapper)
    {
        this.repository = repo;
        this.catalogueSkuMapper = catSkuMapper;
    }

    public Optional<CatalogueSku> getSkuById(Integer catalogueId)
    {
        // проверок catalogueId не нужно, в дальнейшем - обработка ошибок связанных с этим.
        return repository.findById(catalogueId);
    }

    public List<CatalogueSku> getAllSkus()
    {
        return repository.findAll();
    }

    public Optional<CatalogueSkuResponse> getSkuByIdDto(Integer catalogueId)
    {
        // проверок catalogueId не нужно, в дальнейшем - обработка ошибок связанных с этим.
        return getSkuById(catalogueId).map(catalogueSkuMapper::toCatalogueSkuResponse);
    }

    public List<CatalogueSkuResponse> getAllSkusDto()
    {
        return repository.findAll().stream()
                .map(catalogueSkuMapper::toCatalogueSkuResponse).toList();
    }
}
