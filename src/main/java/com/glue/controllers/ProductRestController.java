package com.glue.controllers;

import com.glue.dtos.CriteriaDTO;
import com.glue.dtos.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
public interface ProductRestController {

    @GetMapping("/all")
    List<ProductDTO> getAllProducts();

    @GetMapping("/{productId}")
    ProductDTO getProduct(@PathVariable int productId);

    @PostMapping("/order")
    List<ProductDTO> getOrderProducts(@RequestBody List<CriteriaDTO> criteriaDTOS);


}
