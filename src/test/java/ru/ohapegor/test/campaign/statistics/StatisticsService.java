package ru.ohapegor.test.campaign.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.siblion.crm.campaign.manager.api.CMParticipantsRestApi;
import ru.siblion.crm.campaign.manager.api.CMStatisticsRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestStatisticsEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.response.GetCampaignStatistics;

public class StatisticsService implements CMStatisticsRestApi {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOG = LoggerFactory.getLogger(StatisticsService.class);

    private static final String CM_STATISTICS_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.STATISTICS_CONTR + '/';


    @Override
    public GetCampaignStatistics getStatistics(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_STATISTICS_ENDPOINT + CMRestStatisticsEndpoints.GET_FOR_CAMPAIGN)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        GetCampaignStatistics.class).getBody();
    }

}
