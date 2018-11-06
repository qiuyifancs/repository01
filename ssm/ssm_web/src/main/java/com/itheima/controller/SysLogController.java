package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findSysLogsByPage")
    @ResponseBody
    public PageInfo findSysLogsByPage(Integer page,Integer size){
        List<SysLog> sysLogs = sysLogService.findSysLogsByPage(page,size);
        PageInfo<SysLog> pageInfo=new PageInfo(sysLogs);
        return pageInfo;
    }

}
