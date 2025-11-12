package uz.pdp.warehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.warehouse.model.dto.warehouse.WarehouseCreateDto;
import uz.pdp.warehouse.model.dto.warehouse.WarehouseDto;
import uz.pdp.warehouse.model.dto.warehouse.WarehouseUpdateDto;
import uz.pdp.warehouse.model.entity.Warehouse;
import uz.pdp.warehouse.exceptions.exception.WarehouseNotFoundException;
import uz.pdp.warehouse.mapper.WarehouseMapper;
import uz.pdp.warehouse.repository.WarehouseRepository;
import uz.pdp.warehouse.service.abstractServices.AbstractService;
import uz.pdp.warehouse.service.abstractServices.CRUDService;
import uz.pdp.warehouse.validator.WarehouseValidator;

import java.util.List;

@Service
public class WarehouseService
        extends
        AbstractService<
                WarehouseMapper,
                WarehouseValidator,
                WarehouseRepository>
        implements CRUDService<Warehouse, WarehouseCreateDto, WarehouseUpdateDto, WarehouseDto> {

    protected WarehouseService(WarehouseMapper mapper, WarehouseValidator validator, WarehouseRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public WarehouseDto save(WarehouseCreateDto dto) {
        validator.validateOnCreate(dto);
        Warehouse warehouse = mapper.fromCreateDto(dto);
        repository.save(warehouse);
        return mapper.toDto(warehouse);
    }

    @Override
    public WarehouseDto update(WarehouseUpdateDto updateDto, Long id) {
        Warehouse warehouse = mapper.fromUpdateDto(updateDto, findById(id));
        repository.save(warehouse);
        return mapper.toDto(warehouse);
    }

    @Override
    public void delete(Long id) {
        Warehouse warehouse = findById(id);
        warehouse.softDelete();
        repository.save(warehouse);
    }

    @Override
    public Warehouse findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));
    }

    @Override
    public List<WarehouseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public WarehouseDto getDto(Warehouse warehouse) {
        return mapper
                .toDto(warehouse);
    }

}
