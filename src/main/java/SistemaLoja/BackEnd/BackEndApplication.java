package SistemaLoja.BackEnd;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackEndApplication {
	public static void main(String[] args) {
		try {
			Dotenv dotenv = Dotenv.load();

			dotenv.entries().forEach(entry ->
					System.setProperty(entry.getKey(), entry.getValue())
			);
			System.out.println("Variáveis do .env carregadas com sucesso na JVM.");
		} catch (Exception e) {
			System.out.println("Arquivo .env não encontrado. Utilizando variáveis de ambiente do sistema ou application.properties.");
		}
		SpringApplication.run(BackEndApplication.class, args);
	}
	/*
	 * Swagger apenas disponível em ambiente de homologação e de desenvolvimento!!!
	 */
}
