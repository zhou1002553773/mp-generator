package cn.seeyoui.mp.generator.application.service.impl;

import cn.seeyoui.mp.generator.application.entity.TblApplicationTable;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationTableCreateParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationTableUpdateParam;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationTableVo;
import cn.seeyoui.mp.generator.application.mapper.TblApplicationTableMapper;
import cn.seeyoui.mp.generator.application.service.TblApplicationTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 应用数据库表 服务实现类
 * </p>
 *
 * @author author
 * @since 2020-02-04
 */
@Service
@Transactional
public class TblApplicationTableServiceImpl extends ServiceImpl<TblApplicationTableMapper, TblApplicationTable> implements TblApplicationTableService {

     @Override
     public String create(TblApplicationTableCreateParam param){

          //param check

          //copy

          return null;
     }

     @Override
     public String update(TblApplicationTableUpdateParam param){

          return null;
     }

     @Override
     public String logicDelete(String primaryKey){
         return null;
     }

     @Override
     public TblApplicationTableVo detail(String primaryKey){
          return null;
     }
}
