package cn.seeyoui.mp.generator.application.service.impl;

import cn.seeyoui.mp.generator.application.entity.TblApplicationTable;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationTableCreateParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationTableListParam;
import cn.seeyoui.mp.generator.application.entity.param.TblApplicationTableUpdateParam;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationTableListItemVo;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationTableListVo;
import cn.seeyoui.mp.generator.application.entity.vo.TblApplicationTableVo;
import cn.seeyoui.mp.generator.application.mapper.TblApplicationTableMapper;
import cn.seeyoui.mp.generator.application.service.TblApplicationTableService;
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
 * 应用数据库表 服务实现类
 * </p>
 *
 * @author author
 * @since 2020-02-08
 */
@Service
@Transactional
public class TblApplicationTableServiceImpl extends ServiceImpl<TblApplicationTableMapper, TblApplicationTable> implements TblApplicationTableService {

    @Override
    public String create(TblApplicationTableCreateParam param){
        TblApplicationTable entity = CopyUtils.convert(param, TblApplicationTable.class);
        CopyUtils.copyCreateParam(entity,"createUser");

        if(this.save(entity)){
            return entity.getPrimaryKey();
        }

        throw new RuntimeException("添加失败");
    }

    @Override
    public String update(TblApplicationTableUpdateParam param){
        TblApplicationTable entity = CopyUtils.convert(param, TblApplicationTable.class);
        CopyUtils.copyUpdateParam(entity,"updateUser");
        UpdateWrapper<TblApplicationTable> updateWrapper = new UpdateWrapper<>();
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
    public TblApplicationTableVo detail(String primaryKey){
        TblApplicationTable entity = this.getOne(new QueryWrapper<TblApplicationTable>().eq("primary_key", primaryKey));
        if(entity != null){
            return CopyUtils.convert(entity,TblApplicationTableVo.class);
        }

        throw new RuntimeException("数据不存在");
    }

    @Override
    public TblApplicationTableListVo list(TblApplicationTableListParam param){
    TblApplicationTable convert = CopyUtils.convert(param, TblApplicationTable.class);
        QueryWrapper<TblApplicationTable> queryWrapper = new QueryWrapper<>(convert);

        TblApplicationTableListVo listVo = new TblApplicationTableListVo();
        if(param.getCurrentPage() != null && param.getPageSize() != null){
            Page<TblApplicationTable> page = new Page<TblApplicationTable>(param.getCurrentPage(), param.getPageSize());
            IPage<TblApplicationTable> iPage = this.baseMapper.selectPage(page, queryWrapper);
            listVo.setRows(CopyUtils.convertList(iPage.getRecords(),TblApplicationTable.class, TblApplicationTableListItemVo.class));
            listVo.setTotal((int)iPage.getTotal());
        }else {
            List<TblApplicationTable> list = this.baseMapper.selectList(queryWrapper);
            listVo.setRows(CopyUtils.convertList(list,TblApplicationTable.class, TblApplicationTableListItemVo.class));
            listVo.setTotal(list.size());
        }

        return listVo;
    }
}
