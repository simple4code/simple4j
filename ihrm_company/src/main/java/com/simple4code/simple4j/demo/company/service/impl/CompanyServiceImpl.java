package com.simple4code.simple4j.demo.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4code.simple4j.core.utils.IdWorker;
import com.simple4code.simple4j.demo.company.entity.Company;
import com.simple4code.simple4j.demo.company.mapper.CompanyMapper;
import com.simple4code.simple4j.demo.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author simple4j
 * @since 2020-10-31
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存企业
     * 1.配置idwork到工程
     * 2.在service中注入idwork
     * 3.通过idwork生成id
     * 4.保存企业
     */
    @Override
    public void add(Company company) {
        //基本属性的设置
        String id = idWorker.nextId() + "";
        company.setId(id);
        //默认的状态
        //0：未审核，1：已审核
        company.setAuditState("0");
        //0.未激活，1：已激活
        company.setState(1);
        companyMapper.insert(company);
    }

    /**
     * 更新企业
     * 1.参数：Company
     * 2.根据id查询企业对象
     * 3.设置修改的属性
     * 4.调用dao完成更新
     */

    @Override
    public void update(Company company) {
        Company temp = companyMapper.selectById(company.getId());
        temp.setName(company.getName());
        temp.setCompanyPhone(company.getCompanyPhone());
        companyMapper.insert(company);
    }


    /**
     * 删除企业
     */

    @Override
    public void deleteById(String id) {
        companyMapper.deleteById(id);
    }

    /**
     * 根据id查询企业
     */
    @Override
    public Company findById(String id) {
        return companyMapper.selectById(id);

    }

    /**
     * 查询企业列表
     */

    @Override
    public List<Company> findAll() {
        return companyMapper.selectList(new QueryWrapper<>());
    }
}
