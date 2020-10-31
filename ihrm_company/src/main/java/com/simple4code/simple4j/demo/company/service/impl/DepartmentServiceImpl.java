package com.simple4code.simple4j.demo.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple4code.simple4j.core.utils.IdWorker;
import com.simple4code.simple4j.demo.company.entity.Department;
import com.simple4code.simple4j.demo.company.mapper.DepartmentMapper;
import com.simple4code.simple4j.demo.company.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {


    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private IdWorker idWorker;

    /**
     * 1.保存部门
     */
    @Override
    public void add(Department department) {
        //设置主键的值
        String id = idWorker.nextId() + "";
        department.setId(id);
        //调用dao保存部门
        departmentMapper.insert(department);
    }

    /**
     * 2.更新部门
     */
    @Override
    public void update(Department department) {
        //1.根据id查询部门
        Department dept = departmentMapper.selectById(department.getId());
        //2.设置部门属性
        dept.setCode(department.getCode());
        dept.setIntroduce(department.getIntroduce());
        dept.setName(department.getName());
        //3.更新部门
        departmentMapper.insert(dept);
    }

    /**
     * 3.根据id查询部门
     */
    @Override
    public Department findById(String id) {
        return departmentMapper.selectById(id);
    }

    /**
     * 4.查询全部部门列表
     */
    @Override
    public List<Department> findAll(String companyId) {
        /**
         * 用户构造查询条件
         *      1.只查询companyId
         *      2.很多的地方都需要根据companyId查询
         *      3.很多的对象中都具有companyId
         *
         */
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("companyId", companyId);
        return departmentMapper.selectList(queryWrapper);
    }

    /**
     * 5.根据id删除部门
     */
    @Override
    public void deleteById(String id) {
        departmentMapper.deleteById(id);
    }

}
