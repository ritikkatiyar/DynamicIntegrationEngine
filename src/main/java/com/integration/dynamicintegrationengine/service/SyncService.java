package com.integration.dynamicintegrationengine.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.dynamicintegrationengine.dto.UserDTO;
import com.integration.dynamicintegrationengine.entity.AppConfig;
import com.integration.dynamicintegrationengine.entity.EndpointConfig;
import com.integration.dynamicintegrationengine.entity.FieldMapping;
import com.integration.dynamicintegrationengine.entity.TempUser;
import com.integration.dynamicintegrationengine.repository.AppConfigRepository;
import com.integration.dynamicintegrationengine.repository.EndpointConfigRepository;
import com.integration.dynamicintegrationengine.repository.FieldMappingRepository;
import com.integration.dynamicintegrationengine.repository.TempUserRepository;
import com.integration.dynamicintegrationengine.service.api.GenericApiCallerService;
import com.integration.dynamicintegrationengine.service.mapper.FieldMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SyncService {
    private final AppConfigRepository appRepo;
    private final EndpointConfigRepository endpointRepo;
    private final FieldMappingRepository mappingRepo;
    private final TempUserRepository tempUserRepo;

    private final GenericApiCallerService apiCaller;
    private final FieldMappingService fieldMapper;

    private final ObjectMapper objectMapper;
    public void syncCalendlyUsers() {

        // 1. Load app config
        AppConfig app = appRepo.findById(1L).orElseThrow();

        // 2. Load endpoint config
        EndpointConfig endpoint = endpointRepo.findById(1L).orElseThrow();

        // 3. Call external API
        JsonNode response = apiCaller.call(app, endpoint, null);

        // Calendly users → response.collection[]
        JsonNode collection = response.get("collection");

        if (collection == null || !collection.isArray()) {
            System.out.println("No users found in Calendly response");
            return;
        }

        // 4. Load field mappings
        List<FieldMapping> mappings = mappingRepo.findByEndpointId(endpoint.getId());

        // 5. Loop through users
        for (JsonNode item : collection) {

            // Normalize dynamic fields → UserDTO
            UserDTO userDTO = fieldMapper.normalize(item, mappings);

            // 6. Store into temp_users
            TempUser tempUser = new TempUser();
            tempUser.setEmail(userDTO.getEmail());
            tempUser.setFullName(userDTO.getFullName());
            tempUser.setRawJson(item.toString());

            tempUserRepo.save(tempUser);
        }

        System.out.println("Calendly user sync completed.");
    }

}
