package com.javaguru.shoppinglist;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DefaultProductService implements ProductService {

    private Map<Long, Product> database = new HashMap<>();
    private Long PRODUCT_ID_SEQUENCE = 0L;

    private BigDecimal validPrice = new BigDecimal(0);

    public Product findBy(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be not null");
        }
        return database.get(id);
    }

    @Override
    public Long create(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot be null");
        } else if (product.getPrice().compareTo(validPrice) <= 0){
            throw new IllegalArgumentException("Price cannot be less or equal to 0");
        } else if (product.getDiscount() > 100L){
            throw new IllegalArgumentException("Discount cannot be more than 100 %");
        } else if (product.getName().length() >= 32 || product.getName().length() <= 3){
            throw new IllegalArgumentException("Name is too short/long");
        }else if (product.getDiscount() < 0L) {
            throw new IllegalArgumentException("Discount cannot be less than 0");
        }
        product.setId(PRODUCT_ID_SEQUENCE);

        database.put(PRODUCT_ID_SEQUENCE, product);
        return PRODUCT_ID_SEQUENCE++;
    }

}
