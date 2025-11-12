package uz.pdp.warehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.warehouse.model.dto.product.ProductCreateDto;
import uz.pdp.warehouse.model.dto.product.ProductDto;
import uz.pdp.warehouse.model.dto.product.ProductUpdateDto;
import uz.pdp.warehouse.model.entity.Product;
import uz.pdp.warehouse.exceptions.exception.ProductNotFoundException;
import uz.pdp.warehouse.mapper.ProductMapper;
import uz.pdp.warehouse.repository.ProductRepository;
import uz.pdp.warehouse.service.abstractServices.AbstractService;
import uz.pdp.warehouse.service.abstractServices.CRUDService;
import uz.pdp.warehouse.validator.ProductValidator;

import java.util.List;

@Service
public class ProductService
        extends
        AbstractService<
                ProductMapper,
                ProductValidator,
                ProductRepository
                >
        implements
        CRUDService<
                Product,
                ProductCreateDto,
                ProductUpdateDto,
                ProductDto
                > {

    protected ProductService(ProductMapper mapper, ProductValidator validator, ProductRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public ProductDto save(ProductCreateDto dto) {
        validator.validateOnCreate(dto);
        Product product = mapper.fromCreateDto(dto);
        repository.save(product);
        return mapper.toDto(product);
    }

    @Override
    public ProductDto update(ProductUpdateDto updateDto, Long id) {
        Product product = mapper.fromUpdateDto(updateDto, findById(id));
        repository.save(product);
        return mapper.toDto(product);
    }

    @Override
    public void delete(Long id) {
        Product product = findById(id);
        product.softDelete();
        repository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<ProductDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ProductDto getDto(Product product) {
        return mapper.toDto(product);
    }
}
