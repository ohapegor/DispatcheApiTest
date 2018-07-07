package ru.ohapegor.test.campaign.efficiency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.ohapegor.test.InLines;
import ru.ohapegor.test.MyAspect;
import ru.ohapegor.test.TestApplication;
import ru.siblion.crm.campaign.manager.api.dto.efficency.EffFactorDTO;
import ru.siblion.crm.campaign.manager.api.dto.efficency.enums.Aim;
import ru.siblion.crm.campaign.manager.api.dto.efficency.enums.Units;
import ru.siblion.crm.campaign.manager.api.response.CreateEntityResponse;
import ru.siblion.crm.campaign.manager.api.response.DoubleResponse;
import ru.siblion.crm.campaign.manager.api.response.ResponseStatus;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EfficiencyServiceTest {

    private static final EfficiencyService service = new EfficiencyService();

    public static final Long campId = 100000L;

    public static final Long facId1 = 100005L;

    public static final Long facId2 = 100006L;

    public static final Long resMapId1 = 100003L;
    public static final Long resMapId2 = 100004L;

    @Test
    void createEffFactors() {
        CreateEntityResponse response1 = service.createEffFactor(campId, incomeFactor1());
        System.out.println(response1);
        assertSame(response1.getStatus(),ResponseStatus.SUCCESS);

        CreateEntityResponse response2 = service.createEffFactor(campId, incomeFactor2());
        System.out.println(response2);
        assertSame(response2.getStatus(),ResponseStatus.SUCCESS);
        //System.out.println(service.createEffFactor(campId, expenseFactor1()));
    }


    @Test
    void editEffFactor() {
        Long factId = 100004L;
        EffFactorDTO factorDTO = incomeFactor1();
        factorDTO.setUnits(Units.RUBLE);
        factorDTO.setName("new name");
        factorDTO.setId(factId);
        System.out.println(service.editEffFactor(factorDTO));
    }

    @Test
    void removeEffFactor() {
        System.out.println(service.removeEffFactor(facId1));
    }

    @Test
    void calculateFactor1() {
        DoubleResponse response = service.calculateFactor(facId1);
        System.out.println(response);
        assertSame(response.getStatus(),ResponseStatus.SUCCESS);
    }

    @Test
    void calculateFactor2() {
        DoubleResponse response = service.calculateFactor(facId2);
        System.out.println(response);
        assertSame(response.getStatus(),ResponseStatus.SUCCESS);
    }

    @Test
    void calculateAllFactors() {
        System.out.println(service.calculateAllFactors(campId));
    }

    @Test
    void getEffFactors() {
        System.out.println(service.getEffFactors(campId));
    }

    @Test
    void changeStartResponseCollectDate() {
        System.out.println(service.changeStartResponseCollectDate(campId, ZonedDateTime.now().minusYears(100)));
    }

    @Test
    void changeEndResponseCollectDate() {
        System.out.println(service.changeEndResponseCollectDate(campId, ZonedDateTime.now().plusYears(100)));
    }

    private EffFactorDTO incomeFactor1() {
        EffFactorDTO factorDTO = new EffFactorDTO();
        factorDTO.setAim(Aim.INCOME);
        factorDTO.setCalculated(true);
        factorDTO.setMappingId(resMapId1);
        factorDTO.setPlanValue(10.0);
        factorDTO.setName("income_factor_1");
        factorDTO.setUnits(Units.ITEM);
        factorDTO.setPerPerson(false);
        factorDTO.setWhereInConstraint(Arrays.asList("Егор", "Михаил"));
        return factorDTO;
    }

    private EffFactorDTO incomeFactor2() {
        EffFactorDTO factorDTO = new EffFactorDTO();
        factorDTO.setAim(Aim.INCOME);
        factorDTO.setCalculated(true);
        factorDTO.setMappingId(resMapId2);
        factorDTO.setPlanValue(10.0);
        factorDTO.setName("income_factor_2");
        factorDTO.setUnits(Units.ITEM);
        factorDTO.setPerPerson(false);
        factorDTO.setWhereInConstraint(Arrays.asList("Егор", "Михаил"));
        return factorDTO;
    }

    private EffFactorDTO expenseFactor1() {
        EffFactorDTO factorDTO = new EffFactorDTO();
        factorDTO.setAim(Aim.EXPENSE);
        factorDTO.setCalculated(true);
        factorDTO.setMappingId(resMapId1);
        factorDTO.setPlanValue(10.0);
        factorDTO.setFactValue(15.0);
        factorDTO.setName("income_factor_1");
        factorDTO.setUnits(Units.RUBLE);
        factorDTO.setPerPerson(true);
        factorDTO.setWhereInConstraint(Arrays.asList("Егор", "Михаил"));
        return factorDTO;
    }
}