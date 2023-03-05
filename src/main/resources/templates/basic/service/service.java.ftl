package ${package.Service};

import ${package.Entity}.${entity};
import ${package.Entity}.param.*;
import ${package.Entity}.vo.${entity}Vo;
import ${package.Entity}.vo.${entity}ListVo;
import ${cfg.packageRootPath}.common.model.vo.ResultVo;
import ${superServiceClassPackage};

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    ResultVo create(${entity}CreateParam param);

    ResultVo update(${entity}UpdateParam param);

    ResultVo delete(${entity}DeleteParam param);

    ${entity}Vo detail(${entity}DetailParam param);

    ${entity}ListVo list(${entity}ListParam param);

    ResultVo batchCreate(${entity}BatchCreateParam param);

    ResultVo batchUpdate(${entity}BatchUpdateParam param);
}
</#if>
