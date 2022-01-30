package cn.seeyoui.mp.generator;

import cn.seeyoui.mp.generator.template.service.GenerateService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class MpGeneratorApplicationGests {

    @Autowired
    private GenerateService generateService;

    @Test
    public void testGenerator(){
        generateService.generate("nba2kol2",null,null);
    }
}
