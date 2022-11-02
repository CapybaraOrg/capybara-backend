package org.capybara.capybarabackend.common.clients.carbonawareapi;

import java.time.OffsetDateTime;

public class ForecastBatchRequest {

    private OffsetDateTime requestedAt;

    private String location;

    private OffsetDateTime dataStartAt;

    private OffsetDateTime dataEndAt;

    private Integer windowSize;

    public OffsetDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(OffsetDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public OffsetDateTime getDataStartAt() {
        return dataStartAt;
    }

    public void setDataStartAt(OffsetDateTime dataStartAt) {
        this.dataStartAt = dataStartAt;
    }

    public OffsetDateTime getDataEndAt() {
        return dataEndAt;
    }

    public void setDataEndAt(OffsetDateTime dataEndAt) {
        this.dataEndAt = dataEndAt;
    }

    public Integer getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(Integer windowSize) {
        this.windowSize = windowSize;
    }

}
