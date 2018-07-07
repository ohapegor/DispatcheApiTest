package ru.ohapegor.test.campaign.statistics;

import org.junit.jupiter.api.Test;

class StatisticsServiceTest {

    private StatisticsService service = new StatisticsService();

    private Long campId = 100000L;

    @Test
    void getStatistics() {
        System.out.println(service.getStatistics(campId));
    }

    @Test
    void showFields() {
        System.out.println(service.showFieldsTags(campId));
    }


    @Test
    void showData() {
        System.out.println(service.showExampleData(campId,10));
    }
}