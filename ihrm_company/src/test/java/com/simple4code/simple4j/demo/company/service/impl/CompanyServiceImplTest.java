package com.simple4code.simple4j.demo.company.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple4code.simple4j.CompanyApplicationTest;
import com.simple4code.simple4j.demo.company.entity.Company;
import com.simple4code.simple4j.demo.company.mapper.CompanyMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CompanyServiceImplTest extends CompanyApplicationTest {

    @Autowired
    private CompanyMapper companyMapper;

    @Test
    public void test() {
        List<Company> companies = companyMapper.selectList(new QueryWrapper<>());
        companies.forEach(System.out::println);
    }
}