package com.itheima.service;

import com.itheima.domain.Product;

import javax.annotation.security.RolesAllowed;
import java.util.List;

public interface ProductService {

    /**
     * 查询所有产品信息
     * @return
     */
    List<Product> findAll();

    /**
     * 添加一个产品
     * @param product
     */
    void addProduct(Product product);

}
