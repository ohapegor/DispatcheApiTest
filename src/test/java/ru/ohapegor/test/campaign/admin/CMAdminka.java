package ru.ohapegor.test.campaign.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ohapegor.test.campaign.editor.CampaignEditor;
import ru.siblion.crm.campaign.manager.api.CMAdminRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestAdminEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.dto.efficency.mapping.ResultMappingDTO;
import ru.siblion.crm.campaign.manager.api.dto.mapping.MappingDTO;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;
import ru.siblion.crm.campaign.manager.api.response.CreateEntityResponse;
import ru.siblion.crm.campaign.manager.api.response.PersonalFieldsResponse;
import ru.siblion.crm.campaign.manager.api.response.ShowAdminDataResponse;

public class CMAdminka implements CMAdminRestApi {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOG = LoggerFactory.getLogger(CampaignEditor.class);

    private static final String CM_ADMIN_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.ADMIN_CONTR + '/';


    @Override
    public CreateEntityResponse addGlobalDataSource(MappingDTO mappingDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.ADD_GLOBAL_SOURCE)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,mappingDTO,
                        CreateEntityResponse.class).getBody();
    }

    @Override
    public CreateEntityResponse addGlobalResultDataSource(ResultMappingDTO resultMappingDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.ADD_GLOBAL_RESULT_SOURCE)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,resultMappingDTO,
                        CreateEntityResponse.class).getBody();
    }

    @Override
    public CMResponse removeGlobalDataSource(Long mappingId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.REMOVE_SOURCE)
                .queryParam(QueryParams.MAPPING_ID,mappingId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .exchange(URL,HttpMethod.DELETE,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse removeResultGlobalDataSource(Long mappingId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.REMOVE_RESULT_SOURCE)
                .queryParam(QueryParams.MAPPING_ID,mappingId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .exchange(URL,HttpMethod.DELETE,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse activateDataSource(Long mappingId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.ACTIVATE_SOURCE)
                .queryParam(QueryParams.MAPPING_ID,mappingId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse deactivateDataSource(Long mappingId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.ACTIVATE_SOURCE)
                .queryParam(QueryParams.MAPPING_ID,mappingId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
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
    public PersonalFieldsResponse showFields() {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.SHOW_FIELDS)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        PersonalFieldsResponse.class).getBody();
    }
}
