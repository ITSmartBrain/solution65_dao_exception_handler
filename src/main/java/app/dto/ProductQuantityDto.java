package app.dto;

import lombok.Data;

// DTO для операций с количеством
@Data
public class ProductQuantityDto {
    private Long productId;
    private String productName;
    private Integer availableQuantity;
    private Integer requestedQuantity;
}