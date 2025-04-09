package app.dto;

import lombok.Data;

import java.math.BigDecimal;

// DTO для запроса на создание/обновление
@Data
public class ProductRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String category;
}