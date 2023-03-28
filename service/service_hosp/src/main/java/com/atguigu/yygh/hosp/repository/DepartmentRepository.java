package com.atguigu.yygh.hosp.repository;

import com.atguigu.yygh.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author:厚积薄发
 * @create:2022-07-19-22:29
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {

    //上传科室接口
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);

}
