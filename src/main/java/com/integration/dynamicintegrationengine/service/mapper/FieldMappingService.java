package com.integration.dynamicintegrationengine.service.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.integration.dynamicintegrationengine.dto.UserDTO;
import com.integration.dynamicintegrationengine.entity.FieldMapping;
import com.integration.dynamicintegrationengine.utils.JsonPathEvaluator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldMappingService {
    public UserDTO normalize(JsonNode itemNode, List<FieldMapping> mappings){
        UserDTO user=new UserDTO();

        for(FieldMapping fieldMapping:mappings){
            String localField= fieldMapping.getLocalField();
            String remotePath=fieldMapping.getRemotePath();

            JsonNode valueNode= JsonPathEvaluator.eval(itemNode,remotePath);

            if(valueNode==null)  continue;
            switch (localField) {
                case "email":
                    user.setEmail(valueNode.asText());
                    break;
                case "fullName":
                    user.setFullName(valueNode.asText());
                    break;
                default:
                    break;
            }
        }
        return user;
    }
}
