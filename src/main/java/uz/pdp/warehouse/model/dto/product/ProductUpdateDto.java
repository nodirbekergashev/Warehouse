package uz.pdp.warehouse.model.dto.product;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.warehouse.model.enums.Unit;

import java.math.BigDecimal;
@Getter
@Setter
public class ProductUpdateDto {
    private String name;
    private Long categoryId;
    private Unit unit;
    private BigDecimal price;
    private Long SupplierId;
}
