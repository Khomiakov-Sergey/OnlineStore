package by.it.academy.ishop.mappers;

import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.entities.product.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper class for products. It for converts Product to ProductDto .
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Component
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductMapper() {
        this.modelMapper = new ModelMapper();
    }

    /**
     * This method converts Product to ProductDto.
     * @param product - Entity Product.
     * @return productToDto - Product representation in DTO.
     */
    public ProductDto productToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

}
