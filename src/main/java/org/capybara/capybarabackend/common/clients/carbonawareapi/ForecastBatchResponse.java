package org.capybara.capybarabackend.common.clients.carbonawareapi;

import java.time.OffsetDateTime;

public class ForecastBatchResponse {

    private OptimalDataPoints[] optimalDataPoints;

    public OptimalDataPoints[] getOptimalDataPoints() {
        return optimalDataPoints;
    }

    public void setOptimalDataPoints(OptimalDataPoints[] optimalDataPoints) {
        this.optimalDataPoints = optimalDataPoints;
    }

    public static class OptimalDataPoints {

        private String location;

        private OffsetDateTime timestamp;

        private Integer duration;

        private Double value;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public OffsetDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(OffsetDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

    }

}
