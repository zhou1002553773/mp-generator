package ${package.Entity}.vo;

import ${cfg.packageRootPath}.common.model.vo.QueryListVo;
import lombok.Data;
<#if swagger2>
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
</#if>

@Data
<#if swagger2>
@ApiModel(value="${entity}ListVo对象", description="${table.comment!}")
</#if>
public class ${entity}ListVo extends QueryListVo<${entity}ListItemVo>{

}
