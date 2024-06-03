package egovframework.example.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EgovConfigDatasource {

	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean(name="dataSource")
	public DataSource dataSource() {
		
		return DataSourceBuilder.create().build();
	}

}
