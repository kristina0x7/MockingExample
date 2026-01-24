package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    @DisplayName("Booking skapas med korrekta värden")
    void constructor_WithValidParameters_CreatesBooking() {

        String id = "booking1";
        String roomId = "room1";
        LocalDateTime start = LocalDateTime.of(2026, 1, 7, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 7, 11, 0);

        Booking booking = new Booking(id, roomId, start, end);

        assertThat(booking.getId()).isEqualTo(id);
        assertThat(booking.getRoomId()).isEqualTo(roomId);
        assertThat(booking.getStartTime()).isEqualTo(start);
        assertThat(booking.getEndTime()).isEqualTo(end);
    }
}