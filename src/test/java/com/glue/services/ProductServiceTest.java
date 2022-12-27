package com.glue.services;

import com.glue.dtos.CriteriaDTO;
import com.glue.entities.Criteria;
import com.glue.entities.Product;
import com.glue.repositories.CriteriaRepository;
import com.glue.repositories.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.glue.utils.Mappers.convertToEntity;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    CriteriaRepository criteriaRepository;

    ProductServiceImpl productService;

    Product product1 = new Product(1, "V-NECK BASIC SHIRT", 100, "S:4 / M:9 / L:0");
    Product product2 = new Product(2, "CONTRASTING FABRIC T-SHIRT", 50, "S:35 / M:9 / L:9");
    Product product3 = new Product(3, "RAISED PRINT T-SHIRT", 80, "S:20 / M:2 / L20");
    Product product4 = new Product(4, "PLEATED T-SHIRT ", 3, "S:25 / M:30 / L:10");
    Product product5 = new Product(5, "CONTRASTING LACE T-SHIRT", 650, "S:0 / M:1 / L:0");
    Product product6 = new Product(6, "SLOGAN T-SHIRT", 20, "S:9 / M:2 / L:5");

    @BeforeEach
    void initUseCase() {
        productService = new ProductServiceImpl(productRepository, criteriaRepository);
    }

    @Test
    void getOrderProducts() {
        //parameters
        List<CriteriaDTO> criteriaDTOS = Arrays.asList(new CriteriaDTO("stocks", 3), new CriteriaDTO("unitSales", 1));

        //data
        List<Product> products = new ArrayList<>(Arrays.asList(product1, product2, product3, product4, product5, product6));
        List<Criteria> criteria = Arrays.asList(new Criteria(1, "unitSales"), new Criteria(2, "stocks"));

        when(productRepository.findAll()).thenReturn(products);
        when(criteriaRepository.findBynameCriteria(criteriaDTOS.stream()
                .map(CriteriaDTO::getNameCriteria)
                .collect(Collectors.toList()))).thenReturn(criteria);

        Assert.assertEquals(new ArrayList<>(Arrays.asList(product4, product6, product2, product3, product1, product5)), productService.getOrderProducts(convertToEntity(criteriaDTOS)));

    }

    @Test
    public void getAllProducts() {
        List<Product> products = new ArrayList<>(Arrays.asList(product1, product2, product3, product4, product5, product6));
        when(productRepository.findAll()).thenReturn(products);

        Assert.assertEquals(6, productService.getAllProducts().size());
    }

    @Test
    public void getProduct() {
        when(productRepository.findById(5)).thenReturn(Optional.ofNullable(product5));

        Product product = productService.getProduct(5).get();

        Assert.assertEquals(5, product.getId());
        Assert.assertEquals("CONTRASTING LACE T-SHIRT", product.getName());
    }

}
