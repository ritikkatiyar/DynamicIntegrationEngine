package com.integration.dynamicintegrationengine.utils;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonPathEvaluator {
    public static JsonNode eval(JsonNode node,String path){
        try{
            String[] parts=path.split("\\.");
            JsonNode current=node;

            for(String part:parts){
                if(part.endsWith("[]")){
                    part=part.replace("[]","");
                    current=current.get(part).get(0);
                }else{
                    current=current.get(part);
                }
                if(current==null) return null;
            }
            return current;
        }catch (Exception e){
            return null;
        }
    }
}
