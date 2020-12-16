package com.mdt.architecture.applications.configuration;

import javax.sql.DataSource;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ShedLockConfig {

  @Bean("lockProvider")
  public LockProvider lockProvider(DataSource dataSource) {
    return new JdbcTemplateLockProvider(
            JdbcTemplateLockProvider.Configuration.builder()
                    .withJdbcTemplate(new JdbcTemplate(dataSource))
                    .usingDbTime()
                    .build()
        );
  }
}
