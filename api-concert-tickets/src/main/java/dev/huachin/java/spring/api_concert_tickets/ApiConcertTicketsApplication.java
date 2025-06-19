package dev.huachin.java.spring.api_concert_tickets;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
// @SpringBootApplication es una anotación que combina @Configuration, @EnableAutoConfiguration y @ComponentScan
@SpringBootApplication
public class ApiConcertTicketsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiConcertTicketsApplication.class, args);
	}

	@Bean
	public String apiVersion() {
		return "1.0.0";
	}

	// @Bean es una anotación que indica que un método produce un bean que debe ser gestionado por el contenedor de Spring.
	// @Bean se usa para definir un bean en el contexto de la aplicación.
	// el método customOpenApi devuelve una instancia de OpenAPI que describe la API. y se registra como un bean en el contexto de la aplicación. y se ejecuta al iniciar la aplicación.
	// Un bean es un objeto que es instanciado, ensamblado y gestionado por el contenedor de Spring.
	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
			.info(new Info()
				.title("API de Entradas de Conciertos")
				.version(apiVersion())
				.summary("API para gestionar entradas de conciertos")
				.description("Esta API permite gestionar entradas de conciertos, géneros musicales y artistas.")
				.termsOfService("https://example.com/terms"));
	}

	@Override
	public void run(String... args) throws Exception {
		// Este método se ejecuta al iniciar la aplicación.
		log.info("API de Entradas de Conciertos iniciada correctamente...");
	}
}
