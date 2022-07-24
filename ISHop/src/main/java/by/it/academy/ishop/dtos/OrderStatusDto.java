package by.it.academy.ishop.dtos;

import by.it.academy.ishop.entities.order.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * DTO class for representing order status.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusDto {
    @Enumerated(EnumType.STRING)
    private Status status;
}
