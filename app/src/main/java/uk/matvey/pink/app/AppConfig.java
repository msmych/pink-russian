package uk.matvey.pink.app;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.matvey.pink.freezer.Repo;

@Configuration
public class AppConfig {

    @Bean
    public Repo repo(@Value("${spring.datasource.url}") String dsUrl,
                     @Value("${spring.datasource.driverClassName}") String driver,
                     @Value("${spring.datasource.username}") String username,
                     @Value("${spring.datasource.password}") String password) {
        final var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dsUrl);
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        return new Repo(hikariConfig.getDataSource());
    }
}
