package com.integration.dynamicintegrationengine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "field_mappings")
@Data
public class FieldMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long endpointId;
    private String remotePath;    //eg->collection[].email
    private String localField;    //email
}
