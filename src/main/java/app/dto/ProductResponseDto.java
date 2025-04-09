package app.dto;

import lombok.Data;
import java.math.BigDecimal;

// DTO для ответа
@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String category;
    private boolean inStock;
}