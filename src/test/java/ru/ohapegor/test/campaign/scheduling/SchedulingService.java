package ru.ohapegor.test.campaign.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ohapegor.test.campaign.AbstractCMService;
import ru.ohapegor.test.campaign.editor.CampaignEditor;
import ru.siblion.crm.campaign.manager.api.CMSchedulingEditorRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestSchedulingEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.dto.scheduling.SchedulingConfigDTO;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;

public class SchedulingService extends AbstractCMService implements CMSchedulingEditorRestApi {

    private static final Logger LOG = LoggerFactory.getLogger(CampaignEditor.class);

    private static final String CM_SCHEDULE_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.SCHEDULING_CONFIG_CONTR + '/';
    
    
    
    @Override
    public CMResponse<Long> createConfig(Long campId, SchedulingConfigDTO schedulingConfigDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_SCHEDULE_ENDPOINT + CMRestSchedulingEndpoints.CREATE_CONFIG)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE.postForEntity(URL,
                schedulingConfigDTO, CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> editConfig(SchedulingConfigDTO schedulingConfigDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_SCHEDULE_ENDPOINT + CMRestSchedulingEndpoints.EDIT_CONFIG)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE.postForEntity(URL,
                schedulingConfigDTO, CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> deleteById(Long id) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_SCHEDULE_ENDPOINT + CMRestSchedulingEndpoints.DELETE_BY_ID)
                .queryParam(QueryParams.SCHEDULE_CONFIG_ID,id)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE.exchange(URL,HttpMethod.DELETE,
                null, CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> deleteByCampId(Long aLong) {
        return null;
    }


    @Override
    public CMResponse<SchedulingConfigDTO> getById(Long id) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_SCHEDULE_ENDPOINT + CMRestSchedulingEndpoints.GET_BY_ID)
                .queryParam(QueryParams.SCHEDULE_CONFIG_ID,id)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE.getForEntity(URL,CMResponse.class).getBody();
    }

    @Override
    public CMResponse<SchedulingConfigDTO> getByCampId(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_SCHEDULE_ENDPOINT + CMRestSchedulingEndpoints.GET_BY_CAMP_ID)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE.getForEntity(URL,CMResponse.class).getBody();
    }
}
