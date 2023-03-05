package ${package.Entity}.param;

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
@ApiModel(value="${entity}DeleteParam对象", description="${table.comment!}")
</#if>
<#if superEntityClass??>
public class ${entity}DeleteParam extends ${superEntityClass}<#if activeRecord><${entity}CreateParam></#if> {
<#elseif activeRecord>
public class ${entity}DeleteParam extends Model<${entity}CreateParam> {
<#else>
public class ${entity}DeleteParam implements Serializable {
</#if>

    private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段  ---------->
        @ApiModelProperty(value="primaryKey",dataType="java.lang.String")
        private List<String> primaryKeys;

        @ApiModelProperty(value="primaryKey",dataType="java.lang.String")
        private String primaryKey;

        @ApiModelProperty(value="是否需要物理删除",dataType="java.lang.Boolean")
        private Boolean delFlag = false;
<#------------  END 字段  ---------->

<#if !entityLombokModel>
    <#list table.fields as field>
        <#if field.propertyType == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>
    public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
    }

        <#if entityBuilderModel>
    public Base${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        <#else>
    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        </#if>
        this.${field.propertyName} = ${field.propertyName};
        <#if entityBuilderModel>
        return this;
        </#if>
    }
    </#list>
</#if>

<#if entityColumnConstant>
    <#list table.fields as field>
    public static final String ${field.name?upper_case} = "${field.name}";

    </#list>
</#if>
<#if activeRecord>
    @Override
    protected Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return null;
    </#if>
    }

</#if>
<#if !entityLombokModel>
    @Override
    public String toString() {
        return "${entity}{" +
    <#list table.fields as field>
        <#if field_index==0>
        "${field.propertyName}=" + ${field.propertyName} +
        <#else>
        ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
        "}";
    }
</#if>
}
