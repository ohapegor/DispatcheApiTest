package ru.ohapegor.test.offlineMarketing;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;

public class AggregateRequest {

    private Long sourceId;

    private String dateFieldName;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private Map<String,Set<String>> argumetsMap;

    private Set<String> summFields;

    public Set<String> getSummFields() {
        return summFields;
    }

    public void setSummFields(Set<String> summFields) {
        this.summFields = summFields;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getDateFieldName() {
        return dateFieldName;
    }

    public void setDateFieldName(String dateFieldName) {
        this.dateFieldName = dateFieldName;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Map<String, Set<String>> getArgumetsMap() {
        return argumetsMap;
    }

    public void setArgumetsMap(Map<String, Set<String>> argumetsMap) {
        this.argumetsMap = argumetsMap;
    }

    @Override
    public String toString() {
        return "AggregateRequest{" +
                "sourceId=" + sourceId +
                ", dateFieldName='" + dateFieldName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", argumetsMap=" + argumetsMap +
                '}';
    }
}
