package br.uece.spring.api.pagamento;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;

@Configuration
@EnableRabbit
@EnableFeignClients(basePackages = {"br.uece.spring.api.pagamento.controller", "br.uece.spring.api.pagamento.application.interfaces"})
@EnableDiscoveryClient
@SpringBootApplication
public class PagamentoApplication {

	@Bean
	public OtlpGrpcSpanExporter otlpHttpSpanExporter(@Value("${tracing.url}") String url) {
		return OtlpGrpcSpanExporter.builder().setEndpoint(url).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(PagamentoApplication.class, args);
	}

}