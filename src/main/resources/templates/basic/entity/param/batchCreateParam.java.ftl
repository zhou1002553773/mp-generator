package ${package.Entity}.param;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
<#if swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
</#if>

/**
* <p>
* ${table.comment!}
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if entityLombokModel>
@Data
<#if superEntityClass??>
@EqualsAndHashCode(callSuper = true)
<#else>
@EqualsAndHashCode(callSuper = false)
</#if>
</#if>
<#if table.convert>
</#if>
<#if swagger2>
@ApiModel(value="${entity}BatchCreateParam对象", description="${table.comment!}")
</#if>
<#if superEntityClass??>
public class ${entity}BatchCreateParam extends ${superEntityClass}<#if activeRecord><${entity}BatchCreateParam></#if> {
<#elseif activeRecord>
public class ${entity}BatchCreateParam extends Model<${entity}CreateParam> {
<#else>
public class ${entity}BatchCreateParam implements Serializable {
</#if>

    private List<${entity}CreateParam> list = new ArrayList<>();
}
