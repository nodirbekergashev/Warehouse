package uz.pdp.warehouse.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.product.ProductCreateDto;
import uz.pdp.warehouse.model.dto.product.ProductDto;
import uz.pdp.warehouse.model.dto.product.ProductUpdateDto;
import uz.pdp.warehouse.model.entity.Product;
import uz.pdp.warehouse.repository.CategoryRepository;
import uz.pdp.warehouse.repository.SupplierRepository;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final CategoryRepository categoryRepo;
    private final SupplierRepository supplierRepo;

    public Product fromCreateDto(ProductCreateDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setSku(dto.getSku());
        product.setUnit(dto.getUnit());
        product.setPrice(dto.getPrice());
        attachRelations(product, dto.getCategoryId(), dto.getSupplierId());
        return product;
    }

    public Product fromUpdateDto(ProductUpdateDto dto, Product existingProduct) {
        existingProduct.setName(dto.getName());
        existingProduct.setUnit(dto.getUnit());
        existingProduct.setPrice(dto.getPrice());
        attachRelations(existingProduct, dto.getCategoryId(), dto.getSupplierId());
        return existingProduct;
    }

    private void attachRelations(Product product, Long catId, Long supplierId) {
        product.setCategory(categoryRepo
                .findById(catId)
                .orElseThrow(() -> new RuntimeException("Category not found")));
        product.setSupplier(supplierRepo
                .findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found")));

    }

    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setUnit(product.getUnit());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setSupplierName(product.getSupplier().getName());
        return productDto;
    }
}
