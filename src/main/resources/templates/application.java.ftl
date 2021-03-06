package ${cfg.packageRootPath};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan({"${cfg.packageRootPath}.**.mapper",
        "com.baomidou.mybatisplus.**.mapper"})
@EnableSwagger2
@EnableTransactionManagement
public class ${cfg.applicationName}Application {

    public static void main(String[] args) {
        SpringApplication.run(${cfg.applicationName}Application.class, args);
    }

}
