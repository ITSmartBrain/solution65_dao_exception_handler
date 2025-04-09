package app.dto;

import lombok.Data;

// DTO для ответа операций restock/sell
@Data
public class ProductOperationResponseDto {
    private Long productId;
    private String productName;
    private Integer newQuantity;
    private String operationType;
}