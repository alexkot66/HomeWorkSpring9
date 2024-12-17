package com.example.productStorage.service;

import com.example.productStorage.exceptions.ExcessAmountException;
import com.example.productStorage.exceptions.ResourceNotFoundException;
import com.example.productStorage.model.Product;
import com.example.productStorage.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Получение всех товаров.
     * @return список товаров.
     */
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    /**
     * Получение данных о конкретном товаре на складе.
     * @param id идентификатор товара.
     * @return объект товара.
     * @throws ResourceNotFoundException исключение при отсутствии товара.
     */
    public Product getProductById(Long id) throws  ResourceNotFoundException{
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Товар " + id + " не найден!"));
    }

    /**
     * Уменьшение остатка товара на складе.
     * @param id идентификатор товара.
     * @param amount количество для уменьшения.
     * @throws ExcessAmountException исключение при превышении остатка.
     */

    @Transactional
    public void reduceAmount(Long id, int amount)
            throws ExcessAmountException{
        Product product = getProductById(id);
        if (amount > product.getAmount())
            throw new ExcessAmountException("Заказ превышает остаток на складе!");
        product.setAmount(product.getAmount() - amount);
        product.setReserved(product.getReserved() - amount);
        productRepository.save(product);
    }

    /**
     * Резервирование товара на складе.
     * @param id идентификатор товара.
     * @param amount количество заказа.
     * @throws ExcessAmountException исключение при превышении остатка.
     */
    @Transactional
    public void reservedProduct(Long id, int amount) throws ExcessAmountException{
        Product product = getProductById(id);
        if (amount > product.getAmount())
            throw new ExcessAmountException("Заказ превышает остаток на складе!");
        product.setReserved(amount);
        productRepository.save(product);
    }

    /**
     * Откат резервирования товара на складе.
     * @param id идентификатор товара.
     * @param amount количество.
     */
    @Transactional
    public void rollbackReservedProduct(Long id, int amount){
        Product product = getProductById(id);
        product.setReserved(product.getReserved() - amount);
        productRepository.save(product);
    }
}
