package com.university.booking.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking implements Serializable {

    private String id;
    private String personId;
    private String roomId;
    private String eventName;
    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int participantCount;
    private String contactPhone;
    private BookingStatus status;
    private LocalDateTime createdAt;

    public enum BookingStatus {
        DRAFT,
        UNDER_REVIEW,
        APPROVED,
        REJECTED
    }
}
