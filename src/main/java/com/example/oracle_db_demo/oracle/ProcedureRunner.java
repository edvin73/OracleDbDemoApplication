package com.example.oracle_db_demo.oracle;

import java.sql.Types;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProcedureRunner { 
	
	private final JdbcTemplate jdbcTemplate;
	 
	
	public ProcedureRunner(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate; 
	}
	
	public void runProcedure(String procedureName) {
		String sql = "{call " + procedureName + "()}";
		jdbcTemplate.execute(sql);
	}
	
	
	public String procedureRunner( String procedureName) {
			
			log.info("Executing stored procedure: {}", procedureName);
			 
			 SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
			 jdbcCall.withProcedureName(procedureName)
			 		.declareParameters(new SqlOutParameter("v_date", Types.VARCHAR));
			 
			 log.info("Calling procedure...");
			 
			 Map<String, Object> out = jdbcCall.execute();
			 
			 log.info("Procedure executed. Output: {}", out);
			 
			 String currentDate = (String) out.get("v_date");
			 
			 return currentDate;
    }
}
