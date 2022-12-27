package com.glue.repositoriess;

import com.glue.entities.Product;
import com.glue.repositories.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void initUseCase() {
        List<Product> products = Arrays.asList(
                new Product(1, "V-NECK BASIC SHIRT", 100, "S:4 / M:9 / L:0"),
                new Product(2, "CONTRASTING FABRIC T-SHIRT", 50, "S:35 / M:9 / L:9"),
                new Product(3, "RAISED PRINT T-SHIRT", 80, "S:20 / M:2 / L20"),
                new Product(4, "PLEATED T-SHIRT ", 3, "S:25 / M:30 / L:10"),
                new Product(5, "CONTRASTING LACE T-SHIRT", 650, "S:0 / M:1 / L:0"),
                new Product(6, "SLOGAN T-SHIRT", 20, "S:9 / M:2 / L:5")
        );
        productRepository.saveAll(products);
    }

    @AfterEach
    public void destroyAll() {
        productRepository.deleteAll();
    }

    @Test
    void findAll() {
        Assert.assertEquals(6, productRepository.findAll().size());
    }

    @Test
    void findByIdOk() {
        Assert.assertEquals(2, productRepository.findById(2).get().getId());
        Assert.assertEquals("CONTRASTING FABRIC T-SHIRT", productRepository.findById(2).get().getName());
    }

    @Test
    void findByIdNotFound() {
        Assert.assertEquals(false, productRepository.findById(10).isPresent());
    }
}
