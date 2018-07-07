package ru.ohapegor.test.campaign.efficiency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ohapegor.test.campaign.editor.CampaignEditor;
import ru.siblion.crm.campaign.manager.api.CMEfficiencyRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestCampaignEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEfficiencyEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.dto.efficency.EffFactorDTO;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;
import ru.siblion.crm.campaign.manager.api.response.CalculateResponse;
import ru.siblion.crm.campaign.manager.api.response.CreateEntityResponse;
import ru.siblion.crm.campaign.manager.api.response.DoubleResponse;
import ru.siblion.crm.campaign.manager.api.response.EffFactorsListResponse;

import java.time.ZonedDateTime;

public class EfficiencyService implements CMEfficiencyRestApi {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOG = LoggerFactory.getLogger(CampaignEditor.class);

    private static final String EFF_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.EFF_CONTR + '/';


    @Override
    public CreateEntityResponse createEffFactor(Long campId, EffFactorDTO effFactorDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.CREATE_EFF_FACTOR)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        effFactorDTO,
                        CreateEntityResponse.class).getBody();
    }

    @Override
    public CMResponse editEffFactor(EffFactorDTO effFactorDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.EDIT_EFF_FACTOR)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        effFactorDTO,
                        CreateEntityResponse.class).getBody();
    }

    @Override
    public CMResponse removeEffFactor(Long factId) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.DELETE_EFF_FACTOR)
                .queryParam(QueryParams.EFF_FACT_ID, factId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .exchange(URL,
                        HttpMethod.DELETE,
                        null,
                        CMResponse.class).getBody();
    }

    @Override
    public DoubleResponse calculateFactor(Long factId) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.CALCULATE_FACTOR)
                .queryParam(QueryParams.EFF_FACT_ID, factId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        DoubleResponse.class).getBody();
    }

    @Override
    public CalculateResponse calculateAllFactors(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.CALCULATE_ALL_FACTORS)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CalculateResponse.class).getBody();
    }

    @Override
    public EffFactorsListResponse getEffFactors(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.GET_FOR_CAMPAIGN)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        EffFactorsListResponse.class).getBody();
    }

    @Override
    public CMResponse changeStartResponseCollectDate(Long campId, ZonedDateTime zonedDateTime) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.CHANGE_START_RESPONSE_COLLECT_DATE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        zonedDateTime,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse changeEndResponseCollectDate(Long campId, ZonedDateTime zonedDateTime) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.CHANGE_END_RESPONSE_COLLECT_DATE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        zonedDateTime,
                        CMResponse.class).getBody();
    }
}
