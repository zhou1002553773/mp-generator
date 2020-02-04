package cn.seeyoui.mp.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan({"cn.seeyoui.mp.generator.**.mapper",
        "com.baomidou.mybatisplus.**.mapper"})
@EnableSwagger2
@EnableTransactionManagement
public class MpGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpGeneratorApplication.class, args);
    }

}
