package ru.ohapegor.test.campaign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.siblion.crm.campaign.manager.api.CMCampaignEditorRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestCampaignEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.dto.CampaignDTO;
import ru.siblion.crm.campaign.manager.api.dto.MappingDTO;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;
import ru.siblion.crm.campaign.manager.api.response.CreateEntityResponse;
import ru.siblion.crm.campaign.manager.api.response.GetAllCampaignResponse;
import ru.siblion.crm.campaign.manager.api.response.GetCampaignByIdResponse;


public class CampaignEditor implements CMCampaignEditorRestApi {

    private static final Logger LOG = LoggerFactory.getLogger(CampaignEditor.class);

    private static final String CAMP_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.CAMP_EDITOR_CONTR + '/';


    private static final RestTemplate restTemplate = new RestTemplate();

    @Override
    public CreateEntityResponse createCampaign(CampaignDTO campaignDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CAMP_ENDPOINT + CMRestCampaignEndpoints.CREATE)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        campaignDTO,
                        CreateEntityResponse.class).getBody();
    }

    @Override
    public CMResponse editCampaign(CampaignDTO campaignDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CAMP_ENDPOINT + CMRestCampaignEndpoints.EDIT)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        campaignDTO,
                        CMResponse.class).getBody();
    }

    @Override
    public GetAllCampaignResponse getAll() {
        String URL = UriComponentsBuilder.fromHttpUrl(CAMP_ENDPOINT + CMRestCampaignEndpoints.GET_ALL)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate.getForEntity(URL, GetAllCampaignResponse.class).getBody();
    }

    @Override
    public GetCampaignByIdResponse getById(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CAMP_ENDPOINT + CMRestCampaignEndpoints.GET_BY_ID)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate.getForEntity(URL, GetCampaignByIdResponse.class).getBody();
    }

    @Override
    public CMResponse deleteById(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CAMP_ENDPOINT + CMRestCampaignEndpoints.DELETE_BY_ID)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate.exchange(URL, HttpMethod.DELETE, null,
                CMResponse.class).getBody();
    }

    @Override
    public CMResponse assignAudience(Long campId, Long audId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CAMP_ENDPOINT + CMRestCampaignEndpoints.ASSIGN_AUDIENCE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .queryParam(QueryParams.AUD_ID, audId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate.postForEntity(URL, null, CMResponse.class).getBody();
    }

    @Override
    public CMResponse unassignAudience(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CAMP_ENDPOINT + CMRestCampaignEndpoints.UNASSIGN_AUDIENCE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate.postForEntity(URL, null, CMResponse.class).getBody();
    }

    @Override
    public CMResponse assignSource(Long campId, MappingDTO mappingDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CAMP_ENDPOINT + CMRestCampaignEndpoints.ASSIGN_SOURCE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate.postForEntity(URL, mappingDTO, CMResponse.class).getBody();
    }

    @Override
    public CMResponse unassignSource(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CAMP_ENDPOINT + CMRestCampaignEndpoints.UNASSIGN_SOURCE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate.postForEntity(URL, null, CMResponse.class).getBody();
    }
}
