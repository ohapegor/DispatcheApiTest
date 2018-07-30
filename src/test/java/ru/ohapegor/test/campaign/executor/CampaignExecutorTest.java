package ru.ohapegor.test.campaign.executor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampaignExecutorTest {

    private static final CampaignExecutor executor = new CampaignExecutor();

    private static final Long campId = 100059L;

    @Test
    void scheduleStart() {
        System.out.println(executor.startCampaignScheduled(campId));
    }

    @Test
    void startNow() {
        System.out.println(executor.startCampaignNow(campId));
    }


    @Test
    void collectStatistics() {
        System.out.println(executor.collectStatistics(campId));
    }

    @Test
    void pause() {
        System.out.println(executor.pause(campId));
    }

    @Test
    void finish() {
        System.out.println(executor.finish(campId));
    }

    @Test
    void cancell() {
        System.out.println(executor.finish(campId));
    }

    @Test
    void resume() {
        System.out.println(executor.resume(campId));
    }
}