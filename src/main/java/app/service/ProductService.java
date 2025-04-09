package app.service;

import app.domain.Product;
import app.dto.ProductRequestDto;
import app.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> findAll();
    ProductResponseDto findById(Long id);
    ProductResponseDto save(ProductRequestDto product);
    void deleteById(Long id);
    ProductResponseDto update(Long id, ProductRequestDto product);
}
