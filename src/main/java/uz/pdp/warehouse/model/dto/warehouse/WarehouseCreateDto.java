package uz.pdp.warehouse.model.dto.warehouse;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WarehouseCreateDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "location cannot be blank")
    private String location;

    private String description;
    private Integer capacity;
    private Integer currentStock;
}
