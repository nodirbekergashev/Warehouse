package uz.pdp.warehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.warehouse.model.dto.category.*;
import uz.pdp.warehouse.model.entity.Category;
import uz.pdp.warehouse.exceptions.exception.CategoryNotFoundException;
import uz.pdp.warehouse.mapper.CategoryMapper;
import uz.pdp.warehouse.repository.CategoryRepository;
import uz.pdp.warehouse.service.abstractServices.*;
import uz.pdp.warehouse.validator.CategoryValidator;

import java.util.List;

@Service
public class CategoryService
        extends
        AbstractService<
                CategoryMapper,
                CategoryValidator,
                CategoryRepository
                >
        implements
        CRUDService<
                Category,
                CategoryActionDto,
                CategoryActionDto,
                CategoryDto
                > {

    protected CategoryService(CategoryMapper mapper, CategoryValidator validator, CategoryRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public CategoryDto save(CategoryActionDto dto) {
        validator.validateOnCreate(dto);
        Category category = mapper.fromDto(dto);
        repository.save(category);
        return mapper.toDto(category);
    }

    @Override
    public CategoryDto update(CategoryActionDto updateDto, Long id) {
        Category category = mapper.fromDto(updateDto);
        repository.save(category);
        return mapper.toDto(category);
    }

    @Override
    public void delete(Long id) {
        Category category = findById(id);
        category.softDelete();
        repository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public List<CategoryDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto getDto(Category category) {
        return mapper.toDto(category);
    }

    public Category findByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new CategoryNotFoundException(name));
    }
}
