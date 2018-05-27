package ru.ohapegor.test.campaign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.siblion.crm.campaign.manager.api.CMCampaignEditorRestApi;
import ru.siblion.crm.campaign.manager.api.CMCampaignExecutorApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestCampaignEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestExecutorEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.dto.CampaignDTO;
import ru.siblion.crm.campaign.manager.api.dto.MappingDTO;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;
import ru.siblion.crm.campaign.manager.api.response.CreateEntityResponse;
import ru.siblion.crm.campaign.manager.api.response.GetAllCampaignResponse;
import ru.siblion.crm.campaign.manager.api.response.GetCampaignByIdResponse;


public class CampaignExecutor implements CMCampaignExecutorApi {

    private static final Logger LOG = LoggerFactory.getLogger(CampaignExecutor.class);

    private static final String CAMP_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.CAMP_EXECITOR_CONTR + '/';


    private static final RestTemplate restTemplate = new RestTemplate();


    @Override
    public CMResponse scheduleStart(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CAMP_ENDPOINT + CMRestExecutorEndpoints.SCHEDULE_START)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate.getForEntity(URL, CMResponse.class).getBody();
    }

    @Override
    public CMResponse startNow(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CAMP_ENDPOINT + CMRestExecutorEndpoints.START_NOW)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate.getForEntity(URL, CMResponse.class).getBody();
    }

    @Override
    public CMResponse collectStatistics(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CAMP_ENDPOINT + CMRestExecutorEndpoints.COLLECT_STATISTICS)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate.getForEntity(URL, CMResponse.class).getBody();
    }

    @Override
    public CMResponse stop(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CAMP_ENDPOINT + CMRestExecutorEndpoints.STOP)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate.getForEntity(URL, CMResponse.class).getBody();
    }
}
