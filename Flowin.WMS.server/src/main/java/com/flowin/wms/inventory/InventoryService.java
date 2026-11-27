package com.flowin.wms.inventory;

import com.flowin.wms.area.AreaService;
import com.flowin.wms.bin.BinDto;
import com.flowin.wms.bin.BinService;
import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.storageplace.StoragePlaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryService {
    private final BalanceRepository balanceRepository;
    private final LocationBalanceRepository locationBalanceRepository;
    private final BinService binService;
    private final AreaService areaService;
    private final StoragePlaceService storagePlaceService;

    private final InventoryMapper mapper;

    public InventoryService(BalanceRepository balRepo, LocationBalanceRepository lbRepo,
                            InventoryMapper iMap, BinService binS, StoragePlaceService sps,
                            AreaService as)
    {
        balanceRepository = balRepo;
        locationBalanceRepository = lbRepo;
        mapper = iMap;
        binService = binS;
        storagePlaceService = sps;
        areaService = as;
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

    public List<InventoryDto.LocationInventoryResponse> getBinBalance(Integer binId)
    {
        if (binId == null || binId < 1)
            throw new InvalidArgumentException(
                    "ERROR: binId was null or less than 1.",
                    "ARGUMENT: binId",
                    "PLACED: InventoryService::getBinBalance"
            );

        binService.isBinExists(binId);

        return locationBalanceRepository.getLocationInventory(binId).stream().map(mapper::locationBalanceToInventoryResponse).toList();
//        return locationBalanceRepository.getBinInventory(binId);
    }

    public List<InventoryDto.LocationInventoryResponse> getAreaBalance(Integer areaId)
    {
        if (areaId == null || areaId < 1)
            throw new InvalidArgumentException(
                    "ERROR: areaId was null or less than 1.",
                    "ARGUMENT: areaId",
                    "PLACED: InventoryService::getAreaBalance"
            );


        areaService.isAreaExists(areaId);

        return locationBalanceRepository.getLocationInventory(areaId).stream().map(mapper::locationBalanceToInventoryResponse).toList();
//        return locationBalanceRepository.getBinInventory(binId);
    }
}
