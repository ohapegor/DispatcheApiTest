package ru.ohapegor.test.offlineMarketing;

public abstract class AbstractSupportDTO {
  protected long dataListId = -1;
  protected String lastDate;
  protected String lastTime;
  protected int lastCounted;

  public String getLastTime() {
    return lastTime;
  }

  public void setLastTime(String lastTime) {
    this.lastTime = lastTime;
  }

  public String getLastDate() {
    return lastDate;
  }

  public void setLastDate(String lastDate) {
    this.lastDate = lastDate;
  }

  public long getLastCounted() {
    return lastCounted;
  }

  public void setLastCounted(int lastCounted) {
    this.lastCounted = lastCounted;
  }

  public long getDataListId() {
    return dataListId;
  }

  public void setDataListId(long dataListId) {
    this.dataListId = dataListId;
  }
}
