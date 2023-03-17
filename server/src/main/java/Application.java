import dao.TriangleDao;
import dao.TriangleProvider;
import dao.impl.TriangleDaoImpl;
import dao.impl.TriangleProviderImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import service.TriangleService;
import service.TriangleValidateService;
import service.impl.TriangleServiceImpl;
import service.impl.TriangleValidateServiceImpl;

@SpringBootApplication
@ComponentScan("controller")
public class Application {
	@Bean
	public static TriangleDao triangleDao() {
		return new TriangleDaoImpl();
	}

	@Bean
	public static TriangleProvider triangleProvider(TriangleDao triangleDao) {
		return new TriangleProviderImpl(triangleDao);
	}

	@Bean
	public static TriangleService triangleService(TriangleDao triangleDao) {
		return new TriangleServiceImpl(triangleDao);
	}

	@Bean
	public static TriangleValidateService triangleValidateService(TriangleProvider triangleProvider, TriangleService triangleService) {
		return new TriangleValidateServiceImpl(triangleProvider, triangleService);
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
