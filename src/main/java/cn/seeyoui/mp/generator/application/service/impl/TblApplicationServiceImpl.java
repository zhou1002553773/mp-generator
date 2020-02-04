package cn.seeyoui.mp.generator.application.service.impl;

import cn.seeyoui.mp.generator.application.entity.TblApplication;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationCreateParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationUpdateParam;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationVo;
import cn.seeyoui.mp.generator.application.mapper.TblApplicationMapper;
import cn.seeyoui.mp.generator.application.service.TblApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 应用 服务实现类
 * </p>
 *
 * @author author
 * @since 2020-02-04
 */
@Service
@Transactional
public class TblApplicationServiceImpl extends ServiceImpl<TblApplicationMapper, TblApplication> implements TblApplicationService {

     @Override
     public String create(TblApplicationCreateParam param){

          //param check

          //copy

          return null;
     }

     @Override
     public String update(TblApplicationUpdateParam param){

          return null;
     }

     @Override
     public String logicDelete(String primaryKey){
         return null;
     }

     @Override
     public TblApplicationVo detail(String primaryKey){
          return null;
     }
}
