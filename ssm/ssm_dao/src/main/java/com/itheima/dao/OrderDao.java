package com.itheima.dao;

import com.itheima.domain.Member;
import com.itheima.domain.Order;
import com.itheima.domain.Product;
import com.itheima.domain.Traveller;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface OrderDao {

    /**
     * 查询所有订单信息
     * @return
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,
            one = @One(select = "com.itheima.dao.ProductDao.findProductById",fetchType = FetchType.EAGER))
    })
    List<Order> findAll();

    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,
                    one = @One(select = "com.itheima.dao.ProductDao.findProductById",fetchType = FetchType.EAGER)),
            @Result(property = "member",column = "memberId",javaType = Member.class,
                    one = @One(select = "com.itheima.dao.MemberDao.findMemberById",fetchType = FetchType.EAGER)),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,
                    one = @One(select = "com.itheima.dao.TravellerDao.findTravellersByOrderId",fetchType = FetchType.LAZY))
    })
    Order findOrderById(String id);

}
