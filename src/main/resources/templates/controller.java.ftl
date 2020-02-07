package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

import ${package.Entity}.param.${entity}CreateParam;
import ${package.Entity}.param.${entity}UpdateParam;
import ${package.Entity}.param.${entity}ListParam;
import org.springframework.beans.factory.annotation.Autowired;
import ${cfg.packageRootPath}.common.model.vo.BaseResponseVo;
import ${package.Service}.${table.serviceName};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#if table.comment??>
@Api(tags = "${table.comment}", value = "${table.comment}")
<#else>
@Api(tags = "${entity}前端控制器", value = "${entity}前端控制器")
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    @Autowired
    private ${entity}Service ${entity?uncap_first}Service;

    @PostMapping("create")
    @ApiOperation(value = "新增", notes = "新增")
    public BaseResponseVo create(@RequestBody ${entity}CreateParam param){
    return markSuccess(${entity?uncap_first}Service.create(param));
    }

    @PostMapping("update")
    @ApiOperation(value = "更新", notes = "更新")
    public BaseResponseVo update(@RequestBody ${entity}UpdateParam param){
    return markSuccess(${entity?uncap_first}Service.update(param));
    }

    @GetMapping("detail")
    @ApiOperation(value = "详情", notes = "详情")
    public BaseResponseVo detail(String primaryKey){
    return markSuccess(${entity?uncap_first}Service.detail(primaryKey));
    }

    @GetMapping("logicDelete")
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    public BaseResponseVo logicDelete(String primaryKey){
    return markSuccess(${entity?uncap_first}Service.logicDelete(primaryKey));
    }

    @PostMapping("list")
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    public BaseResponseVo list(@RequestBody ${entity}ListParam param){
    return markSuccess(${entity?uncap_first}Service.list(param));
    }
}
</#if>
