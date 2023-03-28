package com.atguigu.yygh.order.repository;

import com.atguigu.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author:厚积薄发
 * @create:2022-08-14-16:14
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {

    //根据 医院编号 and 排班编号 进行查询（此方法只要名字写对，会自动实现）
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);

}
