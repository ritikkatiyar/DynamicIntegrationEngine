package com.integration.dynamicintegrationengine.service.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.dynamicintegrationengine.entity.AppConfig;
import com.integration.dynamicintegrationengine.entity.EndpointConfig;
import com.integration.dynamicintegrationengine.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GenericApiCallerService {
    private final WebClient.Builder webclientBuilder;
    private final ObjectMapper mapper;

    public JsonNode call(AppConfig app, EndpointConfig endpoint, Map<String,String> queryParams){

        WebClient client=webclientBuilder.build();
        String url=buildUrl(app.getBaseUrl(),endpoint.getPath(),queryParams);

        //parse header from Json stored in db
        Map<String,String> headersMap= JsonUtils.toMap(endpoint.getHeaders());

        //add authorization header
        if("API_KEY".equalsIgnoreCase(app.getAuthType())){
            headersMap.put("Authorization", "Bearer " + app.getApiKey());
        }

        Mono<String> response=client.method(endpoint.getMethod().equals("POST")?
                org.springframework.http.HttpMethod.POST : org.springframework.http.HttpMethod.GET)
                .uri(url)
                .headers(h -> headersMap.forEach(h::add))
                .retrieve()
                .bodyToMono(String.class);

        String respStr=response.block();

        try{
            return mapper.readTree(respStr);
        }catch (Exception e){
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }
    private String buildUrl(String baseUrl,String path,Map<String,String> queryParams){
        StringBuilder sb=new StringBuilder(baseUrl).append(path);
        if(queryParams!=null && !queryParams.isEmpty()){
            sb.append("?");
            queryParams.forEach((k,v)->sb.append("=").append(v).append("&"));
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }
}
