package com.example.demo15.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private final Properties properties = new Properties();
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String profile = "mypropertie.properties";
        Resource resource = new ClassPathResource(profile);
        environment.getPropertySources().addLast(loadProfiles(resource));
    }

    private PropertySource<?> loadProfiles(Resource resource){
        if(resource.exists()){
            try {
                properties.load(resource.getInputStream());
                return new PropertiesPropertySource(resource.getFilename(),properties);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
