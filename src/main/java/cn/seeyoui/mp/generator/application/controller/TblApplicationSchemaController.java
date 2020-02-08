package cn.seeyoui.mp.generator.application.controller;

import cn.seeyoui.mp.generator.application.entity.param.TblApplicationSchemaCreateParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationSchemaListParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationSchemaUpdateParam;
import cn.seeyoui.mp.generator.application.service.TblApplicationSchemaService;
import cn.seeyoui.mp.generator.common.model.controller.BaseController;
import cn.seeyoui.mp.generator.common.model.vo.BaseResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 应用模块 前端控制器
 * </p>
 *
 * @author author
 * @since 2020-02-08
 */
@RestController
@Api(tags = "应用模块", value = "应用模块")
@RequestMapping("/application/tblApplicationSchema")
public class TblApplicationSchemaController extends BaseController {
    @Autowired
    private TblApplicationSchemaService tblApplicationSchemaService;

    @PostMapping("create")
    @ApiOperation(value = "新增", notes = "新增")
    public BaseResponseVo create(@RequestBody TblApplicationSchemaCreateParam param){
    return markSuccess(tblApplicationSchemaService.create(param));
    }

    @PostMapping("update")
    @ApiOperation(value = "更新", notes = "更新")
    public BaseResponseVo update(@RequestBody TblApplicationSchemaUpdateParam param){
    return markSuccess(tblApplicationSchemaService.update(param));
    }

    @GetMapping("detail")
    @ApiOperation(value = "详情", notes = "详情")
    public BaseResponseVo detail(String primaryKey){
    return markSuccess(tblApplicationSchemaService.detail(primaryKey));
    }

    @GetMapping("logicDelete")
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    public BaseResponseVo logicDelete(String primaryKey){
    return markSuccess(tblApplicationSchemaService.logicDelete(primaryKey));
    }

    @PostMapping("list")
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    public BaseResponseVo list(@RequestBody TblApplicationSchemaListParam param){
    return markSuccess(tblApplicationSchemaService.list(param));
    }
}
