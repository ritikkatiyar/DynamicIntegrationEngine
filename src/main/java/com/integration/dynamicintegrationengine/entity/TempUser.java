package com.integration.dynamicintegrationengine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "temp_users")
@Data
public class TempUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String fullName;
    @Column(columnDefinition = "TEXT")
    private String rawJson;//store original api responseitem
}
