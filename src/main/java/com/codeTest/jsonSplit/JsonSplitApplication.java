package com.codeTest.jsonSplit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JsonSplitApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonSplitApplication.class, args);
	}

}
