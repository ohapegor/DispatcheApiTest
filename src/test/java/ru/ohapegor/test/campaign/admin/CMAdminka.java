package ru.ohapegor.test.campaign.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ohapegor.test.campaign.AbstractCMService;
import ru.ohapegor.test.campaign.editor.CampaignEditor;
import ru.siblion.crm.campaign.manager.api.CMAdminRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestAdminEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.dto.admin.CMAdminDataDTO;
import ru.siblion.crm.campaign.manager.api.dto.efficency.mapping.ResultMappingDTO;
import ru.siblion.crm.campaign.manager.api.dto.mapping.MappingDTO;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;

import java.util.Map;

@SuppressWarnings("unckecked")
public class CMAdminka extends AbstractCMService implements CMAdminRestApi {


    private static final Logger LOG = LoggerFactory.getLogger(CampaignEditor.class);

    private static final String CM_ADMIN_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.ADMIN_CONTR + '/';


    @Override
    @SuppressWarnings("unckecked")
    public CMResponse<Long> addGlobalDataSource(MappingDTO mappingDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.ADD_GLOBAL_SOURCE)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL, mappingDTO,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Long> addGlobalResultDataSource(ResultMappingDTO resultMappingDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.ADD_GLOBAL_RESULT_SOURCE)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL, resultMappingDTO,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> removeGlobalDataSource(Long mappingId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.REMOVE_SOURCE)
                .queryParam(QueryParams.MAPPING_ID, mappingId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .exchange(URL, HttpMethod.DELETE, null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> removeResultGlobalDataSource(Long mappingId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.REMOVE_RESULT_SOURCE)
                .queryParam(QueryParams.MAPPING_ID, mappingId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .exchange(URL, HttpMethod.DELETE, null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> activateDataSource(Long mappingId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.ACTIVATE_SOURCE)
                .queryParam(QueryParams.MAPPING_ID, mappingId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL, null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> deactivateDataSource(Long mappingId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.ACTIVATE_SOURCE)
                .queryParam(QueryParams.MAPPING_ID, mappingId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL, null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<CMAdminDataDTO> showAdminData() {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.SHOW_ADMIN_DATA)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Map<String, String>> showFields() {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_ADMIN_ENDPOINT + CMRestAdminEndpoints.SHOW_FIELDS)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }
}
