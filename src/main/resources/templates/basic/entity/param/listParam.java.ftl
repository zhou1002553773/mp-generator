package ${package.Entity}.param;

<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import java.util.List;
import ${cfg.packageRootPath}.common.model.paging.BasePaging;
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
@ApiModel(value="${entity}CreateParam对象", description="${table.comment!}")
</#if>
<#if superEntityClass??>
public class ${entity}ListParam extends ${superEntityClass}<#if activeRecord><${entity}ListParam></#if> {
<#elseif activeRecord>
public class ${entity}ListParam extends Model<${entity}ListParam> {
<#else>
public class ${entity}ListParam extends BasePaging implements Serializable {
</#if>

    private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
    <#if swagger2>
    @ApiModelProperty(value = "${field.comment}")
    <#else>
    /**
     * ${field.comment}
     */
    </#if>
    </#if>
    <#-- 普通字段 -->
    <#if field.fill??>
    <#-- -----   存在字段填充设置   ----->
        <#if field.convert>
    @TableField(value = "${field.name}", fill = FieldFill.${field.fill})
        <#else>
    @TableField(fill = FieldFill.${field.fill})
        </#if>
    <#elseif field.convert>
    @TableField("${field.name}")
    </#if>
<#-- 乐观锁注解 -->
    <#if (versionFieldName!"") == field.name>
    @Version
    </#if>
<#-- 逻辑删除注解 -->
    <#if (logicDeleteFieldName!"") == field.name>
    @TableLogic
    </#if>
    private ${field.propertyType} ${field.propertyName};
    <#if "${field.propertyName}"?contains("Key") || "${field.propertyName}"?contains("Id")>
    @ApiModelProperty(value = "${field.comment}s")
    private List<${field.propertyType}> ${field.propertyName}s;
    </#if>
    <#--<#if "${field.propertyType}"?contains("BigDecimal")>

    @ApiModelProperty(value = "${field.comment}")
    private ${field.propertyType} ge${field.propertyName?cap_first};

    @ApiModelProperty(value = "${field.comment}")
    private ${field.propertyType} le${field.propertyName?cap_first};
    </#if>-->
    <#if "${field.propertyType}"?contains("Date")>
    @ApiModelProperty(value = "${field.comment}")
    private ${field.propertyType} ge${field.propertyName?cap_first};

    @ApiModelProperty(value = "${field.comment}")
    private ${field.propertyType} le${field.propertyName?cap_first};
    </#if>
    <#if "${field.propertyName}"?contains("status") || "${field.propertyName}"?contains("Status")>
    @ApiModelProperty(value = "${field.comment}List")
    private List<${field.propertyType}> ${field.propertyName}List;
    </#if>
</#list>
<#------------  END 字段循环遍历  ---------->

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
