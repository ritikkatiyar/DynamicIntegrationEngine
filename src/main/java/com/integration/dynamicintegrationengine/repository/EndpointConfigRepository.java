package com.integration.dynamicintegrationengine.repository;

import com.integration.dynamicintegrationengine.entity.EndpointConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EndpointConfigRepository extends JpaRepository<EndpointConfig,Long> {

    List<EndpointConfig> findByAppId(Long appId);
}
