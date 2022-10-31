package org.capybara.capybarabackend.github.workflow.run.common;

import java.time.LocalDateTime;

// TODO: Move it to inner class?
public class ScheduleData {
    private LocalDateTime startDateTime; // TODO: remove
    private LocalDateTime endDateTime; // TODO: remove

    private int durationInMinutes; // TODO: remove / replace?

    private String location; // TODO: merge

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
