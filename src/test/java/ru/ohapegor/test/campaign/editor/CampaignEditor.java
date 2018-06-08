package ru.ohapegor.test.campaign.editor;

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
import ru.siblion.crm.campaign.manager.api.dto.enums.ChannelType;
import ru.siblion.crm.campaign.manager.api.dto.enums.Queues;
import ru.siblion.crm.campaign.manager.api.response.*;

import java.util.EnumSet;
import java.util.Set;

public class CampaignEditor implements CMCampaignEditorRestApi {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOG = LoggerFactory.getLogger(CampaignEditor.class);

    private static final String CM_EDITOR_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.CAMP_EDITOR_CONTR + '/';

    @Override
    public CreateEntityResponse createCampaign(CampaignDTO campaignDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.CREATE)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        campaignDTO,
                        CreateEntityResponse.class).getBody();
    }

    @Override
    public CMResponse editCampaign(CampaignDTO campaignDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.EDIT)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        campaignDTO,
                        CreateEntityResponse.class).getBody();
    }

    @Override
    public GetAllCampaignResponse getAll() {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.GET_ALL)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        GetAllCampaignResponse.class).getBody();
    }

    @Override
    public GetCampaignByIdResponse getById(Long aLong) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.GET_BY_ID)
                .queryParam(QueryParams.CAMP_ID, aLong)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        GetCampaignByIdResponse.class).getBody();
    }

    @Override
    public CMResponse deleteById(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.DELETE_BY_ID)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .exchange(URL,HttpMethod.DELETE,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse assignAudience(Long campId, Long audId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.ASSIGN_AUDIENCE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .queryParam(QueryParams.AUD_ID, audId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse unassignAudience(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.UNASSIGN_AUDIENCE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse assignProposal(Long campId, Long propId, ChannelType channelType) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.ASSIGN_PROPOSAL)
                .queryParam(QueryParams.CAMP_ID, campId)
                .queryParam(QueryParams.PROP_ID, propId)
                .queryParam(QueryParams.CHANNEL, channelType)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse unassignProposal(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.UNASSIGN_PROPOSAL)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse changeChannel(Long aLong, ChannelType channelType) {
        return null;
    }

    @Override
    public CMResponse assignSource(Long campId, MappingDTO mappingDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.ASSIGN_SOURCE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,mappingDTO,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse unassignSource(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.UNASSIGN_SOURCE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public ShowFieldsResponse showFields(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.SHOW_FIELDS)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        ShowFieldsResponse.class).getBody();
    }

    @Override
    public CMResponse setCampaignQueues(Long campId, Set<Queues> enumSet) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.CHANGE_QUEUES)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,enumSet,
                        CMResponse.class).getBody();
    }
}
