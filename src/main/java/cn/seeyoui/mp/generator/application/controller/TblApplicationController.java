package cn.seeyoui.mp.generator.application.controller;

import cn.seeyoui.mp.generator.application.entity.param.TblApplicationCreateParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationListParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationUpdateParam;
import cn.seeyoui.mp.generator.application.service.TblApplicationService;
import cn.seeyoui.mp.generator.common.model.controller.BaseController;
import cn.seeyoui.mp.generator.common.model.vo.BaseResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 应用 前端控制器
 * </p>
 *
 * @author author
 * @since 2020-02-08
 */
@RestController
@Api(tags = "应用", value = "应用")
@RequestMapping("/application/tblApplication")
public class TblApplicationController extends BaseController {
    @Autowired
    private TblApplicationService tblApplicationService;

    @PostMapping("create")
    @ApiOperation(value = "新增", notes = "新增")
    public BaseResponseVo create(@RequestBody TblApplicationCreateParam param){
    return markSuccess(tblApplicationService.create(param));
    }

    @PostMapping("update")
    @ApiOperation(value = "更新", notes = "更新")
    public BaseResponseVo update(@RequestBody TblApplicationUpdateParam param){
    return markSuccess(tblApplicationService.update(param));
    }

    @GetMapping("detail")
    @ApiOperation(value = "详情", notes = "详情")
    public BaseResponseVo detail(String primaryKey){
    return markSuccess(tblApplicationService.detail(primaryKey));
    }

    @GetMapping("logicDelete")
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    public BaseResponseVo logicDelete(String primaryKey){
    return markSuccess(tblApplicationService.logicDelete(primaryKey));
    }

    @PostMapping("list")
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    public BaseResponseVo list(@RequestBody TblApplicationListParam param){
    return markSuccess(tblApplicationService.list(param));
    }
}
