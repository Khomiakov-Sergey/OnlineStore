package by.it.academy.ishop.mappers;

import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.entities.product.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductMapper() {
        this.modelMapper = new ModelMapper();
    }

    public ProductDto productToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

}
