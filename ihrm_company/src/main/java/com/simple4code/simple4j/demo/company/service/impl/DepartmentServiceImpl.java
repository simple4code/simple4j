package com.simple4code.simple4j.demo.company.service.impl;

import com.simple4code.simple4j.demo.company.entity.Department;
import com.simple4code.simple4j.demo.company.mapper.DepartmentMapper;
import com.simple4code.simple4j.demo.company.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author simple4j
 * @since 2020-10-31
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
