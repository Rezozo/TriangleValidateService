package config;

import dao.TriangleProvider;
import repository.TriangleRepository;
import dao.impl.TriangleProviderImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import service.TriangleService;
import service.TriangleValidateService;
import service.impl.TriangleServiceImpl;
import service.impl.TriangleValidateServiceImpl;
@Configuration
@EnableJpaRepositories(basePackages = "repository")
@EntityScan(basePackages = "model")
public class ApplicationConfig {
    @Bean
    public static TriangleProvider triangleProvider(TriangleRepository triangleRepository) {
        return new TriangleProviderImpl(triangleRepository);
    }

    @Bean
    public static TriangleService triangleService(TriangleProvider triangleProvider) {
        return new TriangleServiceImpl(triangleProvider);
    }

    @Bean
    public static TriangleValidateService triangleValidateService(TriangleProvider triangleProvider, TriangleService triangleService) {
        return new TriangleValidateServiceImpl(triangleProvider, triangleService);
    }
}
