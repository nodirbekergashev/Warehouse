package uz.pdp.warehouse.model.dto.inventory;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryUpdateDto {
    private Long warehouseId;
    private Integer quantityInStock;
}
