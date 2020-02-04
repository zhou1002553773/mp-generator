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
 * 应用
 * </p>
 *
 * @author author
 * @since 2020-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_application")
@ApiModel(value="TblApplicationCreateParam对象", description="应用")
public class TblApplicationCreateParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "作为系统间交互的参数值")
    private String primaryKey;

    @ApiModelProperty(value = "项目名称")
    private String applicationName;

    @ApiModelProperty(value = "GroupId")
    private String groupId;

    @ApiModelProperty(value = "ArtifactId")
    private String artifactId;

    @ApiModelProperty(value = "包路径")
    private String packagePath;

    @ApiModelProperty(value = "Description")
    private String description;

    @ApiModelProperty(value = "Java版本")
    private String javaVersion;

    @ApiModelProperty(value = "数据库名称")
    private String databaseName;

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


}
