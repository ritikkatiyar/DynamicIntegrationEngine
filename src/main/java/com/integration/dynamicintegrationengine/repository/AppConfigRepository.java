package com.integration.dynamicintegrationengine.repository;

import com.integration.dynamicintegrationengine.entity.AppConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppConfigRepository extends JpaRepository<AppConfig,Long> {
}
