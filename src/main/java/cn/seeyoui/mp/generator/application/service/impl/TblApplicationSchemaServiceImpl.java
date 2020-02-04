package cn.seeyoui.mp.generator.application.service.impl;

import cn.seeyoui.mp.generator.application.entity.TblApplicationSchema;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationSchemaCreateParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationSchemaUpdateParam;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationSchemaVo;
import cn.seeyoui.mp.generator.application.mapper.TblApplicationSchemaMapper;
import cn.seeyoui.mp.generator.application.service.TblApplicationSchemaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 应用模块 服务实现类
 * </p>
 *
 * @author author
 * @since 2020-02-04
 */
@Service
@Transactional
public class TblApplicationSchemaServiceImpl extends ServiceImpl<TblApplicationSchemaMapper, TblApplicationSchema> implements TblApplicationSchemaService {

     @Override
     public String create(TblApplicationSchemaCreateParam param){

          //param check

          //copy

          return null;
     }

     @Override
     public String update(TblApplicationSchemaUpdateParam param){

          return null;
     }

     @Override
     public String logicDelete(String primaryKey){
         return null;
     }

     @Override
     public TblApplicationSchemaVo detail(String primaryKey){
          return null;
     }
}
