package ru.ohapegor.test.campaign.editor;

import org.junit.jupiter.api.Test;
import ru.siblion.crm.campaign.manager.api.dto.CampaignDTO;
import ru.siblion.crm.campaign.manager.api.dto.MappingDTO;
import ru.siblion.crm.campaign.manager.api.dto.MappingFieldDTO;
import ru.siblion.crm.campaign.manager.api.dto.ResultDataDTO;
import ru.siblion.crm.campaign.manager.api.dto.builder.CampaignDTOBuilder;
import ru.siblion.crm.campaign.manager.api.dto.enums.ChannelType;
import ru.siblion.crm.campaign.manager.api.dto.enums.MappingDataType;
import ru.siblion.crm.campaign.manager.api.dto.enums.MappingFieldType;
import ru.siblion.crm.campaign.manager.api.dto.enums.Queues;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.UUID;

import static ru.siblion.crm.campaign.manager.api.dto.enums.SystemFields.CLIENT_ID;
import static ru.siblion.crm.campaign.manager.api.dto.enums.SystemFields.EMAIL;
import static ru.siblion.crm.campaign.manager.api.dto.enums.SystemFields.PHONE;

class CampaignEditorTest {

    private static final CampaignEditor campaignEditor = new CampaignEditor();

    private static final Long campId = 100000L;

    private static final Long audId = 100044L;

    private static final Long propId = 100000L;

    private static final Long sourceId = 100000L;

    @Test
    void createNewCampaign() {
        Long campId = campaignEditor.createCampaign(testCampaign()).getId();
        System.out.println(campaignEditor.assignAudience(campId, audId));
        System.out.println(campaignEditor.assignSource(campId,
                testMapping()));
        System.out.println(campaignEditor.assignProposal(campId, propId, ChannelType.CALL));
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
        System.out.println(campaignEditor.getById(100000L));
    }

    @Test
    void deleteById() {
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
        MappingFieldDTO id = new MappingFieldDTO(
                "CLIENTS_PCODE",
                MappingFieldType.SYSTEM_FIELD,
                new ResultDataDTO(CLIENT_ID.name(),
                        MappingDataType.INTEGER), null);
        MappingFieldDTO phone = new MappingFieldDTO(
                "CALL_TRACKING_PHONE",
                MappingFieldType.SYSTEM_FIELD,
                new ResultDataDTO(PHONE.name(),
                        MappingDataType.STRING), null);
        MappingFieldDTO email = new MappingFieldDTO(
                "CLIENTS_CLMAIL",
                MappingFieldType.SYSTEM_FIELD,
                new ResultDataDTO(EMAIL.name(),
                        MappingDataType.STRING), null);
        MappingFieldDTO name = new MappingFieldDTO(
                "CLIENTS_FIRSTNAME",
                MappingFieldType.USER_FIELD,
                new ResultDataDTO("FirstName",
                        MappingDataType.STRING), null);
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
        return new MappingDTO(sourceId,Arrays.asList(id, phone, email, name));
    }

    @Test
    void unassignSource() {
        System.out.println(campaignEditor.unassignSource(campId));
    }

    @Test
    void assignProposal() {
        System.out.println(campaignEditor.assignProposal(campId, 100000L, ChannelType.EMAIL));
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
                .createdBy("egor")
                .controlGroupPercent(0.0)
                .proposalId(777L)
                .language("ru")
                .build();
    }

    @Test
    void showFields(){
        System.out.println(campaignEditor.showFields(campId));
    }

    @Test
    void changeQueues(){
        System.out.println(campaignEditor.setCampaignQueues(campId,EnumSet.of(Queues.CALL_CENTER,Queues.HOVRINO,Queues.MEDVEDKOVO)));
    }
}