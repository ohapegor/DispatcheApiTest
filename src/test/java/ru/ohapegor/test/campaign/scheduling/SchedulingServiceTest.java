package ru.ohapegor.test.campaign.scheduling;

import org.junit.jupiter.api.Test;
import ru.siblion.crm.campaign.manager.api.dto.scheduling.SchedulingConfigDTO;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SchedulingServiceTest {

    private Long campId = 100059L;

    private Long confId = 100001L;


    private SchedulingService service = new SchedulingService();

    @Test
    void createConfig() {
        System.out.println(service.createConfig(campId,testConfig()));
    }

    @Test
    void editConfig() {
        SchedulingConfigDTO dto = testConfig();
        dto.setPeriod(Duration.ofSeconds(30));
        dto.setId(confId);
        System.out.println(service.editConfig(dto));
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteByCampIdId() {
    }

    @Test
    void getById() {
        System.out.println(service.getById(confId));
    }

    @Test
    void getByCampId() {
        System.out.println(service.getByCampId(campId));
    }

    private SchedulingConfigDTO testConfig(){
        SchedulingConfigDTO configDTO = new SchedulingConfigDTO();
        //configDTO.setDaysOfWeek(EnumSet.of(DayOfWeek.FRIDAY,DayOfWeek.MONDAY));
        //configDTO.setExcludeDates(new HashSet<>(Arrays.asList(LocalDate.now(),LocalDate.now().plusDays(2))));
       // configDTO.setIncludeDates(new HashSet<>(Arrays.asList(LocalDate.now().minusDays(2),LocalDate.now().plusDays(3))));
        configDTO.setPeriod(Duration.ofSeconds(30));
        configDTO.setStatisticsDelay(Duration.ofSeconds(45));
        configDTO.setDaysOfWeek(EnumSet.allOf(DayOfWeek.class));
       // configDTO.setTimeAfter(LocalTime.now());
       // configDTO.setTimeAfter(LocalTime.now());
        configDTO.setStartDate(ZonedDateTime.now().plusMinutes(1));
        return configDTO;
    }
}