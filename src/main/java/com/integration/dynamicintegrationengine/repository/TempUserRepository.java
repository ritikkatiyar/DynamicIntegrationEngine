package com.integration.dynamicintegrationengine.repository;

import com.integration.dynamicintegrationengine.entity.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempUserRepository extends JpaRepository<TempUser,Long> {
}
