package ru.ohapegor.test.campaign.efficiency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ohapegor.test.campaign.AbstractCMService;
import ru.ohapegor.test.campaign.editor.CampaignEditor;
import ru.siblion.crm.campaign.manager.api.CMEfficiencyRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEfficiencyEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.dto.efficency.EffFactorDTO;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public class EfficiencyService  extends AbstractCMService implements CMEfficiencyRestApi {

    private static final Logger LOG = LoggerFactory.getLogger(CampaignEditor.class);

    private static final String EFF_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.EFF_CONTR + '/';


    @Override
    public CMResponse<Long> createEffFactor(Long campId, EffFactorDTO effFactorDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.CREATE_EFF_FACTOR)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,
                        effFactorDTO,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> editEffFactor(EffFactorDTO effFactorDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.EDIT_EFF_FACTOR)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,
                        effFactorDTO,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> removeEffFactor(Long factId) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.DELETE_EFF_FACTOR)
                .queryParam(QueryParams.EFF_FACT_ID, factId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .exchange(URL,
                        HttpMethod.DELETE,
                        null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Double> calculateFactor(Long factId) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.CALCULATE_FACTOR)
                .queryParam(QueryParams.EFF_FACT_ID, factId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Map<String, Double>> calculateAllFactors(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.CALCULATE_ALL_FACTORS)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<List<EffFactorDTO>> getEffFactors(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.GET_FOR_CAMPAIGN)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> changeStartResponseCollectDate(Long campId, ZonedDateTime zonedDateTime) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.CHANGE_START_RESPONSE_COLLECT_DATE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,
                        zonedDateTime,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> changeEndResponseCollectDate(Long campId, ZonedDateTime zonedDateTime) {
        String URL = UriComponentsBuilder.fromHttpUrl(EFF_ENDPOINT + CMRestEfficiencyEndpoints.CHANGE_END_RESPONSE_COLLECT_DATE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,
                        zonedDateTime,
                        CMResponse.class).getBody();
    }
}
