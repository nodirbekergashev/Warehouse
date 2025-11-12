package uz.pdp.warehouse.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.category.CategoryActionDto;
import uz.pdp.warehouse.model.dto.category.CategoryDto;
import uz.pdp.warehouse.model.entity.Category;

@Component
public class CategoryMapper {
    public CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    public Category fromDto(CategoryActionDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}
