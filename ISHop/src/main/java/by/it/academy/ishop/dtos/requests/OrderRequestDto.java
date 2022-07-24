package by.it.academy.ishop.dtos.requests;

import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.dtos.responds.UserRespondDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * DTO class for representing order request.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {
    @NotNull
    private ProductDto product;
    @NotNull
    private UserRespondDto user;
    @Positive
    private Long quantity;

}
