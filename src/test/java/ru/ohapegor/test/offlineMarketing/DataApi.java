package ru.ohapegor.test.offlineMarketing;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class DataApi {

    private static final Logger LOG = LoggerFactory.getLogger(DataApi.class);

    private static final String ENDPOINT = "http://localhost:8080/CRM-DN/rest/v1/data/source/getAggregatedData";

    private static final String UPLOAD_ENDPOINT = "http://localhost:8080/CRM-DN/rest/v1/upload";

    private static final String EDIT_ENDPOINT = "http://localhost:8080/CRM-DN/rest/v1/edit";

    private static final RestTemplate restTemplate = new RestTemplate();

    public Map<String, String> aggreagateData(AggregateRequest request) {
        LOG.info(request.toString());
        return restTemplate.postForEntity(ENDPOINT, request, Map.class).getBody();
    }

    public boolean createContactStrategy(FilterDTO filterDTO) {
         return restTemplate.postForEntity(String.format("%s/%s/%s?recountAudiences=false", UPLOAD_ENDPOINT, "filter", "createContactStrategy")
                 ,filterDTO,Object.class).getStatusCode() == HttpStatus.OK;

    }

    public boolean deleteContactStrategy() {
        return restTemplate.exchange(String.format("%s/%s/%s", UPLOAD_ENDPOINT, "filter", "deleteContactStrategy"),
                HttpMethod.DELETE,
                null, Boolean.class).getBody();
    }
}
