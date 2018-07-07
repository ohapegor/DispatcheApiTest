package ru.ohapegor.test.offlineMarketing;

import java.util.List;

public class FilterDTO extends AbstractSupportDTO {
    private long filterId = -1;
    private String filterName;
    private List<RelationDTO> relationDataList;
    private List<RelationDTO> relationBehaviouralList;
    private boolean contactStrategy;

    public FilterDTO() {
    }

    public FilterDTO(long dataListId, long filterId, String filterName, int lastCounted) {
        this.dataListId = dataListId;
        this.filterId = filterId;
        this.filterName = filterName;
        this.lastCounted = lastCounted;
    }

    public List<RelationDTO> getRelationBehaviouralList() {
        return relationBehaviouralList;
    }

    public void setRelationBehaviouralList(List<RelationDTO> relationBehaviouralList) {
        this.relationBehaviouralList = relationBehaviouralList;
    }

    public boolean isContactStrategy() {
        return contactStrategy;
    }

    public void setContactStrategy(boolean contactStrategy) {
        this.contactStrategy = contactStrategy;
    }

    @Override
    public long getDataListId() {
        return dataListId;
    }

    public List<RelationDTO> getRelationDataList() {
        return relationDataList;
    }

    @Override
    public void setDataListId(long dataListId) {
        this.dataListId = dataListId;
    }

    public void setRelationDTOs(
            List<RelationDTO> relationDataList) {
        this.relationDataList = relationDataList;
    }

    public long getFilterId() {
        return filterId;
    }

    public void setFilterId(long filterId) {
        this.filterId = filterId;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public void putDateInfo(String date, String time) {
        this.lastDate = date;
        this.lastTime = time;
    }

    public FilterDTO putName(String filterName) {
        this.filterName = filterName;
        return this;
    }

    public FilterDTO putCount(int lastCounted) {
        this.lastCounted = lastCounted;
        return this;
    }

    @Override
    public String toString() {
        return "Filter: id = " + filterId + ", name = " + filterName + ", list = " + dataListId;
    }
}
