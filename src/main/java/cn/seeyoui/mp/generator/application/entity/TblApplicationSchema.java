package cn.seeyoui.mp.generator.application.entity;

import cn.seeyoui.mp.generator.application.entity.bo.BaseTblApplicationSchema;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 应用模块
 * </p>
 *
 * @author author
 * @since 2020-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_application_schema")
@ApiModel(value="TblApplicationSchema对象", description="应用模块")
public class TblApplicationSchema extends BaseTblApplicationSchema implements Serializable {

    private static final long serialVersionUID = 1L;

}
