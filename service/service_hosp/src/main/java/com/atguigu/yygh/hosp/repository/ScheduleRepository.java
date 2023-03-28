package com.atguigu.yygh.hosp.repository;

import com.atguigu.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author:厚积薄发
 * @create:2022-07-21-17:03
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {

    //根据 医院编号 and 排班编号 进行查询（此方法只要名字写对，会自动实现）
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);

    //根据医院编号、科室编号、工作日期，查询排版详细信息
    List<Schedule> findScheduleByHoscodeAndDepcodeAndWorkDate(String hoscode, String depcode, Date toDate);
}
