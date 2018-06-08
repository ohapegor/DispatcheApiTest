package ru.ohapegor.test.campaign.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ohapegor.test.campaign.editor.CampaignEditor;
import ru.siblion.crm.campaign.manager.api.CMCampaignExecutorRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestExecutorEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;

public class CampaignExecutor implements CMCampaignExecutorRestApi {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOG = LoggerFactory.getLogger(CampaignEditor.class);

    private static final String CM_EXECUTOR_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.CAMP_EXECITOR_CONTR + '/';


    @Override
    public CMResponse scheduleStart(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EXECUTOR_ENDPOINT + CMRestExecutorEndpoints.SCHEDULE_START)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse startNow(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EXECUTOR_ENDPOINT + CMRestExecutorEndpoints.START_NOW)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse collectStatistics(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EXECUTOR_ENDPOINT + CMRestExecutorEndpoints.COLLECT_STATISTICS)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse stop(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EXECUTOR_ENDPOINT + CMRestExecutorEndpoints.STOP)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse finish(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EXECUTOR_ENDPOINT + CMRestExecutorEndpoints.FINISH)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }
}
