package com.philippe.app.config;

import com.philippe.app.service.avro.AvroCodecService;
import com.philippe.app.service.avro.AvroCodecServiceImpl;
import example.avro.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvroConfig {
    @Bean
    public AvroCodecService<User> avroCodecServiceForUsers() {
        return new AvroCodecServiceImpl<>(new example.avro.User());
    }
}
