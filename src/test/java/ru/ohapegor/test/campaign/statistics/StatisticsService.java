package ru.ohapegor.test.campaign.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.siblion.crm.campaign.manager.api.CMInformationRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestInformationEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.dto.scheduling.DeliveryDTO;
import ru.siblion.crm.campaign.manager.api.dto.statistic.DeliveryStatisticsDTO;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;

import java.util.List;
import java.util.Map;

public class StatisticsService implements CMInformationRestApi {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOG = LoggerFactory.getLogger(StatisticsService.class);

    private static final String CM_STATISTICS_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.INFORMATION_CONTR + '/';


    @Override
    public CMResponse<DeliveryStatisticsDTO> getStatistics(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_STATISTICS_ENDPOINT + CMRestInformationEndpoints.GET_FOR_CAMPAIGN)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<List<Map<String, String>>> showExampleData(Long campId, Integer integer) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_STATISTICS_ENDPOINT + CMRestInformationEndpoints.SHOW_DATA)
                .queryParam(QueryParams.CAMP_ID,campId)
                .queryParam(QueryParams.LIMIT,integer)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Map<String, String>>  showFieldsTags(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_STATISTICS_ENDPOINT + CMRestInformationEndpoints.SHOW_FIELDS)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<List<DeliveryDTO>> getCampaignDeliveries(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_STATISTICS_ENDPOINT + CMRestInformationEndpoints.GET_DELIVERIES)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

}
