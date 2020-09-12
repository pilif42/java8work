package com.philippe.app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter @Setter
@Configuration
@ConfigurationProperties(prefix = "products")
public class ProductsConfiguration {
    Map<String, Double> priceMap;
}
