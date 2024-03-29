server.port=8080

# Database
# 单数据源
spring.datasource.dynamic.primary=master
spring.datasource.dynamic.datasource.master.url=jdbc:mysql://127.0.0.1:3306/${cfg.databaseName}?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
spring.datasource.dynamic.datasource.master.username=root
spring.datasource.dynamic.datasource.master.password=test123
spring.datasource.dynamic.datasource.master.driver-class-name=com.mysql.cj.jdbc.Driver

# Mybatis
mybatis-plus.mapper-locations=classpath:mybatis/mapper/**/*.xml
mybatis-plus.typeAliasesPackage=${cfg.packageRootPath}

spring.freemarker.cache=false
spring.freemarker.charset=UTF-8
spring.freemarker.check-template-location=true
spring.freemarker.enabled=true
spring.freemarker.suffix=.ftl
spring.freemarker.template-loader-path=classpath:/templates