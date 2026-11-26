package com.flowin.wms.inventory;

import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryService {
    private final BalanceRepository balanceRepository;
    private final LocationBalanceRepository locationBalanceRepository;

    private final InventoryMapper mapper;

    public InventoryService(BalanceRepository balRepo, LocationBalanceRepository lbRepo,
                            InventoryMapper iMap)
    {
        balanceRepository = balRepo;
        locationBalanceRepository = lbRepo;
        mapper = iMap;
    }

    public List<InventoryDto.InventoryResponse> getInventory()
    {
        return balanceRepository.findAll().stream().map(mapper::balanceToInventoryResponse).toList();
    }

    public Balance getInventoryOfSkuByIdEntity(Integer skuId)
    {
        if (skuId == null || skuId < 1)
            throw new InvalidArgumentException(
                    "ERROR: SkuId was null or less than 1.",
                    "ARGUMENT: SkuId",
                    "PLACED: InventoryService::getInventoryOfSkyByIdEntity"
            );

        var response = balanceRepository.findById(skuId);

        if (response.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: SkuBalance for sku with id %d not found", skuId),
                    String.format("ENTITY: Balance of sku with id %d", skuId),
                    "PLACED: InventoryService::getInventoryOfSkyByIdEntity"
            );

        return response.get();
    }

    public InventoryDto.InventoryResponse getInventoryOfSkuById(Integer skuId)
    {
        return mapper.balanceToInventoryResponse(getInventoryOfSkuByIdEntity(skuId));
    }
}
