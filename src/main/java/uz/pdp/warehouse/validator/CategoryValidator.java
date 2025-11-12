package uz.pdp.warehouse.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.category.CategoryActionDto;
import uz.pdp.warehouse.repository.CategoryRepository;

@Component
@RequiredArgsConstructor
public class CategoryValidator {
    private final CategoryRepository repository;

    public void validateOnCreate(CategoryActionDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        if (repository.findByName(dto.getName()).isPresent()) {
            throw new IllegalArgumentException("Category with name " + dto.getName() + " already exists");
        }

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
    }
}
