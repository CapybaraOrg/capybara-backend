package org.capybara.capybarabackend.common.clients.carbonawareapi;

import java.time.LocalDateTime;

public class ForecastBatchRequest {

    private LocalDateTime requestedAt;

    private String location;

    private LocalDateTime dataStartAt;

    private LocalDateTime dataEndAt;

    private Integer windowSize;

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDataStartAt() {
        return dataStartAt;
    }

    public void setDataStartAt(LocalDateTime dataStartAt) {
        this.dataStartAt = dataStartAt;
    }

    public LocalDateTime getDataEndAt() {
        return dataEndAt;
    }

    public void setDataEndAt(LocalDateTime dataEndAt) {
        this.dataEndAt = dataEndAt;
    }

    public Integer getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(Integer windowSize) {
        this.windowSize = windowSize;
    }

}
