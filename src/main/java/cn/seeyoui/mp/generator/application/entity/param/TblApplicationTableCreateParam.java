package cn.seeyoui.mp.generator.application.entity.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 应用数据库表
 * </p>
 *
 * @author author
 * @since 2020-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_application_table")
@ApiModel(value="TblApplicationTableCreateParam对象", description="应用数据库表")
public class TblApplicationTableCreateParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "作为系统间交互的参数值")
    private String primaryKey;

    @ApiModelProperty(value = "应用PRIMARY KEY")
    private String applicationPrimaryKey;

    @ApiModelProperty(value = "模块PRIMARY KEY")
    private String schemaPrimaryKey;

    @ApiModelProperty(value = "表名")
    private String tableName;

    private String path;

    @ApiModelProperty(value = "业务名称")
    private String boName;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备注")
    private String remark;


}
