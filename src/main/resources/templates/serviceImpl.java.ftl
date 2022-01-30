package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import ${package.Entity}.${entity};
import ${package.Entity}.param.${entity}CreateParam;
import ${package.Entity}.param.${entity}BatchCreateParam;
import ${package.Entity}.param.${entity}UpdateParam;
import ${package.Entity}.param.${entity}ListParam;
import ${package.Entity}.vo.${entity}Vo;
import ${package.Entity}.vo.${entity}ListVo;
import ${package.Entity}.vo.${entity}ListItemVo;
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import ${cfg.packageRootPath}.common.utils.CopyUtils;
import ${cfg.packageRootPath}.common.utils.UUIDUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public String create(${entity}CreateParam param){
        ${entity} entity = CopyUtils.convert(param, ${entity}.class);
        CopyUtils.copyCreateParam(entity,"createUser");

        if(this.save(entity)){
            return entity.getPrimaryKey();
        }

        throw new RuntimeException("添加失败");
    }

    @Override
    public String update(${entity}UpdateParam param){
        ${entity} entity = CopyUtils.convert(param, ${entity}.class);
        CopyUtils.copyUpdateParam(entity,"updateUser");
        UpdateWrapper<${entity}> updateWrapper = new UpdateWrapper<>();
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
    public ${entity}Vo detail(String primaryKey){
        ${entity} entity = this.getOne(new QueryWrapper<${entity}>().eq("primary_key", primaryKey));
        if(entity != null){
            return CopyUtils.convert(entity,${entity}Vo.class);
        }

        throw new RuntimeException("数据不存在");
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
        .le(param.getLe${field.propertyName?cap_first}() != null, ${entity}::get${field.propertyName?cap_first},param.getLe${field.propertyName?cap_first}())
                .ge(param.getGe${field.propertyName?cap_first}() != null, ${entity}::get${field.propertyName?cap_first},param.getGe${field.propertyName?cap_first}())
            </#if>
            <#if "${field.propertyType}"?contains("BigDecimal")>
        .le(param.getLe${field.propertyName?cap_first}() != null, ${entity}::get${field.propertyName?cap_first},param.getLe${field.propertyName?cap_first}())
                .ge(param.getGe${field.propertyName?cap_first}() != null, ${entity}::get${field.propertyName?cap_first},param.getGe${field.propertyName?cap_first}())
            </#if>
            <#if "${field.propertyType}"?contains("String")>
        .like(param.getLike${field.propertyName?cap_first}() != null, ${entity}::get${field.propertyName?cap_first},param.getLike${field.propertyName?cap_first}())
            </#if>
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

        return listVo;
    }

    @Override
    public String batchCreate(${entity}BatchCreateParam param) {
        List<${entity}> list = CopyUtils.convertList(param.getList(), ${entity}CreateParam.class, ${entity}.class);

        list.forEach(item -> {
            String primaryKey = item.getPrimaryKey();
            CopyUtils.copyCreateParam(item,null);
            if (StringUtils.isNotEmpty(primaryKey)){
                item.setPrimaryKey(primaryKey);
            }
        });

        if (this.saveBatch(list)){
            return "保存成功";
        }

        throw new RuntimeException("保存失败");
    }
}
</#if>
