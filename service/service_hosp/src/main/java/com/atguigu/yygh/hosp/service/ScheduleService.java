package com.atguigu.yygh.hosp.service;

import com.atguigu.yygh.model.hosp.Schedule;
import com.atguigu.yygh.vo.hosp.ScheduleOrderVo;
import com.atguigu.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author:厚积薄发
 * @create:2022-07-21-17:04
 */
public interface ScheduleService {

    //上传排版接口
    void save(Map<String, Object> paramMap);

    //查询排版接口
    Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    //删除排班
    void remove(String hoscode, String hosScheduleId);

    Map<String, Object> getRuleSchedule(Integer page, Integer limit, String hoscode, String depcode);

    //根据医院编号、科室编号、工作日期，查询排版详细信息
    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);

    //获取可预约排班数据
    Map<String,Object> getBookingScheduleRule(Integer page, Integer limit, String hoscode, String depcode);

    //根据排班id获取排班数据
    Schedule getScheduleId(String scheduleId);

    //根据排班id获取预约下单数据
    ScheduleOrderVo getScheduleOrderVo(String scheduleId);

    //更新排版数据，用于MQ
    void update(Schedule schedule);
}
