package com.glue.services;

import com.glue.entities.Criteria;
import com.glue.entities.Product;
import com.glue.repositories.CriteriaRepository;
import com.glue.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private CriteriaRepository criteriaRepository;

    public ProductServiceImpl(ProductRepository productRepository, CriteriaRepository criteriaRepository) {
        this.productRepository = productRepository;
        this.criteriaRepository = criteriaRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getOrderProducts(List<Criteria> criteriaList) {
        //Eliminate duplicates
        Set<Criteria> set = new HashSet<>(criteriaList);
        criteriaList.clear();
        criteriaList.addAll(set);
        //Checks that the criteria exist and then assigns them to a list along with their weights
        List<Criteria> criteriaCheckList = criteriaRepository.findBynameCriteria(set.stream().toList()
                .stream()
                .map(Criteria::getNameCriteria)
                .collect(Collectors.toList()));
        for (Criteria criteria : criteriaCheckList) {
            criteria.setWeight(set.stream().toList()
                    .get(set.stream().toList()
                            .indexOf(set.stream()
                                    .filter(c -> c.getNameCriteria().equals(criteria.getNameCriteria())).findFirst().get()))
                    .getWeight());
        }
        if (criteriaCheckList.isEmpty())
            throw new RuntimeException("Criteria not found");
        return orderProducts(criteriaCheckList, getAllProducts());
    }

    /**
     * Returns an ascending ordered list of products
     *
     * @param criteriaList
     * @param products
     * @return List<Product>
     */
    private List<Product> orderProducts(List<Criteria> criteriaList, Iterable<Product> products) {
        List<Product> productsResult = new ArrayList<>();
        for (Product product : products) {
            int total = 0;
            for (Criteria criteria : criteriaList) {
                switch (criteria.getNameCriteria()) {
                    case "unitSales":
                        total += unitSalesCriteria(criteria.getWeight(), product.getSalesUnits());
                        break;
                    case "stocks":
                        total += stockCriteria(criteria.getWeight(), product.getStocks());
                        break;
                    default:
                        //TODO futures criteria
                        break;
                }
            }
            product.setValor(total);
            productsResult.add(product);
        }
        Collections.sort(productsResult, Comparator.comparingInt(Product::getValor));
        return productsResult;
    }

    /**
     * Multiplies the weight by the number of different sizes a product has
     *
     * @param weight
     * @param stocks
     * @return int
     */
    private int stockCriteria(int weight, String stocks) {
        return (int) (weight * Arrays.stream(stocks.replaceAll("[^0-9/]+", "").split("/")).filter(n -> Integer.valueOf(n) > 0).count());
    }

    private int unitSalesCriteria(int weight, int salesUnits) {
        return weight * salesUnits;
    }

}