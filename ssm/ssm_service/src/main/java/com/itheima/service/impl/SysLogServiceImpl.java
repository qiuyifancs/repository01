package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.SysLogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sysLogService")
@Transactional
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void addSysLog(SysLog sysLog) {
        sysLogDao.addSysLog(sysLog);
    }

    @Override
    public List<SysLog> findSysLogsByPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }


}
