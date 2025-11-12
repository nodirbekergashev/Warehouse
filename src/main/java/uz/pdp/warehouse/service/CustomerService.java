package uz.pdp.warehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.warehouse.model.dto.customer.CustomerCreateDto;
import uz.pdp.warehouse.model.dto.customer.CustomerDto;
import uz.pdp.warehouse.model.dto.customer.CustomerUpdateDto;
import uz.pdp.warehouse.model.entity.Customer;
import uz.pdp.warehouse.exceptions.exception.CustomerNotFoundException;
import uz.pdp.warehouse.mapper.CustomerMapper;
import uz.pdp.warehouse.repository.CustomerRepository;
import uz.pdp.warehouse.service.abstractServices.AbstractService;
import uz.pdp.warehouse.service.abstractServices.CRUDService;
import uz.pdp.warehouse.validator.CustomerValidator;

import java.util.List;

@Service
public class CustomerService
        extends
        AbstractService<
                CustomerMapper,
                CustomerValidator,
                CustomerRepository
                >
        implements
        CRUDService<
                Customer,
                CustomerCreateDto,
                CustomerUpdateDto,
                CustomerDto
                > {


    protected CustomerService(CustomerMapper mapper, CustomerValidator validator, CustomerRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public CustomerDto save(CustomerCreateDto dto) {
        validator.validateOnCreate(dto);
        Customer customer = mapper.fromCreateDto(dto);
        repository.save(customer);
        return mapper.toDto(customer);
    }

    @Override
    public CustomerDto update(CustomerUpdateDto updateDto, Long id) {
        Customer customer = mapper.fromUpdateDto(updateDto, findById(id));
        repository.save(customer);
        return mapper.toDto(customer);
    }

    @Override
    public void delete(Long id) {
        Customer deleted = findById(id);
        deleted.softDelete();
        repository.save(deleted);
    }

    @Override
    public Customer findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public List<CustomerDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public CustomerDto getDto(Customer customer) {
        return mapper
                .toDto(customer);
    }
}
