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