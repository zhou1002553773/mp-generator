package ${package.Service};

import ${package.Entity}.${entity};
import ${package.Entity}.param.${entity}CreateParam;
import ${package.Entity}.param.${entity}BatchCreateParam;
import ${package.Entity}.param.${entity}UpdateParam;
import ${package.Entity}.param.${entity}ListParam;
import ${package.Entity}.param.${entity}DeleteParam;
import ${package.Entity}.param.${entity}DetailParam;
import ${package.Entity}.vo.${entity}Vo;
import ${package.Entity}.vo.${entity}ListVo;
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

    String create(${entity}CreateParam param);

    String update(${entity}UpdateParam param);

    String delete(${entity}DeleteParam param);

    ${entity}Vo detail(${entity}DetailParam param);

    ${entity}ListVo list(${entity}ListParam param);

    String batchCreate(${entity}BatchCreateParam param);
}
</#if>
