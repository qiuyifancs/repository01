package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/findOrdersByPage")
    public ModelAndView findOrdersByPage(@RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,@RequestParam(name = "pageSize",defaultValue = "4") Integer pageSize){
        ModelAndView mv=new ModelAndView();
        List<Order> orders = orderService.findOrdersByPage(pageNum,pageSize);
        PageInfo<Order> pageInfo=new PageInfo<>(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("order-page-list");
        return mv;
    }

    @RequestMapping("/findOrderById")
    public ModelAndView findOrderById(String id){
        ModelAndView mv=new ModelAndView();
        Order order = orderService.findOrderById(id);
        mv.addObject("order",order);
        mv.setViewName("order-show");
        return mv;
    }

}
