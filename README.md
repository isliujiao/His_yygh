# His_yygh

#### 介绍

我所参与的预约挂号项目是一个医疗健康类的应用，主要实现了在线预约挂号、医生排班、医生问诊、在线咨询等功能。
#### 技术栈：

- 前端：使用 Vue.js、Element UI、Axios 等技术实现。
- 后端：使用 Spring Boot、MyBatis、SpringCloud等技术实现。
- 数据库：使用 MySQL 数据库存储数据和MongoDB。
- 消息队列：使用 RabbitMQ 实现异步消息处理，提高系统吞吐量。

在项目实现过程中，我们采用前后端分离的方式，前端与后端通过 RESTful API 进行据交互。同时，我们也采用 GitLab 进行版本控制和持续集成，通过自动化测试和部署，保证代码质量和系统稳定性。、

#### 启动说明
- nacos `win`
- mysql 
- redis `linux`
- mongodb `linux`
  - yygh_hosp(Database)
    - Department
    - Hospital
    - Schedule
  - 医院接口模拟管理系统ManageApplication:8889/ 
    - 医院管理 - 添加（hospital.json）
    - 科室列表 - 添加（department.json）
    - **排班列表 - 添加（schedule.json）** 日期‘workDate’修改
- rabbitmq `linux`
- Nginx `win`
  >server {
    listen       9001;
    server_name  localhost;
    location ~ /hosp/ {           
      proxy_pass http://localhost:8201;
    }
    location ~ /cmn/ {           
      proxy_pass http://localhost:8202;
    }
  }
  
ip：192.168.56.101
