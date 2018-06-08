package ru.ohapegor.test.campaign.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ohapegor.test.campaign.editor.CampaignEditor;
import ru.siblion.crm.campaign.manager.api.CMAdminRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestAdminEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestCampaignEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.dto.MappingDTO;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;
import ru.siblion.crm.campaign.manager.api.response.CreateEntityResponse;
import ru.siblion.crm.campaign.manager.api.response.ShowAdminDataResponse;
import ru.siblion.crm.campaign.manager.api.response.ShowFieldsResponse;

public class CMAdminka implements CMAdminRestApi {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOG = LoggerFactory.getLogger(CampaignEditor.class);

    private static final String CM_ADMIN_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.ADMIN_CONTR + '/';

    @Override
    public CMResponse assignPersonalDataSource(MappingDTO mappingDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.ASSIGN_PERSONAL_SOURCE)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        mappingDTO,
                        CreateEntityResponse.class).getBody();
    }

    @Override
    public CMResponse unassignPersonalDataSource() {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.UNASSIGN_PERSONAL_SOURCE)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        null,
                        CreateEntityResponse.class).getBody();
    }

    @Override
    public CMResponse assignScheduleDataSource(MappingDTO mappingDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.ASSIGN_SCHEDULE_SOURCE)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        mappingDTO,
                        CreateEntityResponse.class).getBody();
    }

    @Override
    public CMResponse unassignScheduleSource() {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.UNASSIGN_SCHEDULE_SOURCE)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        null,
                        CreateEntityResponse.class).getBody();
    }

    @Override
    public ShowAdminDataResponse showAdminData() {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.SHOW_ADMIN_DATA)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        ShowAdminDataResponse.class).getBody();
    }

    @Override
    public ShowFieldsResponse showFields() {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.SHOW_FIELDS)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        ShowFieldsResponse.class).getBody();
    }
}
