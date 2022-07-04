package by.it.academy.ishop.mappers;

import by.it.academy.ishop.dtos.requests.CategoryDto;
import by.it.academy.ishop.dtos.requests.ProductDto;
import by.it.academy.ishop.entities.product.Category;
import by.it.academy.ishop.entities.product.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Category.class, CategoryDto.class);
    }

    public ProductDto productToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    public Product dtoToProduct(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }


}
