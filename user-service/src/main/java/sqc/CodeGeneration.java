package sqc;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import sqc.entity.po.BasePO;

import java.util.*;

/**
 * @Description: mybatis-plus，MySQL表 代码生成器。
 * 运行CodeGeneration，输入模块名称（会是一个包，然后其他生成的文件都在这个下面。如果生成了Controller，那@RequestMapping，就会带上这个模块名）
 * 输入表明，多个可用“,”逗号分隔，当然啦，需要设置。strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
 * @author: Sqcode
 * @since: 2021/4/28 15:15
 */
public class CodeGeneration {

    // 代码生成器
    private static final AutoGenerator mpg = new AutoGenerator();
    // 模块路径
    private static final String projectPath = System.getProperty("user.dir") + "/user-service";
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 数据源配置
     */
    private static void dataSourceConfig () {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/sqc?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);
    }

    /**
     * 包配置，设置一些包，（templateConfig中可以自动生成一些类）
     */
    private static PackageConfig packageConfig () {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("sqc");// bao ming
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);
        return pc;
    }

    /**
     * 自定义配置，这里将mapper.xml放在resource下面
     */
    private static void injectionConfig () {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            // 自定义属性注入: abc
            // 在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
//                        + pc.getModuleName() + "/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }

    /**
     * 配置模板，是否需要一些标准类的生成
     */
    private static void templateConfig () {
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");

        /**
         * null, 即不生成
         */
        templateConfig.setXml(null);
//        templateConfig.setController(null);
//        templateConfig.setService(null);
//        templateConfig.setServiceImpl(null);
//        templateConfig.setEntity(null);
//        templateConfig.setMapper(null);
        mpg.setTemplate(templateConfig);
    }

    /**
     * 策略配置，可以多表生成，类名，父类继承等等
     */
    private static void strategyConfig (PackageConfig pc) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //生成表
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(BasePO.class);// 你自己的父类实体,没有就不用设置!
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
    }

    public static void main(String[] args) {
        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Sqcode");
        gc.setOpen(false);
        gc.setSwagger2(true);// 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        dataSourceConfig();// 数据库配置
        PackageConfig pc = packageConfig();// 包的配置
        injectionConfig();// 自定义的配置，例如把mapper放在resource下面
        templateConfig();// 模板的配置，例一些controller/impl是否也要自动生成
        strategyConfig(pc);// 可以多表生成，类名，父类继承等等
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
