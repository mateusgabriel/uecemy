package br.uece.spring.api.usuario;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.*;

import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;

@Configuration
@EnableRabbit
@EnableDiscoveryClient
@SpringBootApplication
public class UsuarioApplication {

	@Bean
	public OtlpGrpcSpanExporter otlpHttpSpanExporter(@Value("${tracing.url}") String url) {
		return OtlpGrpcSpanExporter.builder().setEndpoint(url).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(UsuarioApplication.class, args);
	}
}