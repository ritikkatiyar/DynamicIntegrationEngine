package com.integration.dynamicintegrationengine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "endpoints")
@Data
public class EndpointConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long appId;
    private String name;     //list_users
    private String method;   //Get or post
    private String path;//   /users
    private String headers;   //jsonstring
    private String queryParams;//json string
    private String pagination;//json string
}
