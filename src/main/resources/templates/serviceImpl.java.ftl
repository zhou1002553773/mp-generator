package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Entity}.param.${entity}CreateParam;
import ${package.Entity}.param.${entity}UpdateParam;
import ${package.Entity}.param.${entity}ListParam;
import ${package.Entity}.vo.${entity}Vo;
import ${package.Entity}.vo.${entity}ListVo;
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

          //param check

          //copy

          return null;
     }

     @Override
     public String update(${entity}UpdateParam param){

          return null;
     }

     @Override
     public String logicDelete(String primaryKey){
         return null;
     }

     @Override
     public ${entity}Vo detail(String primaryKey){
          return null;
     }

     @Override
     public ${entity}ListVo list(${entity}ListParam param){
        return null;
     }
}
</#if>
