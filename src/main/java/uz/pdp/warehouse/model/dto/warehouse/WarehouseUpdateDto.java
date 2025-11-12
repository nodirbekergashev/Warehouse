package uz.pdp.warehouse.model.dto.warehouse;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseUpdateDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String description;
    private Integer capacity;
    private Integer currentStock;
}
