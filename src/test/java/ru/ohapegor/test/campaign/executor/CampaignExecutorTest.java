package ru.ohapegor.test.campaign.executor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampaignExecutorTest {

    private static final CampaignExecutor executor = new CampaignExecutor();

    private static final Long campId = 100000L;

    @Test
    void scheduleStart() {
    }

    @Test
    void startNow() {
        System.out.println(executor.startNow(campId));
    }

    @Test
    void collectStatistics() {
        System.out.println(executor.collectStatistics(campId));
    }

    @Test
    void stop() {
    }
}