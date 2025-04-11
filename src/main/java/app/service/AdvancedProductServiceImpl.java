package app.service;

import app.domain.Product;
import app.domain.ProductRepository;
import app.dto.ProductRequestDto;
import app.dto.ProductResponseDto;
import app.exception.InsufficientQuantityException;
import app.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service("advancedProductService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdvancedProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return convertToDto(product);
    }

    @Override
    @Transactional
    public ProductResponseDto save(ProductRequestDto requestDto) {
        Product product = modelMapper.map(requestDto, Product.class);

        // Бизнес-логика: скидка 10% для электроники
        if (product.getCategory().equalsIgnoreCase("electronics")) {
            product.setPrice(product.getPrice().multiply(new BigDecimal("0.9")));
        }

        Product savedProduct = productRepository.save(product);
        return convertToDto(savedProduct);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        if (product.getQuantity() > 0) {
            throw new InsufficientQuantityException(
                    product.getName(),
                    product.getQuantity(),
                    0
            );
        }

        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProductResponseDto update(Long id, ProductRequestDto requestDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        modelMapper.map(requestDto, existingProduct);
        Product updatedProduct = productRepository.save(existingProduct);
        return convertToDto(updatedProduct);
    }

    private ProductResponseDto convertToDto(Product product) {
        ProductResponseDto dto = modelMapper.map(product, ProductResponseDto.class);
        dto.setInStock(product.getQuantity() > 0);
        return dto;
    }

}