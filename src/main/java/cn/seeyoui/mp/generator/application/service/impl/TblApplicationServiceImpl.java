package cn.seeyoui.mp.generator.application.service.impl;

import cn.seeyoui.mp.generator.application.entity.TblApplication;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationCreateParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationListParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationUpdateParam;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationListItemVo;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationListVo;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationVo;
import cn.seeyoui.mp.generator.application.mapper.TblApplicationMapper;
import cn.seeyoui.mp.generator.application.service.TblApplicationService;
import cn.seeyoui.mp.generator.common.utils.CopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 应用 服务实现类
 * </p>
 *
 * @author author
 * @since 2020-02-08
 */
@Service
@Transactional
public class TblApplicationServiceImpl extends ServiceImpl<TblApplicationMapper, TblApplication> implements TblApplicationService {

    @Override
    public String create(TblApplicationCreateParam param){
        TblApplication entity = CopyUtils.convert(param, TblApplication.class);
        CopyUtils.copyCreateParam(entity,"createUser");

        if(this.save(entity)){
            return entity.getPrimaryKey();
        }

        throw new RuntimeException("添加失败");
    }

    @Override
    public String update(TblApplicationUpdateParam param){
        TblApplication entity = CopyUtils.convert(param, TblApplication.class);
        CopyUtils.copyUpdateParam(entity,"updateUser");
        UpdateWrapper<TblApplication> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("primary_key",entity.getPrimaryKey());

        if(this.update(entity,updateWrapper)){
            return entity.getPrimaryKey();
        }

        throw new RuntimeException("更新失败");
    }

    @Override
    public String logicDelete(String primaryKey){
        return null;
    }

    @Override
    public TblApplicationVo detail(String primaryKey){
        TblApplication entity = this.getOne(new QueryWrapper<TblApplication>().eq("primary_key", primaryKey));
        if(entity != null){
            return CopyUtils.convert(entity,TblApplicationVo.class);
        }

        throw new RuntimeException("数据不存在");
    }

    @Override
    public TblApplicationListVo list(TblApplicationListParam param){
    TblApplication convert = CopyUtils.convert(param, TblApplication.class);
        QueryWrapper<TblApplication> queryWrapper = new QueryWrapper<>(convert);

        TblApplicationListVo listVo = new TblApplicationListVo();
        if(param.getCurrentPage() != null && param.getPageSize() != null){
            Page<TblApplication> page = new Page<TblApplication>(param.getCurrentPage(), param.getPageSize());
            IPage<TblApplication> iPage = this.baseMapper.selectPage(page, queryWrapper);
            listVo.setRows(CopyUtils.convertList(iPage.getRecords(),TblApplication.class, TblApplicationListItemVo.class));
            listVo.setTotal((int)iPage.getTotal());
        }else {
            List<TblApplication> list = this.baseMapper.selectList(queryWrapper);
            listVo.setRows(CopyUtils.convertList(list,TblApplication.class, TblApplicationListItemVo.class));
            listVo.setTotal(list.size());
        }

        return listVo;
    }
}
