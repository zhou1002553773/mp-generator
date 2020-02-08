package cn.seeyoui.mp.generator.application.service.impl;

import cn.seeyoui.mp.generator.application.entity.TblApplicationSchema;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationSchemaCreateParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationSchemaListParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationSchemaUpdateParam;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationSchemaListItemVo;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationSchemaVo;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationSchemaListVo;
import cn.seeyoui.mp.generator.application.service.TblApplicationSchemaService;
import cn.seeyoui.mp.generator.application.mapper.TblApplicationSchemaMapper;
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
 * 应用模块 服务实现类
 * </p>
 *
 * @author author
 * @since 2020-02-08
 */
@Service
@Transactional
public class TblApplicationSchemaServiceImpl extends ServiceImpl<TblApplicationSchemaMapper, TblApplicationSchema> implements TblApplicationSchemaService {

    @Override
    public String create(TblApplicationSchemaCreateParam param){
        TblApplicationSchema entity = CopyUtils.convert(param, TblApplicationSchema.class);
        CopyUtils.copyCreateParam(entity,"createUser");

        if(this.save(entity)){
            return entity.getPrimaryKey();
        }

        throw new RuntimeException("添加失败");
    }

    @Override
    public String update(TblApplicationSchemaUpdateParam param){
        TblApplicationSchema entity = CopyUtils.convert(param, TblApplicationSchema.class);
        CopyUtils.copyUpdateParam(entity,"updateUser");
        UpdateWrapper<TblApplicationSchema> updateWrapper = new UpdateWrapper<>();
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
    public TblApplicationSchemaVo detail(String primaryKey){
        TblApplicationSchema entity = this.getOne(new QueryWrapper<TblApplicationSchema>().eq("primary_key", primaryKey));
        if(entity != null){
            return CopyUtils.convert(entity,TblApplicationSchemaVo.class);
        }

        throw new RuntimeException("数据不存在");
    }

    @Override
    public TblApplicationSchemaListVo list(TblApplicationSchemaListParam param){
    TblApplicationSchema convert = CopyUtils.convert(param, TblApplicationSchema.class);
        QueryWrapper<TblApplicationSchema> queryWrapper = new QueryWrapper<>(convert);

        TblApplicationSchemaListVo listVo = new TblApplicationSchemaListVo();
        if(param.getCurrentPage() != null && param.getPageSize() != null){
            Page<TblApplicationSchema> page = new Page<TblApplicationSchema>(param.getCurrentPage(), param.getPageSize());
            IPage<TblApplicationSchema> iPage = this.baseMapper.selectPage(page, queryWrapper);
            listVo.setRows(CopyUtils.convertList(iPage.getRecords(),TblApplicationSchema.class, TblApplicationSchemaListItemVo.class));
            listVo.setTotal((int)iPage.getTotal());
        }else {
            List<TblApplicationSchema> list = this.baseMapper.selectList(queryWrapper);
            listVo.setRows(CopyUtils.convertList(list,TblApplicationSchema.class, TblApplicationSchemaListItemVo.class));
            listVo.setTotal(list.size());
        }

        return listVo;
    }
}
