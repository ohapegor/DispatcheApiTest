package ru.ohapegor.test.offlineMarketing;

import org.hibernate.mapping.Collection;
import org.junit.jupiter.api.Test;
import ru.siblion.crm.campaign.manager.api.dto.filter.BehavioralFieldsEnum;
import ru.siblion.crm.campaign.manager.api.dto.filter.BehavioralPredicate;
import ru.siblion.crm.campaign.manager.api.dto.filter.ConditionsEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


class DataApiTest {

    private static final DataApi dataApi = new DataApi();

    @Test
    void aggreagateData() {
        AggregateRequest request = new AggregateRequest();
        request.setSourceId(100038L);
        Map<String, Set<String>> argsMap = new LinkedHashMap<>();
        argsMap.put("CLIENTS_PCODE", new HashSet<>(Arrays.asList("20000288",
                "40000389",
                "990000188",
                "777",
                "30000289",
                "60006200"
        )));
        request.setArgumetsMap(argsMap);
        request.setSummFields(new HashSet<>(Arrays.asList("CLIENTS_POL", "CLIENTS_FILIAL")));
        System.out.println(dataApi.aggreagateData(request));
    }

    @Test
    void createContactStrategy() {
        FilterDTO filterDTO = new FilterDTO();
        filterDTO.setFilterName("test");

        RelationDTO relationDTO = new RelationDTO();
        relationDTO.setField(true);
        relationDTO.setOperandA("CAMPAIGN_STATUS");
        relationDTO.setOperandType(OperandType.STRING);
        relationDTO.setRelation("contains");
        relationDTO.setOperandB("RUNNING");


        filterDTO.setRelationBehaviouralList(Collections.singletonList(relationDTO));
        filterDTO.setRelationDTOs(Collections.emptyList());

        System.out.println(dataApi.createContactStrategy(filterDTO));
    }
}