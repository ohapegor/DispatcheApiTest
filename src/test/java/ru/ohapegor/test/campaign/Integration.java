package ru.ohapegor.test.campaign;

import org.junit.jupiter.api.Test;
import ru.ohapegor.test.campaign.editor.CampaignEditor;
import ru.ohapegor.test.campaign.executor.CampaignExecutor;
import ru.ohapegor.test.campaign.scheduling.SchedulingService;
import ru.siblion.crm.campaign.manager.api.dto.campaign.CampaignDTO;
import ru.siblion.crm.campaign.manager.api.dto.campaign.CampaignDTOBuilder;
import ru.siblion.crm.campaign.manager.api.dto.campaign.enums.CampaignCategory;
import ru.siblion.crm.campaign.manager.api.dto.campaign.enums.CampaignFrequency;
import ru.siblion.crm.campaign.manager.api.dto.campaign.enums.CampaignType;
import ru.siblion.crm.campaign.manager.api.dto.campaign.enums.ChannelType;
import ru.siblion.crm.campaign.manager.api.dto.scheduling.SchedulingConfigDTO;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;
import ru.siblion.crm.campaign.manager.api.response.ResponseStatus;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.EnumSet;
import java.util.UUID;


public class Integration {

    private CampaignEditor campaignEditor = new CampaignEditor();

    private SchedulingService schedulingService = new SchedulingService();

    private CampaignExecutor campaignExecutor = new CampaignExecutor();

    @Test
    void testPeriodicCampaign(){
        for (int i = 0; i < 2; i++) {
            Long campId = campaignEditor.createCampaign(testCampaign()).getDto();
            schedulingService.createConfig(campId,testConfig());
            campaignExecutor.startCampaignNow(campId);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private CampaignDTO testCampaign() {
        return new CampaignDTOBuilder()
                .name(String.format("TestCampaign[%s]", UUID.randomUUID().toString()))
                .startDate(ZonedDateTime.now().plusMinutes(1))
                .endDate(ZonedDateTime.now().plusMinutes(5))
                .startResponseCollectDate(ZonedDateTime.now().plusMinutes(2))
                .endResponseCollectDate(ZonedDateTime.now().plusMinutes(4))
                .channel(ChannelType.CALL)
                .campaignCategory(CampaignCategory.ABOUT_NEW_SERVICES)
                .type(CampaignType.CRM)
                .frequency(CampaignFrequency.PERIODIC)
                .agreementComment("agreement comment")
                .createdBy("egor")
                .controlGroupPercent(0.0)
                .proposalId(777L)
                .language("ru")
                .build();
    }

    private SchedulingConfigDTO testConfig(){
        SchedulingConfigDTO configDTO = new SchedulingConfigDTO();
        configDTO.setPeriod(Duration.ofSeconds(30));
        configDTO.setStatisticsDelay(Duration.ofSeconds(45));
        configDTO.setDaysOfWeek(EnumSet.allOf(DayOfWeek.class));
        configDTO.setStartDate(ZonedDateTime.now().plusMinutes(1));
        return configDTO;
    }
}
