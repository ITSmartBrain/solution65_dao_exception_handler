package app.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ApiException {
    public ProductNotFoundException(Long id) {
        super("Product not found with id: " + id,
                HttpStatus.NOT_FOUND,
                "PRODUCT_NOT_FOUND");
    }
}