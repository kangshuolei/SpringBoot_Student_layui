package com.xiaokang.login_MP.tset;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class MpGenerator {

	@Test
	public void testGenerator(){
        //1.全局配置  
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)    //是否支持AR模式
                .setAuthor("康硕雷")  //作者
                .setOutputDir("G:\\EclipseWorkspace\\workspace\\userLogin_MP\\src\\main\\java")  //生成路径
                .setFileOverride(true)  //文件覆盖
                .setIdType(IdType.AUTO)     //主键策略
                .setServiceName("%sService")    //设置生成的service接口的名字的首字母是否为I
                .setBaseResultMap(true)     //xml映射文件的配置
                .setBaseColumnList(true);   //xml映射文件的配置
        //2.数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)  //设置数据库类型
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/login")
                .setUsername("root")
                .setPassword("123456");
        //3.策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true)  //开启全局大写命名
                .setTablePrefix("")
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude("c_s_relation");//生成的表
        //4.包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.xiaokang.login_MP")
                .setMapper("dao")
                .setService("service")
                .setController("controllor")
                .setEntity("bean")
                .setXml("dao");
        //5.整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);
        //6.执行
        ag.execute();
    }
}
