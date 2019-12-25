package com.xiaokang.login_MP;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan(value = "com.xiaokang.login_MP.dao")
@EnableSwagger2
@EnableSwaggerBootstrapUI
@SpringBootApplication
public class LoginMpApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginMpApplication.class, args);
	}

}
