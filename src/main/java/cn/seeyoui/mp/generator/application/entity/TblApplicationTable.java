package cn.seeyoui.mp.generator.application.entity;

import cn.seeyoui.mp.generator.application.entity.bo.BaseTblApplicationTable;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@ApiModel(value="TblApplicationTable对象", description="应用数据库表")
public class TblApplicationTable extends BaseTblApplicationTable implements Serializable {

    private static final long serialVersionUID = 1L;

}
