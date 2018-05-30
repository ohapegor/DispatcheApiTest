package ru.ohapegor.test.campaign.statistics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsManagerTest {

    private StatisticsManager statisticsManager = new StatisticsManager();

    private Long campId = 100000L;

    @Test
    void getParticipants() {
        System.out.println(statisticsManager.getParticipants(campId));
    }
}