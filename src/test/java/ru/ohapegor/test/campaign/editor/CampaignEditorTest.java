package ru.ohapegor.test.campaign.editor;

import org.junit.jupiter.api.Test;
import ru.siblion.crm.campaign.manager.api.dto.campaign.CampaignDTO;
import ru.siblion.crm.campaign.manager.api.dto.campaign.CampaignDTOBuilder;
import ru.siblion.crm.campaign.manager.api.dto.campaign.enums.CampaignCategory;
import ru.siblion.crm.campaign.manager.api.dto.campaign.enums.CampaignType;
import ru.siblion.crm.campaign.manager.api.dto.campaign.enums.ChannelType;
import ru.siblion.crm.campaign.manager.api.dto.mapping.MappingDTO;
import ru.siblion.crm.campaign.manager.api.dto.mapping.MappingFieldDTO;
import ru.siblion.crm.campaign.manager.api.dto.mapping.ResultDataDTO;
import ru.siblion.crm.campaign.manager.api.dto.mapping.enums.MappingDataType;
import ru.siblion.crm.campaign.manager.api.dto.mapping.enums.MappingFieldType;
import ru.siblion.crm.campaign.manager.api.dto.mapping.enums.SourcePurpose;
import ru.siblion.crm.campaign.manager.api.dto.participant.enums.Queues;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;
import ru.siblion.crm.campaign.manager.api.response.ResponseStatus;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;
import static ru.siblion.crm.campaign.manager.api.dto.campaign.enums.ChannelType.CALL;
import static ru.siblion.crm.campaign.manager.api.dto.campaign.enums.ChannelType.EMAIL;
import static ru.siblion.crm.campaign.manager.api.dto.mapping.enums.SystemFields.CLIENT_ID;
import static ru.siblion.crm.campaign.manager.api.dto.mapping.enums.SystemFields.PHONE;


class CampaignEditorTest {

    private static final CampaignEditor campaignEditor = new CampaignEditor();

    private static final Long campId = 100000L;

    private static final Long audId = 100025L;

    private static final Long propId = 100000L;

    private static final Long sourceId = 100000L;

    @Test
    void createNewCampaign() {
        Long campId = campaignEditor.createCampaign(testCampaign()).getId();
        CMResponse response1 = campaignEditor.assignAudience(campId, audId);
        System.out.println(response1);
        assertSame(response1.getStatus(),ResponseStatus.SUCCESS);
        // System.out.println(campaignEditor.assignSource(campId, testMapping()));
        CMResponse response2 = campaignEditor.assignProposal(campId, propId, CALL);
        System.out.println(response2);
        assertSame(response2.getStatus(),ResponseStatus.SUCCESS);
    }

    @Test
    void createCampaign() {
        System.out.println(campaignEditor.createCampaign(testCampaign()));
    }

    @Test
    void editCampaign() {
        CampaignDTO campaignDTO = testCampaign();
        campaignDTO.setId(campId);
        campaignDTO.setName("new name");
        System.out.println(campaignEditor.editCampaign(campaignDTO));

    }

    @Test
    void getAll() {
        System.out.println(campaignEditor.getAll());
    }

    @Test
    void getById() {
        System.out.println(campaignEditor.getById(100003L));
    }

    @Test
    void deleteById() {
        System.out.println(campaignEditor.deleteById(campId));
    }

    @Test
    void assignAudience() {
        System.out.println(campaignEditor.assignAudience(campId, 100021L));
    }

    @Test
    void unassignAudience() {
        System.out.println(campaignEditor.unassignAudience(campId));
    }

    @Test
    void assignSource() {
        System.out.println(campaignEditor.assignSource(campId,
                testMapping()));
    }

    private MappingDTO testMapping() {
        MappingFieldDTO id = new MappingFieldDTO();
        id.setSourceName("CLIENTS_PCODE");
        id.setMappingFieldType(MappingFieldType.SYSTEM_FIELD);
        id.setResultData(new ResultDataDTO(CLIENT_ID.name(), MappingDataType.INTEGER));

        MappingFieldDTO phone = new MappingFieldDTO();
        phone.setSourceName("CALL_TRACKING_PHONE");
        phone.setMappingFieldType(MappingFieldType.SYSTEM_FIELD);
        phone.setResultData(new ResultDataDTO(PHONE.name(), MappingDataType.STRING));

        MappingFieldDTO email = new MappingFieldDTO();
        email.setSourceName("CLIENTS_CLMAIL");
        email.setMappingFieldType(MappingFieldType.SYSTEM_FIELD);
        email.setResultData(new ResultDataDTO(EMAIL.name(), MappingDataType.STRING));

        MappingFieldDTO name = new MappingFieldDTO();
        name.setSourceName("CLIENTS_FIRSTNAME");
        name.setMappingFieldType(MappingFieldType.USER_FIELD);
        name.setResultData(new ResultDataDTO("FirstName", MappingDataType.STRING));


        /*MappingFieldDTO birthday = new MappingFieldDTO(
                "B_DATE",
                MappingFieldType.USER_FIELD,
                new ResultDataDTO("birthday",
                        MappingDataType.DATE), "yyyy-MM-dd HH:mm:ss");
        MappingFieldDTO check = new MappingFieldDTO(
                "AVG_SUM",
                MappingFieldType.USER_FIELD,
                new ResultDataDTO("check",
                        MappingDataType.DOUBLE), null);
        MappingFieldDTO color = new MappingFieldDTO(
                "любимый цвет",
                MappingFieldType.USER_FIELD,
                new ResultDataDTO("Color",
                        MappingDataType.STRING), null);*/
        MappingDTO mappingDTO = new MappingDTO();
        mappingDTO.setSourceId(sourceId);
        mappingDTO.setFields(Arrays.asList(id, phone, email, name));
        return mappingDTO;
    }

    @Test
    void unassignSource() {
        System.out.println(campaignEditor.unassignSource(campId, SourcePurpose.PERSONAL));
    }

    @Test
    void assignProposal() {
        System.out.println(campaignEditor.assignProposal(100003L, 100000L, EMAIL));
    }

    @Test
    void unassignProposal() {
        System.out.println(campaignEditor.unassignProposal(campId));
    }

    private CampaignDTO testCampaign() {
        return new CampaignDTOBuilder()
                .name(String.format("TestCampaign[%s]", UUID.randomUUID().toString()))
                .startDate(ZonedDateTime.now().plusWeeks(1))
                .endDate(ZonedDateTime.now().plusMonths(2))
                .startResponseCollectDate(ZonedDateTime.now().plusMonths(1))
                .endResponseCollectDate(ZonedDateTime.now().plusMonths(1).plusWeeks(1))
                .channel(ChannelType.CALL)
                .campaignCategory(CampaignCategory.ABOUT_NEW_SERVICES)
                .type(CampaignType.CRM)
                .agreementComment("agreement comment")
                .createdBy("egor")
                .controlGroupPercent(0.0)
                .proposalId(777L)
                .language("ru")
                .build();
    }

    @Test
    void changeQueues() {
        System.out.println(campaignEditor.setCampaignQueues(campId, EnumSet.of(Queues.CALL_CENTER, Queues.HOVRINO, Queues.MEDVEDKOVO)));
    }
}