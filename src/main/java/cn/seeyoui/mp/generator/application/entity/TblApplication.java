package cn.seeyoui.mp.generator.application.entity;

import cn.seeyoui.mp.generator.application.entity.bo.BaseTblApplication;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@ApiModel(value="TblApplication对象", description="应用")
public class TblApplication extends BaseTblApplication implements Serializable {

    private static final long serialVersionUID = 1L;

}
