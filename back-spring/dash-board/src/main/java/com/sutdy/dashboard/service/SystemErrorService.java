package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.system.SystemError;
import com.sutdy.dashboard.domain.system.SystemErrorFactory;
import com.sutdy.dashboard.domain.system.SystemErrorRepository;
import com.sutdy.dashboard.service.common.ServiceErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kuh
 * @since 2020.05.28
 */

@Service
public class SystemErrorService extends ServiceErrorMessage {

    @Autowired
    private SystemErrorRepository systemErrorRepository;

    public SystemError save(int level, Exception e){
        SystemError error = SystemErrorFactory.create(e);
        return this.systemErrorRepository.save(error);
    }

    public SystemError findById(Long id){
        return this.systemErrorRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(NOT_FIND_DATA));
    }

    public List<SystemError> findAll(){
        return this.systemErrorRepository.findAll(Sort.by("id").descending());
    }
}
