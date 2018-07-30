package ru.ohapegor.test.campaign.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ohapegor.test.campaign.AbstractCMService;
import ru.ohapegor.test.campaign.editor.CampaignEditor;
import ru.siblion.crm.campaign.manager.api.CMCampaignExecutorRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestExecutorEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;

public class CampaignExecutor extends AbstractCMService implements CMCampaignExecutorRestApi {

    private static final Logger LOG = LoggerFactory.getLogger(CampaignEditor.class);

    private static final String CM_EXECUTOR_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.CAMP_EXECITOR_CONTR + '/';


    @Override
    public CMResponse<Void> startCampaignScheduled(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EXECUTOR_ENDPOINT + CMRestExecutorEndpoints.START_CAMPAIGN_SCHEDULED)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> startCampaignNow(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EXECUTOR_ENDPOINT + CMRestExecutorEndpoints.START_CAMPAIGN_NOW)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }


    @Override
    public CMResponse collectStatistics(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EXECUTOR_ENDPOINT + CMRestExecutorEndpoints.COLLECT_CAMPAIGN_STATISTICS)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> collectDeliveryStatistics(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EXECUTOR_ENDPOINT + CMRestExecutorEndpoints.COLLECT_DELIVERY_STATISTICS)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> pause(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EXECUTOR_ENDPOINT + CMRestExecutorEndpoints.PAUSE)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> resume(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EXECUTOR_ENDPOINT + CMRestExecutorEndpoints.RESUME)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> finish(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EXECUTOR_ENDPOINT + CMRestExecutorEndpoints.FINISH)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> stop(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EXECUTOR_ENDPOINT + CMRestExecutorEndpoints.STOP)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> cancelDelivery(Long deliveryId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EXECUTOR_ENDPOINT + CMRestExecutorEndpoints.CANCEL_DELIVERY)
                .queryParam(QueryParams.DELIVERY_ID,deliveryId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }


}
