package com.integration.dynamicintegrationengine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "apps")
@Data
public class AppConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;//calendly,dropbox etc
    private String baseUrl;
    private String authType;//api-key,oauth2
    private String apiKey;//for simple use cases
}
