package com.simple4code.simple4j.demo.company.service;

import com.simple4code.simple4j.demo.company.entity.Company;
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
public interface CompanyService extends IService<Company> {

    void add(Company company);

    void update(Company company);

    void deleteById(String id);

    Company findById(String id);

    List<Company> findAll();
}
