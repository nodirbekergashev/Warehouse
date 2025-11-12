package uz.pdp.warehouse.model.dto.inventory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryCreateDto {
    private Long productId;
    private Long warehouseId;
    private Integer quantityInStock;
}