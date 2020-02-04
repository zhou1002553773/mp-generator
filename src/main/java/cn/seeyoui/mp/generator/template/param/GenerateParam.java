package cn.seeyoui.mp.generator.template.param;

import lombok.Data;

import java.util.List;

@Data
public class GenerateParam {

    private String author = "author";

    /* applicationConfig */
    private String springVersion = "2.1.3.RELEASE";
    private String version = "0.0.1-SNAPSHOT";
    private String description = "Project of China Visionary";
    private String jdkVersion = "1.8";
    private String groupId = "groupId";
    private String artifactId = "artifactId";

    private String databaseName;
    private String projectPath;
    private String applicationName;
    private String packagePath;

    /* jdbcConnection */
    private String datasourceUrl;
    private String datasourceUsername;
    private String datasourcePassword;
    private String datasourceDriverClass;

    /* generatorInformation */
    private List<Schema> schemaList;
}
