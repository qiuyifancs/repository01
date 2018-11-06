package com.itheima.dao;

import com.itheima.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {

    @Select("select ot.orderid,t.* from order_traveller ot,traveller t where ot.travellerid=t.id and ot.orderid=#{orderId}")
    List<Traveller> findTravellersByOrderId(String orderId);

}
