package cn.seeyoui.mp.generator.application.controller;

import cn.seeyoui.mp.generator.application.entity.param.TblApplicationTableCreateParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationTableListParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationTableUpdateParam;
import cn.seeyoui.mp.generator.application.service.TblApplicationTableService;
import cn.seeyoui.mp.generator.common.model.controller.BaseController;
import cn.seeyoui.mp.generator.common.model.vo.BaseResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 应用数据库表 前端控制器
 * </p>
 *
 * @author author
 * @since 2020-02-08
 */
@RestController
@Api(tags = "应用数据库表", value = "应用数据库表")
@RequestMapping("/application/tblApplicationTable")
public class TblApplicationTableController extends BaseController {
    @Autowired
    private TblApplicationTableService tblApplicationTableService;

    @PostMapping("create")
    @ApiOperation(value = "新增", notes = "新增")
    public BaseResponseVo create(@RequestBody TblApplicationTableCreateParam param){
    return markSuccess(tblApplicationTableService.create(param));
    }

    @PostMapping("update")
    @ApiOperation(value = "更新", notes = "更新")
    public BaseResponseVo update(@RequestBody TblApplicationTableUpdateParam param){
    return markSuccess(tblApplicationTableService.update(param));
    }

    @GetMapping("detail")
    @ApiOperation(value = "详情", notes = "详情")
    public BaseResponseVo detail(String primaryKey){
    return markSuccess(tblApplicationTableService.detail(primaryKey));
    }

    @GetMapping("logicDelete")
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    public BaseResponseVo logicDelete(String primaryKey){
    return markSuccess(tblApplicationTableService.logicDelete(primaryKey));
    }

    @PostMapping("list")
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    public BaseResponseVo list(@RequestBody TblApplicationTableListParam param){
    return markSuccess(tblApplicationTableService.list(param));
    }
}
