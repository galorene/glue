package com.glue.utils;

import com.glue.dtos.CriteriaDTO;
import com.glue.dtos.ProductDTO;
import com.glue.entities.Criteria;
import com.glue.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("Mappers")
public class Mappers {

    private static ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static ProductDTO convertToDtoProduct(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

    public static List<ProductDTO> convertToDtoProduct(List<Product> products) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            productDTOS.add(modelMapper.map(product, ProductDTO.class));
        }
        return productDTOS;
    }

    public static Criteria convertToEntity(CriteriaDTO criteriaDTO) {
        Criteria criteria = modelMapper.map(criteriaDTO, Criteria.class);
        return criteria;
    }

    public static List<Criteria> convertToEntity(List<CriteriaDTO> criteriaDTOS) {
        List<Criteria> criteriaList = new ArrayList<>();
        for (CriteriaDTO criteriaDTO : criteriaDTOS) {
            criteriaList.add(modelMapper.map(criteriaDTO, Criteria.class));
        }
        return criteriaList;
    }
}
