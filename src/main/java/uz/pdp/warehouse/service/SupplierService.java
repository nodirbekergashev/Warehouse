package uz.pdp.warehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.warehouse.model.dto.supplier.SupplierCreateDto;
import uz.pdp.warehouse.model.dto.supplier.SupplierDto;
import uz.pdp.warehouse.model.dto.supplier.SupplierUpdateDto;
import uz.pdp.warehouse.model.entity.Supplier;
import uz.pdp.warehouse.exceptions.exception.SupplierNotFoundException;
import uz.pdp.warehouse.mapper.SupplierMapper;
import uz.pdp.warehouse.repository.SupplierRepository;
import uz.pdp.warehouse.service.abstractServices.AbstractService;
import uz.pdp.warehouse.service.abstractServices.CRUDService;
import uz.pdp.warehouse.validator.SupplierValidator;

import java.util.List;

@Service
public class SupplierService
        extends
        AbstractService<
                SupplierMapper,
                SupplierValidator,
                SupplierRepository
                >
        implements
        CRUDService<
                Supplier,
                SupplierCreateDto,
                SupplierUpdateDto,
                SupplierDto
                > {

    protected SupplierService(SupplierMapper mapper, SupplierValidator validator, SupplierRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public SupplierDto save(SupplierCreateDto dto) {
        validator.validateOnCreate(dto);
        Supplier supplier = mapper.fromCreateDto(dto);
        repository.save(supplier);
        return mapper.toDto(supplier);
    }

    @Override
    public SupplierDto update(SupplierUpdateDto updateDto, Long id) {
        Supplier supplier = mapper.fromUpdateDto(updateDto, findById(id));
        repository.save(supplier);
        return mapper.toDto(supplier);
    }

    @Override
    public void delete(Long id) {
        Supplier supplier = findById(id);
        supplier.softDelete();
        repository.save(supplier);
    }

    @Override
    public Supplier findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new SupplierNotFoundException(id));
    }

    @Override
    public List<SupplierDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public SupplierDto getDto(Supplier supplier) {
        return mapper.toDto(supplier);
    }
}
