package cn.seeyoui.mp.generator.application.service;

import cn.seeyoui.mp.generator.application.entity.TblApplicationSchema;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationSchemaCreateParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationSchemaUpdateParam;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationSchemaVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 应用模块 服务类
 * </p>
 *
 * @author author
 * @since 2020-02-04
 */
public interface TblApplicationSchemaService extends IService<TblApplicationSchema> {

     String create(TblApplicationSchemaCreateParam param);

     String update(TblApplicationSchemaUpdateParam param);

     String logicDelete(String primaryKey);

     TblApplicationSchemaVo detail(String primaryKey);
}
