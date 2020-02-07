package cn.seeyoui.mp.generator.template.service.impl;

import cn.seeyoui.mp.generator.application.entity.TblApplication;
import cn.seeyoui.mp.generator.application.entity.TblApplicationSchema;
import cn.seeyoui.mp.generator.application.entity.TblApplicationTable;
import cn.seeyoui.mp.generator.application.service.TblApplicationSchemaService;
import cn.seeyoui.mp.generator.application.service.TblApplicationService;
import cn.seeyoui.mp.generator.application.service.TblApplicationTableService;
import cn.seeyoui.mp.generator.common.utils.CollectionsUtils;
import cn.seeyoui.mp.generator.template.param.GenerateParam;
import cn.seeyoui.mp.generator.template.param.Schema;
import cn.seeyoui.mp.generator.template.param.Table;
import cn.seeyoui.mp.generator.template.service.GenerateService;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GenerateServiceImpl implements GenerateService {

    @Value("${generator.path.temp}")
    private String fileTemp;

    @Value("${spring.datasource.dynamic.datasource.master.url}")
    private String DATASOURCE_URL = "url";

    @Value("${spring.datasource.dynamic.datasource.master.password}")
    private String DATASOURCE_PASSWORD = "password";

    @Value("${spring.datasource.dynamic.datasource.master.username}")
    private String DATASOURCE_USER_NAME = "userName";

    @Value("${spring.datasource.dynamic.datasource.master.driver-class-name}")
    private String DATASOURCE_DRIVER_NAME = "driverName";

    @Autowired
    private TblApplicationService tblApplicationService;
    @Autowired
    private TblApplicationSchemaService tblApplicationSchemaService;
    @Autowired
    private TblApplicationTableService tblApplicationTableService;

    @Override
    public Boolean generate(String applicationPrimaryKey, HttpServletResponse response, HttpServletRequest request) {
        QueryWrapper<TblApplication> queryWrapperApplication = new QueryWrapper<>();
        queryWrapperApplication.eq("primary_key",applicationPrimaryKey);
        TblApplication tblApplicationPo = tblApplicationService.getOne(queryWrapperApplication);

        QueryWrapper<TblApplicationSchema> queryWrapperSchema = new QueryWrapper<>();
        queryWrapperSchema.eq("application_primary_key",applicationPrimaryKey);
        List<TblApplicationSchema> schemaList = tblApplicationSchemaService.list(queryWrapperSchema);

        QueryWrapper<TblApplicationTable> queryWrapperTable = new QueryWrapper<>();
        queryWrapperTable.eq("application_primary_key",applicationPrimaryKey);
        List<TblApplicationTable> applicationTablePoList = tblApplicationTableService.list(queryWrapperTable);
        Map<String, List<TblApplicationTable>> tableMap = applicationTablePoList.stream()
                .collect(Collectors.groupingBy(TblApplicationTable::getSchemaPrimaryKey));

        GenerateParam generateParam = new GenerateParam();
        // 应用信息
        generateParam.setApplicationName(tblApplicationPo.getApplicationName());
        generateParam.setDescription(tblApplicationPo.getDescription());
        generateParam.setDatabaseName(tblApplicationPo.getDatabaseName());
        generateParam.setPackagePath(tblApplicationPo.getPackagePath());
        generateParam.setApplicationName(tblApplicationPo.getApplicationName());
        generateParam.setGroupId(tblApplicationPo.getGroupId());
        generateParam.setArtifactId(tblApplicationPo.getArtifactId());

        // 数据库连接
        generateParam.setDatasourceUrl(DATASOURCE_URL);
        generateParam.setDatasourceUsername(DATASOURCE_USER_NAME);
        generateParam.setDatasourcePassword(DATASOURCE_PASSWORD);
        generateParam.setDatasourceDriverClass(DATASOURCE_DRIVER_NAME);

        generateParam.setSchemaList(schemaList.stream().map(applicationSchema->{
            Schema schema = new Schema();
            schema.setModuleName(applicationSchema.getSchemaName());
            // 不为空才操作
            Optional.ofNullable(tableMap.get(applicationSchema.getPrimaryKey())).ifPresent(
                    list-> schema.setTableList(list.stream().map(
                            table->{
                                Table t = new Table();
                                t.setPath(table.getPath());
                                t.setTableName(table.getTableName());
                                return t;
                            }
                    ).collect(Collectors.toList())));
            return schema;
        }).collect(Collectors.toList()));

        createCode(generateParam);
        return true;
    }

    /**
     * 生成代码
     *
     */
    private void createCode(GenerateParam generateParam) {
        String projectPath = fileTemp + "/" + System.currentTimeMillis();
        for (int i = 0; i < generateParam.getSchemaList().size(); i++) {
            Schema item = generateParam.getSchemaList().get(i);
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();
            String author = generateParam.getAuthor();

            //全局配置
            GlobalConfig gc = buildGlobalConfig(author, projectPath);
            mpg.setGlobalConfig(gc);

            //数据库配置
            DataSourceConfig ds = buildDataSource(generateParam.getDatasourceUrl(), generateParam.getDatasourceDriverClass(),
                    generateParam.getDatasourceUsername(), generateParam.getDatasourcePassword());
            mpg.setDataSource(ds);

            // 模板信息
            TemplateConfig templateConfig = buildTemplateConfig(generateParam);
            mpg.setTemplate(templateConfig);
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
            String moduleRootPath = generateParam.getPackagePath();

            // 自定义配置
            InjectionConfig ic = buildInjectionConfig(generateParam,projectPath, item.getModuleName(),i);
            mpg.setCfg(ic);

            //包配置
            PackageConfig pc = buildPageConfig(item.getModuleName(), moduleRootPath);
            mpg.setPackageInfo(pc);

            //策略配置
            List<String> tables = CollectionsUtils.getListProperty(item.getTableList(), Table::getTableName);
            String[] arr = new String[tables.size()];
            tables.toArray(arr);
            StrategyConfig sc = buildStrategy(moduleRootPath,arr);
            mpg.setStrategy(sc);
            mpg.execute();
        }
    }

    /**
     * 全局配置
     *
     * @param author
     * @param projectPath
     * @return
     */
    private GlobalConfig buildGlobalConfig(String author, String projectPath) {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(Boolean.FALSE);
        gc.setSwagger2(Boolean.TRUE);
        gc.setActiveRecord(Boolean.FALSE);
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        gc.setEntityName("%s");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        return gc;
    }

    /**
     * @param url
     * @param driverName
     * @param userName
     * @param password
     * @return
     */
    private DataSourceConfig buildDataSource(String url, String driverName, String userName, String password) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName(driverName);
        return dsc;
    }

    /**
     * 自定义配置
     *
     * @param projectPath
     * @param moduleName
     * @return
     */
    private InjectionConfig buildInjectionConfig(GenerateParam generateParam,String projectPath, String moduleName,int index) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("springVersion", generateParam.getSpringVersion());
                map.put("applicationName", generateParam.getApplicationName());
                map.put("groupId", generateParam.getGroupId());
                map.put("artifactId", generateParam.getArtifactId());
                map.put("version", generateParam.getVersion());
                map.put("packageRootPath", generateParam.getPackagePath());
                map.put("databaseName", generateParam.getDatabaseName());
                map.put("description", generateParam.getDescription());
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" + moduleName
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        // 基础实体类
        focList.add(new FileOutConfig("/templates/baseEntity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                        + "/" + moduleName + "/entity/bo/Base" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });

        // 新增实体类
        focList.add(new FileOutConfig("/templates/createParam.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                        + "/" + moduleName + "/entity/param/" + tableInfo.getEntityName() + "CreateParam" + StringPool.DOT_JAVA;
            }
        });

        // 更新实体类
        focList.add(new FileOutConfig("/templates/updateParam.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                        + "/" + moduleName + "/entity/param/" + tableInfo.getEntityName() + "UpdateParam" + StringPool.DOT_JAVA;
            }
        });

        // 详情实体类
        focList.add(new FileOutConfig("/templates/detailVo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                        + "/" + moduleName + "/entity/vo/" + tableInfo.getEntityName() + "Vo" + StringPool.DOT_JAVA;
            }
        });

        // 列表入参实体类
        focList.add(new FileOutConfig("/templates/listParam.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                        + "/" + moduleName + "/entity/param/" + tableInfo.getEntityName() + "ListParam" + StringPool.DOT_JAVA;
            }
        });

        // 列表出参实体类
        focList.add(new FileOutConfig("/templates/listVo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                        + "/" + moduleName + "/entity/vo/" + tableInfo.getEntityName() + "ListVo" + StringPool.DOT_JAVA;
            }
        });

        // 列表出参实体类
        focList.add(new FileOutConfig("/templates/listItemVo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                        + "/" + moduleName + "/entity/vo/" + tableInfo.getEntityName() + "ListItemVo" + StringPool.DOT_JAVA;
            }
        });

        // 只生成一遍的文件
        if(index == 0){
            // pom.xml
            focList.add(new FileOutConfig("/templates/pom.xml.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/pom" + StringPool.DOT_XML;
                }
            });

            // SwaggerConfig.java
            focList.add(new FileOutConfig("/templates/swaggerConfig.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                            + "/common/config/SwaggerConfig" + StringPool.DOT_JAVA;
                }
            });

            // MybatisPlusConfig.java
            focList.add(new FileOutConfig("/templates/mybatisPlusConfig.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                            + "/common/config/MybatisPlusConfig" + StringPool.DOT_JAVA;
                }
            });

            // CollectionsUtils.java
            focList.add(new FileOutConfig("/templates/collectionsUtils.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                            + "/common/utils/CollectionsUtils" + StringPool.DOT_JAVA;
                }
            });

            // ClassUtils.java
            focList.add(new FileOutConfig("/templates/classUtils.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                            + "/common/utils/ClassUtils" + StringPool.DOT_JAVA;
                }
            });

            // CopyUtils.java
            focList.add(new FileOutConfig("/templates/copyUtils.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                            + "/common/utils/CopyUtils" + StringPool.DOT_JAVA;
                }
            });

            // UUIDUtils.java
            focList.add(new FileOutConfig("/templates/uUIDUtils.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                            + "/common/utils/UUIDUtils" + StringPool.DOT_JAVA;
                }
            });

            // Application.java
            focList.add(new FileOutConfig("/templates/application.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                            + "/" + generateParam.getApplicationName() + "Application" + StringPool.DOT_JAVA;
                }
            });

            // applicationProperties
            focList.add(new FileOutConfig("/templates/applicationProperties.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/resources/application.properties";
                }
            });

            // QueryListVo
            focList.add(new FileOutConfig("/templates/queryListVo.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                            + "/common/model/vo/QueryListVo" + StringPool.DOT_JAVA;
                }
            });

            // BaseResponseVo
            focList.add(new FileOutConfig("/templates/baseResponseVo.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                            + "/common/model/vo/BaseResponseVo" + StringPool.DOT_JAVA;
                }
            });

            // BaseController
            focList.add(new FileOutConfig("/templates/baseController.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/java/" + getRelativeFilePathByPackagePath(generateParam.getPackagePath())
                            + "/common/model/controller/BaseController" + StringPool.DOT_JAVA;
                }
            });
        }
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 包配置
     *
     * @param moduleName
     * @param moduleRootPath
     * @return
     */
    private PackageConfig buildPageConfig(String moduleName, String moduleRootPath) {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(moduleRootPath);
        return pc;
    }

    /**
     * 配置策略
     *
     * @param table
     * @return
     */
    private StrategyConfig buildStrategy(String packageRootPath,String... table) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(table);
        strategy.setEntityLombokModel(Boolean.TRUE);
        strategy.setEntityBuilderModel(Boolean.TRUE);
        strategy.setRestControllerStyle(Boolean.TRUE);
        strategy.setEntityColumnConstant(Boolean.FALSE);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperControllerClass(packageRootPath + ".common.model.controller.BaseController");
        return strategy;
    }

    /**
     * 模板信息
     * @param generateParam
     * @return
     */
    private TemplateConfig buildTemplateConfig(GenerateParam generateParam){
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setService("templates/service.java");
        templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setController("templates/controller.java");
        templateConfig.setXml(null);
        return templateConfig;
    }

    private String getRelativeFilePathByPackagePath(String packagePath) {
        return packagePath.replaceAll("\\.", "/");
    }
}
