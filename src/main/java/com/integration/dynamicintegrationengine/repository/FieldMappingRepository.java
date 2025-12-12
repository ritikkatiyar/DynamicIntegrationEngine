package com.integration.dynamicintegrationengine.repository;

import com.integration.dynamicintegrationengine.entity.FieldMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldMappingRepository extends JpaRepository<FieldMapping,Long> {
    List<FieldMapping> findByEndpointId(Long endPointId);
}
