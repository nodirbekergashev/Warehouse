package uz.pdp.warehouse.model.dto.inventory;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryDto {
    private Long id;
    private Long productId;
    private Long warehouseId;
    private Integer quantityInStock;
}
