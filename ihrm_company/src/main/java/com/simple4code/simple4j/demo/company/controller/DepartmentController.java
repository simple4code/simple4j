package com.simple4code.simple4j.demo.company.controller;


import com.simple4code.simple4j.core.controller.BaseController;
import com.simple4code.simple4j.core.entity.Result;
import com.simple4code.simple4j.core.entity.ResultCode;
import com.simple4code.simple4j.demo.company.entity.Company;
import com.simple4code.simple4j.demo.company.entity.Department;
import com.simple4code.simple4j.demo.company.entity.response.DeptListResult;
import com.simple4code.simple4j.demo.company.service.CompanyService;
import com.simple4code.simple4j.demo.company.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author simple4j
 * @since 2020-10-31
 */
@CrossOrigin
@Api(tags = "部门接口")
@RestController
@RequestMapping("/company")
public class DepartmentController extends BaseController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyService companyService;


    @ApiOperation(value = "保存部门")
    @RequestMapping(value = "/department", method = RequestMethod.POST)
    public Result save(@RequestBody Department department) {
        department.setCompanyId(companyId);
        departmentService.add(department);
        return new Result(ResultCode.SUCCESS);
    }


    @ApiOperation(value = "指定企业id,查询企业的部门列表")
    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public Result findAll() {
        Company company = companyService.findById(companyId);
        List<Department> list = departmentService.findAll(companyId);
        DeptListResult deptListResult = new DeptListResult(company, list);
        return new Result(ResultCode.SUCCESS, deptListResult);
    }


    @ApiOperation(value = "根据ID查询department")
    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id") String id) {
        Department department = departmentService.findById(id);
        return new Result(ResultCode.SUCCESS, department);
    }


    @ApiOperation(value = " 修改Department")
    @RequestMapping(value = "/department/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id, @RequestBody Department department) {
        //1.设置修改的部门id
        department.setId(id);
        //2.调用service更新
        departmentService.update(department);
        return new Result(ResultCode.SUCCESS);
    }


    @ApiOperation(value = " 根据id删除")
    @RequestMapping(value = "/department/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id) {
        departmentService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }
}
