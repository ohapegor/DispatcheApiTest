package ru.ohapegor.test.campaign.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.siblion.crm.campaign.manager.api.CMInformationRestApi;
import ru.siblion.crm.campaign.manager.api.CMParticipantsRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestInformationEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.response.ExampleDataResponse;
import ru.siblion.crm.campaign.manager.api.response.GetCampaignStatistics;
import ru.siblion.crm.campaign.manager.api.response.PersonalFieldsResponse;

public class StatisticsService implements CMInformationRestApi {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOG = LoggerFactory.getLogger(StatisticsService.class);

    private static final String CM_STATISTICS_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.INFORMATION_CONTR + '/';


    @Override
    public GetCampaignStatistics getStatistics(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_STATISTICS_ENDPOINT + CMRestInformationEndpoints.GET_FOR_CAMPAIGN)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        GetCampaignStatistics.class).getBody();
    }

    @Override
    public ExampleDataResponse showExampleData(Long campId, Integer integer) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_STATISTICS_ENDPOINT + CMRestInformationEndpoints.SHOW_DATA)
                .queryParam(QueryParams.CAMP_ID,campId)
                .queryParam(QueryParams.LIMIT,integer)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        ExampleDataResponse.class).getBody();
    }

    @Override
    public PersonalFieldsResponse showFieldsTags(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_STATISTICS_ENDPOINT + CMRestInformationEndpoints.SHOW_FIELDS)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        PersonalFieldsResponse.class).getBody();
    }

}
