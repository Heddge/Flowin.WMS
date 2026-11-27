package com.flowin.wms.inventory;

import com.flowin.wms.area.AreaService;
import com.flowin.wms.bin.Bin;
import com.flowin.wms.bin.BinDto;
import com.flowin.wms.bin.BinService;
import com.flowin.wms.cataloguesku.CatalogueSku;
import com.flowin.wms.cataloguesku.CatalogueSkuService;
import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.exception.NeededEntityDisabledException;
import com.flowin.wms.exception.NotEnoughSpaceException;
import com.flowin.wms.location.Location;
import com.flowin.wms.location.LocationService;
import com.flowin.wms.storageplace.StoragePlaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;
import java.util.List;

@Service
@Transactional
public class InventoryService {
    private final BalanceRepository balanceRepository;
    private final LocationBalanceRepository locationBalanceRepository;
    private final BinService binService;
    private final AreaService areaService;
    private final LocationService locationService;
    private final CatalogueSkuService catalogueSkuService;

    private final StoragePlaceService storagePlaceService;

    private final InventoryMapper mapper;

    public InventoryService(BalanceRepository balRepo, LocationBalanceRepository lbRepo,
                            InventoryMapper iMap, BinService binS, StoragePlaceService sps,
                            AreaService as, LocationService ls, CatalogueSkuService css)
    {
        balanceRepository = balRepo;
        locationBalanceRepository = lbRepo;
        mapper = iMap;
        binService = binS;
        storagePlaceService = sps;
        areaService = as;
        locationService = ls;
        catalogueSkuService = css;
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

    @Transactional
    public InventoryDto.LocationInventoryResponse addSkuToBin(Integer binId, InventoryDto.InventoryRequest request)
    {
//        System.console().printf("HELLO");
        if (binId == null || binId < 1)
            throw new InvalidArgumentException(
                    "ERROR: binId was null or less than 1.",
                    "ARGUMENT: binId",
                    "PLACED: InventoryService::addSkuToBin"
            );

        if (request == null)
            throw new InvalidArgumentException(
                    "ERROR: Request was null.",
                    "ARGUMENT: Request",
                    "PLACED: InventoryService::addSkuToBin"
            );

        Bin bin = binService.getBinByIdEntity(binId);

        if (!bin.getStoragePlace().getIsActive())
            throw new NeededEntityDisabledException(
                    String.format("ERROR: Bin`s storage place with id %d is deactivated", request.locationId()),
                    String.format("ENTITY: Storage place with id %d", request.locationId()),
                    "PLACED: InventoryService::addSkuToBin"
            );

        // TODO BEFORE ADDING SKUS TO BIN HAVE TO CHECK IF THERE AREN'T ANY TASK
        // ADDING SKUS TO THAT BIN

        if (bin.getMaxVolume() - bin.getOccupiedVolume() < request.volume())
            throw new NotEnoughSpaceException(
                    String.format("ERROR: Not enough free available volume in bin with id %d", binId),
                    String.format("NOTENOUGHIN: Bin with id %d", binId),
                    "PLACED: InventoryService::addSkuToBin"
            );

        if (bin.getMaxCount() - bin.getOccupiedCount() < request.count())
            throw new NotEnoughSpaceException(
                    String.format("ERROR: Not enough free available count in bin with id %d", binId),
                    String.format("NOTENOUGHIN: Bin with id %d", binId),
                    "PLACED: InventoryService::addSkuToBin"
            );

        if (bin.getMaxWeight() - bin.getOccupiedWeight() < request.weight())
            throw new NotEnoughSpaceException(
                    String.format("ERROR: Not enough free available weight in bin with id %d", binId),
                    String.format("NOTENOUGHIN: Bin with id %d", binId),
                    "PLACED: InventoryService::addSkuToBin"
            );

        if (balanceRepository.existsById(request.skuId()))
        {
            Balance itemBalance = balanceRepository.getBySkuId(request.skuId());
            itemBalance.increaseCount(request.count());
        }
        else
        {
            CatalogueSku sku = catalogueSkuService.getSkuById(request.skuId());
            Balance itemBalance = new Balance(sku, 0, request.count());
            balanceRepository.save(itemBalance);
        }

        if (locationBalanceRepository.existsByLocationBalanceId(new LocationBalanceId(binId, request.skuId())))
        {
            LocationBalance locationBalance = locationBalanceRepository.getByLocationId(binId);
            locationBalance.increaseCount(request.count());
        }
        else
        {
            Location loc = locationService.getLocationByIdEntity(binId);
            CatalogueSku sku = catalogueSkuService.getSkuById(request.skuId());
            LocationBalance locationBalance = new LocationBalance(
                    new LocationBalanceId(binId, request.skuId()),
                    loc, sku, 0, request.count());

            locationBalanceRepository.save(locationBalance);
        }


        bin.increaseOccupiedCount(request.count());
        bin.increaseOccupiedVolume(request.volume());
        bin.increaseOccupiedWeight(request.weight());


        // TODO ADD INTO EVERY HISTORY TABLE THIS UPDATE
        return mapper.locationBalanceToInventoryResponse(locationBalanceRepository.getByLocationBalanceId(
                new LocationBalanceId(binId, request.skuId())));
    }
}
