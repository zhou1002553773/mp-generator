package cn.seeyoui.mp.generator.template.controller;

import cn.seeyoui.mp.generator.common.model.controller.BaseController;
import cn.seeyoui.mp.generator.common.model.vo.BaseResponseVo;
import cn.seeyoui.mp.generator.template.service.GenerateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "应用", value = "应用")
@RequestMapping("/template")
public class TemplateController extends BaseController {

    @Autowired
    private GenerateService generateService;

    @GetMapping("generate")
    @ApiOperation(value = "生成代码", notes = "生成代码")
    public BaseResponseVo generate(String applicationPrimaryKey, HttpServletResponse response, HttpServletRequest request){
        return markSuccess(generateService.generate(applicationPrimaryKey,response,request));
    }
}
