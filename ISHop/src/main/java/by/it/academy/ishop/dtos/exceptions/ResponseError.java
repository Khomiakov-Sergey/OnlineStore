package by.it.academy.ishop.dtos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for representing errors.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {
    private String message;

}
