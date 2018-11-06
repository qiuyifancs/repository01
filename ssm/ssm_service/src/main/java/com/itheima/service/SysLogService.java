package com.itheima.service;

import com.itheima.domain.SysLog;

import java.util.List;

public interface SysLogService {

    void addSysLog(SysLog sysLog);

    List<SysLog> findSysLogsByPage(Integer page,Integer size);

}
