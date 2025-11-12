package uz.pdp.warehouse.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.warehouse.WarehouseCreateDto;
import uz.pdp.warehouse.model.dto.warehouse.WarehouseDto;
import uz.pdp.warehouse.model.dto.warehouse.WarehouseUpdateDto;
import uz.pdp.warehouse.model.entity.Warehouse;

@Component
public class WarehouseMapper {
    public Warehouse fromCreateDto(WarehouseCreateDto dto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());
        warehouse.setDescription(dto.getDescription());
        warehouse.setCapacity(dto.getCapacity());
        warehouse.setCurrentStock(dto.getCurrentStock());
        return warehouse;
    }

    public Warehouse fromUpdateDto(WarehouseUpdateDto dto, Warehouse warehouse) {
        warehouse.setName(dto.getName());
        warehouse.setDescription(dto.getDescription());
        warehouse.setCapacity(dto.getCapacity());
        warehouse.setCurrentStock(dto.getCurrentStock());
        return warehouse;
    }

    public WarehouseDto toDto(Warehouse warehouse) {
        WarehouseDto dto = new WarehouseDto();
        dto.setId(warehouse.getId());
        dto.setName(warehouse.getName());
        dto.setLocation(warehouse.getLocation());
        dto.setDescription(warehouse.getDescription());
        dto.setCapacity(warehouse.getCapacity());
        dto.setCurrentStock(warehouse.getCurrentStock());
        return dto;
    }

}
