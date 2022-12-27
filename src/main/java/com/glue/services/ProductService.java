package com.glue.services;

import com.glue.entities.Criteria;
import com.glue.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getProduct(int id);

    List<Product> getOrderProducts(List<Criteria> criteria);

    /**
     * Al usar solamente esta búsqueda, no he creado otro servicio nuevo
     *
     * @param id
     * @return
     */
}
