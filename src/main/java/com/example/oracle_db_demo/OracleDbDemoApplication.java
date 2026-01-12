package com.example.oracle_db_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.oracle_db_demo.oracle.ProcedureRunner;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class OracleDbDemoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(OracleDbDemoApplication.class, args);
		
		ProcedureRunner procedureRunner = ctx.getBean(ProcedureRunner.class);
		
		// Example of running a stored procedure named "MY_PROCEDURE"
		String value = procedureRunner.procedureRunner("PS_GET_CURRECT_DATE");
		
		log.info("Returned value from procedure: {}", value);
	}

}
