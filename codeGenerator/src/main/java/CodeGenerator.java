import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @created 2020-09-19-13:37.
 */
public class CodeGenerator {

    /**
     * 数据库链接配置
     */
    private static final String MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/simple4j?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static final String MYSQL_DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String MYSQL_USERNAME = "simple4j";
    private static final String MYSQL_PASSWORD = "simple4j";

    /**
     * 作者
     */
    private static final String AUTHOR = "simple4j";
    /**
     * 项目工程包名
     */
    private static final String MODULE_PARENT = "multiModule_";
    private static final String MODULE = "manage";
    private static final String CONTROLLER_MODULE = MODULE_PARENT + MODULE + File.separator + MODULE + "_web";
    private static final String COMMON_MODULE = "multiModule_commons";
    private static final String DAO_MODULE = COMMON_MODULE + File.separator + "common-dao";
    private static final String SERVICE_MODULE = COMMON_MODULE + File.separator + "common-service";
    private static final String SERVICE_IMPL_MODULE = MODULE_PARENT + MODULE + File.separator + MODULE + "_service_impl";
    private static final String DOMAIN_MODULE = COMMON_MODULE + File.separator + "common-domain";
    /**
     * 模块包名
     */
    private static final String PACKAGE_MODULE = "sys";
    /**
     * 包路径
     */
    private static final String PACKAGE_PARENT = "com.simple4code.simple4j.demo" ;
    /**
     * 表的前缀
     */
    private static final String TABLE_PREFIX = "t_";


    /**
     * 生产代码
     *
     * @param args args
     */
    public static void main(String[] args) {
        // 执行
        getAutoGenerator("t_sys_user_role").execute();
    }

    /**
     * 全局配置
     *
     * @return 返回 GlobalConfig
     */
    private static GlobalConfig getGlobalConfig() {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir") + File.separator + MODULE + "/src/main/java");
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        //去除接口前面的I
        gc.setServiceName("%sService");
        gc.setFileOverride(true);
        gc.setIdType(IdType.AUTO);
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        return gc;
    }

    /**
     * 数据源配置
     *
     * @return 返回 getDataSourceConfig
     */
    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(MYSQL_URL);
        dsc.setDriverName(MYSQL_DRIVER_NAME);
        dsc.setUsername(MYSQL_USERNAME);
        dsc.setPassword(MYSQL_PASSWORD);
        return dsc;
    }

    /**
     * 包名配置
     *
     * @return 返回 PackageConfig
     */
    private static PackageConfig getPackageConfig() {
        String projectPath = System.getProperty("user.dir");
        String mavenPath = "\\src\\main\\java\\";
        PackageConfig pc = new PackageConfig();
        pc.setParent(PACKAGE_PARENT);
        //设置文件的包名
        pc.setModuleName(PACKAGE_MODULE);
        //pc.setEntity(PACKAGE_PARENT );
        //pc.setMapper(PACKAGE_PARENT );
        //设置不同类文件生成的路径
        String moduleName = pc.getModuleName();

        /**
         * packageInfo配置controller、service、serviceImpl、entity、mapper等文件的包路径
         * 这里包路径可以根据实际情况灵活配置
         */
        Map<String, String> packageInfo = new HashMap<>();

        packageInfo.put(ConstVal.CONTROLLER, PACKAGE_PARENT + File.separator + moduleName + ".controller");
        packageInfo.put(ConstVal.SERVICE, PACKAGE_PARENT + File.separator + moduleName + ".service");
        packageInfo.put(ConstVal.SERVICE_IMPL, PACKAGE_PARENT + File.separator + moduleName + ".service.impl");
        packageInfo.put(ConstVal.ENTITY, PACKAGE_PARENT + File.separator + moduleName + ".entity");
        packageInfo.put(ConstVal.MAPPER, PACKAGE_PARENT + File.separator + moduleName + ".mapper");

        Map pathInfo = new HashMap<>();
        pathInfo.put(ConstVal.CONTROLLER_PATH, projectPath + File.separator + CONTROLLER_MODULE + mavenPath + packageInfo.get(ConstVal.CONTROLLER).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.SERVICE_PATH, projectPath + File.separator + SERVICE_MODULE + mavenPath + packageInfo.get(ConstVal.SERVICE).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, projectPath + File.separator + SERVICE_IMPL_MODULE + mavenPath + packageInfo.get(ConstVal.SERVICE_IMPL).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.ENTITY_PATH, projectPath + File.separator + DOMAIN_MODULE + mavenPath + packageInfo.get(ConstVal.ENTITY).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.MAPPER_PATH, projectPath + File.separator + DAO_MODULE + mavenPath + packageInfo.get(ConstVal.MAPPER).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        //pathInfo.put(ConstVal.XML_PATH, projectPath + "\\src\\main\\resources\\mapper\\" + moduleName);
        pc.setPathInfo(pathInfo);
        return pc;
    }

    /**
     * 模板引擎配置
     *
     * @return 返回 TemplateConfig
     */
    private static TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        return templateConfig;
    }

    /**
     * 策略配置
     *
     * @param tableNames 表名称
     *                   strategy.setInclude(tableNames) 传入需要 "生成" 的表名
     *                   strategy.setExclude(tableNames) 传入需要 "过滤" 的表名
     *                   strategy.setSuperEntityColumns("id");
     * @return 返回 getStrategyConfig
     */
    private static StrategyConfig getStrategyConfig(String... tableNames) {
        StrategyConfig strategy = new StrategyConfig();
        //全局大写命名
        strategy.setCapitalMode(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tableNames);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(TABLE_PREFIX);
        return strategy;
    }

    /**
     * 自定义配置
     *
     * @return 返回 InjectionConfig
     */
    private static InjectionConfig getInjectionConfig() {

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        final String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return System.getProperty("user.dir") + File.separator + DAO_MODULE + "/src/main/resources/mapping/" + PACKAGE_MODULE + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);

        return cfg;
    }


    /**
     * 获取代码生成器
     *
     * @return 返回代码生成器
     */
    private static AutoGenerator getAutoGenerator(String... tableNames) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        mpg.setGlobalConfig(getGlobalConfig());

        // 数据源配置
        mpg.setDataSource(getDataSourceConfig());

        // 包配置
        mpg.setPackageInfo(getPackageConfig());

        // 自定义配置
        mpg.setCfg(getInjectionConfig());

        // 配置模板
        mpg.setTemplate(getTemplateConfig());

        // 策略配置
        mpg.setStrategy(getStrategyConfig(tableNames));

        // 设置模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        return mpg;
    }


}
