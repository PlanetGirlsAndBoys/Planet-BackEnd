package com.spaceX.spaceX.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dd7uxgv7z",
            "api_key", "673231199637639",
            "api_secret", "15KKnytnzMkzfjiEjnuosfigFI"
        ));
    }
} 