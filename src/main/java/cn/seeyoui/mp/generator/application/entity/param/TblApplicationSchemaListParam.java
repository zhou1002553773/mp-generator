package cn.seeyoui.mp.generator.application.entity.param;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 应用模块
 * </p>
 *
 * @author author
 * @since 2020-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TblApplicationSchemaCreateParam对象", description="应用模块")
public class TblApplicationSchemaListParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "作为系统间交互的参数值")
    private String primaryKey;

    @ApiModelProperty(value = "应用PRIMARY KEY")
    private String applicationPrimaryKey;

    @ApiModelProperty(value = "模块名")
    private String schemaName;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String updateUser;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value="当前页",dataType="java.lang.Integer")
    private Integer currentPage;

    @ApiModelProperty(value="页条数",dataType="java.lang.Integer")
    private Integer pageSize;


}
