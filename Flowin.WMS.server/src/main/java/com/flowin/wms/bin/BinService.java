package com.flowin.wms.bin;

import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.exception.NeededEntityDisabledException;
import com.flowin.wms.exception.PresenceOfDependentEntitiesException;
import com.flowin.wms.shelf.ShelfService;
import com.flowin.wms.storageplace.StoragePlaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class BinService {
    private final BinRepository repository;
    private final BinMapper mapper;
    private final StoragePlaceService storagePlaceService;
    private final ShelfService shelfService;

    public BinService(BinRepository repo, BinMapper map, StoragePlaceService sps, ShelfService ss)
    {
        repository = repo;
        mapper = map;
        storagePlaceService = sps;
        shelfService = ss;
    }

    @Transactional(readOnly = true)
    public List<BinDto.BinResponse> getBins()
    {
        return repository.findAll().stream().map(mapper::toBinResponse).toList();
    }

    @Transactional(readOnly = true)
    public BinDto.BinResponse getBinById(Integer id)
    {
        return mapper.toBinResponse(getBinByIdEntity(id));
    }

    @Transactional(readOnly = true)
    public Boolean isBinExists(Integer id)
    {
        if (id == null || id < 1)
            throw new InvalidArgumentException(
                    "ERROR: Argument id was null or less than 1",
                    "ARGUMENT: id.",
                    "PLACED: BinService::isBinExists"
            );

        if (!repository.existsByBinId(id))
            throw new EntityNotFoundException(
                    String.format("ERROR: Bin with id %d not found", id),
                    String.format("ENTITY: Bin with id %d", id),
                    "PLACED: BinService::isBinExists"
            );

        return true;
    }

    @Transactional(readOnly = true)
    public Bin getBinByIdEntity(Integer id)
    {
        if (id == null || id < 1)
            throw new InvalidArgumentException(
                    "ERROR: Argument id was null or less than 1",
                    "ARGUMENT: id.",
                    "PLACED: BinService::getBinByIdEntity"
            );

        var response = repository.findById(id);

        if (response.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: Bin with id %d not found.", id),
                    String.format("ENTITY: Bin with id %d", id),
                    "PLACED: BinService::getBinByIdEntity"
            );

        return response.get();
    }

    @Transactional
    public BinDto.BinResponse postBin(BinDto.BinRequest request)
    {
        if (request == null)
            throw new InvalidArgumentException(
                    "ERROR: Argument request was null.",
                    "ARGUMENT: Request.",
                    "PLACED: BinService::postBin"
            );

        var storagePlace =storagePlaceService.getStoragePlaceByIdEntity(request.storagePlaceId());
        if (!storagePlace.getIsActive())
            throw new NeededEntityDisabledException(
                    "ERROR: Needed storage place is deactivated.",
                    String.format("ENTITY: Storage place with id %d", request.storagePlaceId()),
                    "PLACED: BinService::postBin"
            );
        if (storagePlaceService.getCountOfRelatedEntities(request.storagePlaceId()) != 0)
            throw new PresenceOfDependentEntitiesException(
                    "ERROR: Related entities for needed storage place already exist.",
                    String.format("ENTITY: StoragePlace with id %d", request.storagePlaceId()),
                    "PLACED: BinService::postBin"
            );

        var shelf = shelfService.getShelfByIdEntity(request.shelfId());
        if (!shelf.getActive())
            throw new NeededEntityDisabledException(
                    "ERROR: Needed shelf is deactivated.",
                    String.format("ENTITY: Shelf with id %d", request.shelfId()),
                    "PLACED: BinService::postBin"
            );

//        LocalDateTime.now(ZoneId.of("UTC") -> better to use Instant.now().
        Bin requesting = new Bin(storagePlace, shelf, Instant.now(),
                0, request.maxVolume(), 0, request.maxWeight(), 0, request.maxCount());

        return mapper.toBinResponse(repository.save(requesting));
    }
}
