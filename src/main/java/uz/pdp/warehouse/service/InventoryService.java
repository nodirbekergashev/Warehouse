package uz.pdp.warehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.warehouse.exceptions.exception.InsufficientStockException;
import uz.pdp.warehouse.model.dto.inventory.InventoryCreateDto;
import uz.pdp.warehouse.model.dto.inventory.InventoryDto;
import uz.pdp.warehouse.model.dto.inventory.InventoryUpdateDto;
import uz.pdp.warehouse.model.entity.Inventory;
import uz.pdp.warehouse.model.entity.Product;
import uz.pdp.warehouse.model.entity.Warehouse;
import uz.pdp.warehouse.exceptions.exception.InventoryNotFoundException;
import uz.pdp.warehouse.exceptions.exception.ProductNotFoundException;
import uz.pdp.warehouse.exceptions.exception.WarehouseNotFoundException;
import uz.pdp.warehouse.mapper.InventoryMapper;
import uz.pdp.warehouse.repository.InventoryRepository;
import uz.pdp.warehouse.repository.ProductRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;
import uz.pdp.warehouse.service.abstractServices.AbstractService;
import uz.pdp.warehouse.service.abstractServices.CRUDService;
import uz.pdp.warehouse.validator.InventoryValidator;

import java.util.List;

@Service
public class InventoryService
        extends
        AbstractService<
                InventoryMapper,
                InventoryValidator,
                InventoryRepository
                >
        implements
        CRUDService<
                Inventory,
                InventoryCreateDto,
                InventoryUpdateDto,
                InventoryDto
                > {

    private final ProductRepository productRepo;
    private final WarehouseRepository warehouseRepo;

    protected InventoryService(InventoryMapper mapper, InventoryValidator validator, InventoryRepository repository, ProductRepository productRepo, WarehouseRepository warehouseRepo) {
        super(mapper, validator, repository);
        this.productRepo = productRepo;
        this.warehouseRepo = warehouseRepo;
    }


    @Override
    public InventoryDto save(InventoryCreateDto dto) {
        validator.validateOnCreate(dto);

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(dto.getProductId()));
        Warehouse warehouse = warehouseRepo.findById(dto.getWarehouseId())
                .orElseThrow(() -> new WarehouseNotFoundException(dto.getWarehouseId()));

        Inventory inventory = mapper.fromCreateDto(dto);
        inventory.setProduct(product);
        inventory.setWarehouse(warehouse);

        repository.save(inventory);
        return mapper.toDto(inventory);

    }

    @Override
    public InventoryDto update(InventoryUpdateDto dto, Long id) {
        Inventory inventory = findById(id);
        mapper.fromUpdateDto(dto, inventory);

        Warehouse warehouse = warehouseRepo.findById(dto.getWarehouseId())
                .orElseThrow(() -> new WarehouseNotFoundException(dto.getWarehouseId()));

        inventory.setWarehouse(warehouse);

        repository.save(inventory);
        return mapper.toDto(inventory);
    }

    @Override
    public void delete(Long id) {
        Inventory inventory = findById(id);
        inventory.softDelete();
        repository.save(inventory);
    }

    @Override
    public Inventory findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new InventoryNotFoundException(id));
    }

    @Override
    public List<InventoryDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public InventoryDto getDto(Inventory inventory) {
        return mapper.toDto(inventory);
    }
}
