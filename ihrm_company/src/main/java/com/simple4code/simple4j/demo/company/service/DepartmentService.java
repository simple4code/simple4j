package com.simple4code.simple4j.demo.company.service;

import com.simple4code.simple4j.demo.company.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author simple4j
 * @since 2020-10-31
 */
public interface DepartmentService extends IService<Department> {

    void add(Department department);

    void update(Department department);

    Department findById(String id);

    List<Department> findAll(String companyId);

    void deleteById(String id);
}
