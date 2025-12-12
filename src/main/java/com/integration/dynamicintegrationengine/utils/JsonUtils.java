package com.integration.dynamicintegrationengine.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.Map;

public class JsonUtils {
    private static final ObjectMapper mapper=new ObjectMapper();
    public static Map<String,String> toMap(String json){
        try{
            if(json==null) return Collections.emptyMap();
            return mapper.readValue(json, new TypeReference<Map<String, String>>() {});
        }catch (Exception e){
            return Collections.emptyMap();
        }
    }
}
