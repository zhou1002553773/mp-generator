package cn.seeyoui.mp.generator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CodeGenerator {

    private final static String AUTHOR = "author";

    private final static String URL = "url";

    private final static String TABLE = "table";

    private final static String PASSWORD = "password";

    private final static String USER_NAME = "userName";

    private final static String MODULE_NAME = "moduleName";

    private final static String DRIVER_NAME = "driverName";

    private final static String MODULE_ROOT_PATH = "rootPath";

    private final static String TABLE_MODULE_MAPPING = "mapping";

    public static void main(String[] args) {
        //代码自动生成
        Map configData;
        try {
            ObjectMapper mapper = new ObjectMapper();
            configData = mapper.readValue(ResourceUtils.
                    getFile("classpath:generateCfg.json"), Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Parse config generate file failure");
        }

        List<Map<String, Object>> listMapping = (List<Map<String, Object>>) configData.get(TABLE_MODULE_MAPPING);
        for (Map<String, Object> obj : listMapping) {
            String moduleName = obj.get(MODULE_NAME).toString();
            List<String> tables = (List<String>) obj.get(TABLE);
            String[] arr = new String[tables.size()];
            tables.toArray(arr);
            createCode(configData, moduleName, arr);
        }
    }

    /**
     * 生成代码
     *
     * @param configData
     * @param moduleName
     * @param tables
     */
    private static void createCode(Map<String, Object> configData, String moduleName, String[] tables) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        String projectPath = "/Users/zyh/develop/file/code";
        String author = configData.get(AUTHOR).toString();
        //全局配置
        GlobalConfig gc = buildGlobalConfig(author, projectPath);
        mpg.setGlobalConfig(gc);
        //数据库配置
        String url = configData.get(URL).toString();
        String password = configData.get(PASSWORD).toString();
        String userName = configData.get(USER_NAME).toString();
        String driverName = configData.get(DRIVER_NAME).toString();
        DataSourceConfig ds = buildDataSource(url, driverName, userName, password);
        mpg.setDataSource(ds);

        mpg.setTemplate(new TemplateConfig().setXml(null));
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        String moduleRootPath = configData.get(MODULE_ROOT_PATH).toString();

        InjectionConfig ic = buildInjectionConfig(projectPath, moduleName);
        mpg.setCfg(ic);
        //包配置
        PackageConfig pc = buildPageConfig(moduleName, moduleRootPath);
        mpg.setPackageInfo(pc);
        //策略配置
        StrategyConfig sc = buildStrategy(tables);
        mpg.setStrategy(sc);
        mpg.execute();
    }

    /**
     * 全局配置
     *
     * @param author
     * @param projectPath
     * @return
     */
    private static GlobalConfig buildGlobalConfig(String author, String projectPath) {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(Boolean.FALSE);
        gc.setSwagger2(Boolean.TRUE);
        gc.setActiveRecord(Boolean.TRUE);
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        gc.setEntityName("%sPo");
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
    private static DataSourceConfig buildDataSource(String url, String driverName, String userName, String password) {
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
    private static InjectionConfig buildInjectionConfig(String projectPath, String moduleName) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
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
        focList.add(new FileOutConfig("/templates/PomTemplate.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/pom" + StringPool.DOT_XML;
            }
        });
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
    private static PackageConfig buildPageConfig(String moduleName, String moduleRootPath) {
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
    private static StrategyConfig buildStrategy(String... table) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(table);
        strategy.setEntityLombokModel(Boolean.TRUE);
        strategy.setEntityBuilderModel(Boolean.TRUE);
        strategy.setRestControllerStyle(Boolean.TRUE);
        strategy.setEntityColumnConstant(Boolean.TRUE);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        return strategy;
    }
}
