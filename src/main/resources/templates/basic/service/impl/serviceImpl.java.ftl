package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import ${package.Entity}.${entity};
import ${package.Entity}.param.*;
import ${package.Entity}.vo.${entity}Vo;
import ${package.Entity}.vo.${entity}ListVo;
import ${package.Entity}.vo.${entity}ListItemVo;
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import ${cfg.packageRootPath}.common.constant.GlobalErrorCode;
import ${cfg.packageRootPath}.common.context.SystemContext;
import ${cfg.packageRootPath}.common.exception.BusinessException;
import ${cfg.packageRootPath}.common.utils.CopyUtils;
import ${cfg.packageRootPath}.common.utils.UUIDUtils;
import ${cfg.packageRootPath}.common.utils.CollectionsUtils;
import ${cfg.packageRootPath}.common.constant.CommonConstant;
import ${cfg.packageRootPath}.common.model.vo.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@Transactional
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public ResultVo create(${entity}CreateParam param){
        ${entity} entity = CopyUtils.convert(param, ${entity}.class);
        CopyUtils.copyCreateParam(entity,SystemContext.getUserPrimaryKey());

        if(this.save(entity)){
            return new ResultVo(true,"保存成功",entity.getPrimaryKey());
        }

        throw new BusinessException(GlobalErrorCode.GL9990500);
    }

    @Override
    public ResultVo update(${entity}UpdateParam param){
        ${entity} entity = CopyUtils.convert(param, ${entity}.class);
        CopyUtils.copyUpdateParam(entity,SystemContext.getUserPrimaryKey());
        UpdateWrapper<${entity}> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("primary_key",entity.getPrimaryKey());

        if(this.update(entity,updateWrapper)){
            return new ResultVo(true,"更新成功",entity.getPrimaryKey());
        }

        throw new BusinessException(GlobalErrorCode.GL9990500);
    }

    @Override
    public ResultVo delete(${entity}DeleteParam param){
        if (StringUtils.isEmpty(param.getPrimaryKey()) && CollectionUtils.isEmpty(param.getPrimaryKeys())) {
            throw new BusinessException(GlobalErrorCode.GL9990400);
        }

        if (param.getDelFlag()) {
            Wrapper<${entity}> wrapper = new QueryWrapper<${entity}>()
            .lambda()
            .eq(StringUtils.isNotEmpty(param.getPrimaryKey()), ${entity}::getPrimaryKey, param.getPrimaryKey())
            .in(CollectionUtils.isNotEmpty(param.getPrimaryKeys()), ${entity}::getPrimaryKey, param.getPrimaryKeys());
            if (this.remove(wrapper)) {
                return new ResultVo(true, "删除成功");
            }
        } else {
            ${entity} delEntity = CopyUtils.convert(param, ${entity}.class);
            CopyUtils.copyUpdateParam(delEntity, SystemContext.getUserPrimaryKey());
            delEntity.setStatus(CommonConstant.SYSTEM_STATUS_LOSE_EFFECT.value());
            Wrapper<${entity}> wrapper = new QueryWrapper<${entity}>()
                .lambda()
                .eq(StringUtils.isNotEmpty(param.getPrimaryKey()), ${entity}::getPrimaryKey, param.getPrimaryKey())
                .in(CollectionUtils.isNotEmpty(param.getPrimaryKeys()), ${entity}::getPrimaryKey, param.getPrimaryKeys());
            if (this.update(delEntity, wrapper)) {
                return new ResultVo(true, "删除成功");
            }
        }
        throw new BusinessException(GlobalErrorCode.GL9990500);
    }

    @Override
    public ${entity}Vo detail(${entity}DetailParam param){
        ${entity} entity = this.getOne(new QueryWrapper<${entity}>().eq("primary_key", param.getPrimaryKey()));
        if(entity != null){
            ${entity}Vo vo = CopyUtils.convert(entity,${entity}Vo.class);
            vo.setSuccessMessage("查询成功");
            return vo;
        }

        throw new BusinessException(GlobalErrorCode.GL9990500);
    }

    @Override
    public ${entity}ListVo list(${entity}ListParam param){
        ${entity} convert = CopyUtils.convert(param, ${entity}.class);
        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>(convert);
        queryWrapper.lambda()
        <#list table.fields as field>
            <#if "${field.propertyName}"?contains("Key")>
        .in(CollectionUtils.isNotEmpty(param.get${field.propertyName?cap_first}s()), ${entity}::get${field.propertyName?cap_first},param.get${field.propertyName?cap_first}s())
            </#if>
            <#if "${field.propertyType}"?contains("Date")>
        .ge(param.getGe${field.propertyName?cap_first}() != null, ${entity}::get${field.propertyName?cap_first},param.getGe${field.propertyName?cap_first}())
                .le(param.getLe${field.propertyName?cap_first}() != null, ${entity}::get${field.propertyName?cap_first},param.getLe${field.propertyName?cap_first}())
            </#if>
<#--            <#if "${field.propertyType}"?contains("BigDecimal")>
        .ge(param.getGe${field.propertyName?cap_first}() != null, ${entity}::get${field.propertyName?cap_first},param.getGe${field.propertyName?cap_first}())
                .le(param.getLe${field.propertyName?cap_first}() != null, ${entity}::get${field.propertyName?cap_first},param.getLe${field.propertyName?cap_first}())
            </#if>-->
            <#--<#if "${field.propertyType}"?contains("String")>
        .like(param.getLike${field.propertyName?cap_first}() != null, ${entity}::get${field.propertyName?cap_first},param.getLike${field.propertyName?cap_first}())
            </#if>-->
        </#list>;

        ${entity}ListVo listVo = new ${entity}ListVo();
        if(param.getCurrentPage() != null && param.getPageSize() != null){
            Page<${entity}> page = new Page<${entity}>(param.getCurrentPage(), param.getPageSize());
            IPage<${entity}> iPage = this.baseMapper.selectPage(page, queryWrapper);
            listVo.setRows(CopyUtils.convertList(iPage.getRecords(),${entity}.class, ${entity}ListItemVo.class));
            listVo.setTotal((int)iPage.getTotal());
        }else {
            List<${entity}> list = this.baseMapper.selectList(queryWrapper);
            listVo.setRows(CopyUtils.convertList(list,${entity}.class, ${entity}ListItemVo.class));
            listVo.setTotal(list.size());
        }

        listVo.setSuccessMessage("查询成功");
        return listVo;
    }

    @Override
    public ResultVo batchCreate(${entity}BatchCreateParam param) {
        List<${entity}> list = CopyUtils.convertList(param.getList(), ${entity}CreateParam.class, ${entity}.class);

        list.forEach(item -> {
            String primaryKey = item.getPrimaryKey();
            CopyUtils.copyCreateParam(item,SystemContext.getUserPrimaryKey());
            if (StringUtils.isNotEmpty(primaryKey)){
                item.setPrimaryKey(primaryKey);
            }
        });

        if (this.saveBatch(list)){
            return new ResultVo(true, "保存成功", String.join(",", list.stream().map(${entity}::getPrimaryKey).collect(Collectors.toList())));
        }

        throw new BusinessException(GlobalErrorCode.GL9990500);
    }

    @Override
    public ResultVo batchUpdate(${entity}BatchUpdateParam param) {
        if (CollectionUtils.isEmpty(param.getList())) {
            return new ResultVo(true, "暂无可修改数据");
        }
        List<${entity}> list = CopyUtils.convertList(param.getList(), ${entity}UpdateParam.class, ${entity}.class);
        // 批量设置更新人、更新时间
        CopyUtils.copyBatchUpdateParam(list, SystemContext.getUserPrimaryKey());
        // 通过primaryKey 先查询一下. 然后同id的方式更新
        List<String> updatePrimaryKeys = CollectionsUtils.getListProperty(list, e -> e.getPrimaryKey());

        // 根据primaryKey 查询
        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
            .in(${entity}::getPrimaryKey, updatePrimaryKeys);
        queryWrapper.select("id", "primary_key");

        List<${entity}> dataList = this.list(queryWrapper);
        if (CollectionUtils.isEmpty(dataList)) {
            return new ResultVo(true, "修改成功");
        }
        // 更新数据ID
        Map<String, Long> updateDataId = CollectionsUtils.getMapPropertyWithNull(dataList, e -> e.getPrimaryKey(), e -> e.getId());
        // 更新数据
        List<${entity}> updateList = new ArrayList<>();
        for (${entity} item : list) {
            Long id = updateDataId.get(item.getPrimaryKey());
            if (null == id) {
                continue;
            }
            item.setId(id);
            updateList.add(item);
        }
        if (CollectionUtils.isNotEmpty(updateList)){
            this.updateBatchById(updateList);
        }
        return new ResultVo(true, "修改成功");
    }
}
</#if>
