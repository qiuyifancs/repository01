package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 产品的持久层接口
 */
public interface ProductDao {

    /**
     * 查询所有产品信息
     * @return
     */
    @Select("select * from product")
    List<Product> findAll();

    /**
     * 根据id查找产品
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    Product findProductById(String id);

    /**
     * 添加一个产品
     * @param product
     */
    @Insert("insert into\n" +
            "product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)\n" +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice}," +
            "#{productDesc},#{productStatus})")
    void addProduct(Product product);

}
