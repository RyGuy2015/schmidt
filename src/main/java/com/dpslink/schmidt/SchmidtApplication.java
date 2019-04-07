
package com.dpslink.schmidt;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SchmidtApplication {
	
	@Autowired
	DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(SchmidtApplication.class, args);
	}

}
