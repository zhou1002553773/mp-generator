package cn.seeyoui.mp.generator.application.service;

import cn.seeyoui.mp.generator.application.entity.TblApplicationTable;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationTableCreateParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationTableListParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationTableUpdateParam;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationTableListVo;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationTableVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 应用数据库表 服务类
 * </p>
 *
 * @author author
 * @since 2020-02-08
 */
public interface TblApplicationTableService extends IService<TblApplicationTable> {

    String create(TblApplicationTableCreateParam param);

    String update(TblApplicationTableUpdateParam param);

    String logicDelete(String primaryKey);

    TblApplicationTableVo detail(String primaryKey);

    TblApplicationTableListVo list(TblApplicationTableListParam param);
}
