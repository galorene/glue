package com.glue.controllers;

import com.glue.dtos.CriteriaDTO;
import com.glue.dtos.ProductDTO;
import com.glue.entities.Product;
import com.glue.services.ProductService;
import com.glue.utils.Mappers;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.glue.utils.Mappers.convertToDtoProduct;
import static com.glue.utils.Mappers.convertToEntity;

@RestController
public class ProductRestControllerImpl implements ProductRestController {

    private ProductService productService;

    public ProductRestControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return Mappers.convertToDtoProduct(productService.getAllProducts());
    }

    @Override
    public ProductDTO getProduct(@PathVariable int productId) {
        Optional<Product> product = productService.getProduct(productId);
        if (product == null) {
            throw new RuntimeException("Product id not found -" + productId);
        }
        return convertToDtoProduct(product.get());
    }

    @Override
    public List<ProductDTO> getOrderProducts(@RequestBody List<CriteriaDTO> criteriaDTOS) {
        return convertToDtoProduct(productService.getOrderProducts(convertToEntity(criteriaDTOS)));
    }
}
