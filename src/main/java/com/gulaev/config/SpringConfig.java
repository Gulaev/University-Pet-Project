package com.gulaev.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@ComponentScan("com.gulaev")
@PropertySource("classpath:SQL/database.properties")
public class SpringConfig {

  private final ApplicationContext applicationContext;

  @Autowired
  public SpringConfig(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName("org.postgresql.Driver");
    dataSource.setUrl("jdbc:postgresql://localhost:5432/task10");
    dataSource.setUsername("denisgulaev");
    dataSource.setPassword("5262618z");

    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  public DataSourceInitializer dataSourceInitializer(
      @Qualifier("dataSource") final DataSource dataSource) {
    ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
    resourceDatabasePopulator.addScript(new ClassPathResource("/SQL/create_all_table_SQL.sql"));
    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
    dataSourceInitializer.setDataSource(dataSource);
    dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
    return dataSourceInitializer;
  }
}
