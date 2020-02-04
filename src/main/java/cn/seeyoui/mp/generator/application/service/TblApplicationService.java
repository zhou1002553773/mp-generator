package cn.seeyoui.mp.generator.application.service;

import cn.seeyoui.mp.generator.application.entity.TblApplication;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationCreateParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationUpdateParam;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 应用 服务类
 * </p>
 *
 * @author author
 * @since 2020-02-04
 */
public interface TblApplicationService extends IService<TblApplication> {

     String create(TblApplicationCreateParam param);

     String update(TblApplicationUpdateParam param);

     String logicDelete(String primaryKey);

     TblApplicationVo detail(String primaryKey);
}
