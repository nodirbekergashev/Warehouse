package uz.pdp.warehouse.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.inventory.InventoryCreateDto;
import uz.pdp.warehouse.model.dto.inventory.InventoryDto;
import uz.pdp.warehouse.model.dto.inventory.InventoryUpdateDto;
import uz.pdp.warehouse.model.entity.Inventory;
import uz.pdp.warehouse.repository.ProductRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;

@Component
@RequiredArgsConstructor
public class InventoryMapper {

    private final ProductRepository productRepo;
    private final WarehouseRepository warehouseRepo;

    public Inventory fromCreateDto(InventoryCreateDto dto) {
        Inventory inventory = new Inventory();
        inventory.setQuantityInStock(dto.getQuantityInStock());
        return inventory;
    }

    public Inventory fromUpdateDto(InventoryUpdateDto updateDto, Inventory inventory) {
        inventory.setQuantityInStock(updateDto.getQuantityInStock());
        return inventory;
    }

    public InventoryDto toDto(Inventory inventory) {
        InventoryDto dto = new InventoryDto();
        dto.setId(inventory.getId());
        dto.setProductId(inventory.getProduct().getId());
        dto.setWarehouseId(inventory.getWarehouse().getId());
        dto.setQuantityInStock(inventory.getQuantityInStock());
        return dto;
    }
}
