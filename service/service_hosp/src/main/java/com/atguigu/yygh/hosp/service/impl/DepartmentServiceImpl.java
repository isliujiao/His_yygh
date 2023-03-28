package com.atguigu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.yygh.hosp.repository.DepartmentRepository;
import com.atguigu.yygh.hosp.service.DepartmentService;
import com.atguigu.yygh.model.hosp.Department;
import com.atguigu.yygh.vo.hosp.DepartmentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author:厚积薄发
 * @create:2022-07-19-22:32
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    //上传科室接口
    @Override
    public void save(Map<String, Object> paramMap) {
        //paramMap 转换department对象
        String paramMapString = JSONObject.toJSONString(paramMap);
        Department department = JSONObject.parseObject(paramMapString, Department.class);

        //根据 医院编号 and 科室编号 进行查询
        Department departmentExist = departmentRepository.getDepartmentByHoscodeAndDepcode(department.getHoscode(), department.getDepcode());

        //判断
        if (departmentExist != null) {//有数据做 修改 操作
            departmentExist.setUpdateTime(new Date());
            departmentExist.setIsDeleted(0);
            departmentRepository.save(departmentExist);
        } else {//没有数据做 添加 操作
            department.setUpdateTime(new Date());
            department.setCreateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }

    //查询科室接口
    @Override
    public Page<Department> findpageDepartment(Integer page, Integer limit, com.atguigu.yygh.vo.hosp.DepartmentQueryVo departmentQueryVo) {
        //0为第一页
        PageRequest pageable = PageRequest.of(page - 1, limit);

        //创建匹配器，即如何使用查询条件
        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo, department);
        department.setIsDeleted(0);
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写

        //创建实例
        Example<Department> example = Example.of(department, matcher);
        Page<Department> pages = departmentRepository.findAll(example, pageable);

        return pages;
    }

    //删除科室接口
    @Override
    public void remove(String hoscode, String depcode) {
        //根据医院编号和科室编号查询
        Department departmentByHoscodeAndDepcode = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);

        if (departmentByHoscodeAndDepcode != null) {
            //调用方法删除
            departmentRepository.deleteById(departmentByHoscodeAndDepcode.getId());
        }
    }

    //***根据医院的编号，查询医院所有科室列表***
    @Override
    public List<DepartmentVo> findDeptTree(String hoscode) {
        //1.创建list集合，用于数据封装
        List<DepartmentVo> result = new ArrayList<>();

        //2.根据医院编号，查询医院所有科室信息
        //构建Department对象
        Department department = new Department();
        //设置hoscode值
        department.setHoscode(hoscode);
        //创建Example
        Example<Department> example = Example.of(department);
        //根据条件将查询结果放入list集合
        List<Department> departmentList = departmentRepository.findAll(example);

        //***3.根据大科室编号 bigcode分组，获取每个大科室里面下级子科室***
        Map<String, List<Department>> deparmentMap =
                departmentList.stream().collect(Collectors.groupingBy(Department::getBigcode));

        //4.遍历map集合 deparmentMap
        for(Map.Entry<String,List<Department>> entry : deparmentMap.entrySet()){
            //大科室编号
            String bigcode = entry.getKey();
            //大科室编号对应的全局数据
            List<Department> deparmentBigList = entry.getValue();

            //封装大科室
            DepartmentVo departmentBigVo = new DepartmentVo();
            departmentBigVo.setDepcode(bigcode);//大科室的code
            departmentBigVo.setDepname(deparmentBigList.get(0).getBigname());//大科室name
            //封装小科室
            List<DepartmentVo> children = new ArrayList<>();
            for(Department departments : deparmentBigList){
                DepartmentVo departmentSmallVo = new DepartmentVo();
                departmentSmallVo.setDepcode(departments.getDepcode());
                departmentSmallVo.setDepname(departments.getDepname());
                //封装到list集合
                children.add(departmentSmallVo);
            }

            //把小科室list集合放到大科室children里面
            departmentBigVo.setChildren(children);
            //放到最终result里面
            result.add(departmentBigVo);
        }

        return result;
    }

    @Override
    public String getDepName(String hoscode, String depcode) {

        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if(department != null){
            return department.getDepname();
        }
        return null;
    }

    @Override
    public Department getDepartment(String hoscode, String depcode) {
        return departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
    }


}
