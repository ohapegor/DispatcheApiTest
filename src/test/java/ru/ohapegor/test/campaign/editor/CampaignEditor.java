package ru.ohapegor.test.campaign.editor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ohapegor.test.campaign.AbstractCMService;
import ru.siblion.crm.campaign.manager.api.CMCampaignEditorRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestCampaignEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.dto.campaign.CampaignDTO;
import ru.siblion.crm.campaign.manager.api.dto.campaign.enums.ChannelType;
import ru.siblion.crm.campaign.manager.api.dto.mapping.MappingDTO;
import ru.siblion.crm.campaign.manager.api.dto.mapping.enums.SourcePurpose;
import ru.siblion.crm.campaign.manager.api.dto.participant.enums.Queues;
import ru.siblion.crm.campaign.manager.api.request.ChangeQueuesRequest;
import ru.siblion.crm.campaign.manager.api.response.*;

import java.util.List;
import java.util.Set;

public class CampaignEditor extends AbstractCMService implements CMCampaignEditorRestApi {


    private static final Logger LOG = LoggerFactory.getLogger(CampaignEditor.class);

    private static final String CM_EDITOR_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.CAMP_EDITOR_CONTR + '/';

    @Override
    public CMResponse<Long> createCampaign(CampaignDTO campaignDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.CREATE)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,
                        campaignDTO,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> editCampaign(CampaignDTO campaignDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.EDIT)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,
                        campaignDTO,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<List<CampaignDTO>> getAll() {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.GET_ALL)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<CampaignDTO> getById(Long aLong) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.GET_BY_ID)
                .queryParam(QueryParams.CAMP_ID, aLong)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> deleteById(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.DELETE_BY_ID)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .exchange(URL,HttpMethod.DELETE,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> assignAudience(Long campId, Long audId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.ASSIGN_AUDIENCE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .queryParam(QueryParams.AUD_ID, audId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> unassignAudience(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.UNASSIGN_AUDIENCE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> assignProposal(Long campId, Long propId, ChannelType channelType) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.ASSIGN_PROPOSAL)
                .queryParam(QueryParams.CAMP_ID, campId)
                .queryParam(QueryParams.PROP_ID, propId)
                .queryParam(QueryParams.CHANNEL, channelType)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> unassignProposal(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.UNASSIGN_PROPOSAL)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> changeChannel(Long campId, ChannelType channelType) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.CHANGE_CHANNEL)
                .queryParam(QueryParams.CAMP_ID, campId)
                .queryParam(QueryParams.CHANNEL, channelType)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> assignSource(Long campId, MappingDTO mappingDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.ASSIGN_SOURCE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,mappingDTO,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> unassignSource(Long campId, SourcePurpose purpose) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.UNASSIGN_SOURCE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }


    @Override
    public CMResponse<Void> setCampaignQueues(Long campId, ChangeQueuesRequest request) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.CHANGE_QUEUES)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,request,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<Void> assignGlobalSource(Long campId, Long mappingId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_EDITOR_ENDPOINT + CMRestCampaignEndpoints.ASSIGN_GLOBAL_SOURCE)
                .queryParam(QueryParams.CAMP_ID, campId)
                .queryParam(QueryParams.MAPPING_ID, mappingId)
                .build().toUri().toString();
        LOG.info(URL);
        return REST_TEMPLATE
                .postForEntity(URL,null,
                        CMResponse.class).getBody();
    }
}
