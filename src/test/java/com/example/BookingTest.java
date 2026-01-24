package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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

    @Test
    @DisplayName("Booking med null-värden - kastar exception")
    void constructor_WithNullValues_ThrowsException() {
        LocalDateTime validTime = LocalDateTime.now();

        assertThatThrownBy(() -> new Booking(null, "room", validTime, validTime))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Booking("id", null, validTime, validTime))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Booking("id", "room", null, validTime))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Booking("id", "room", validTime, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Booking med ogiltiga tider (slut före start) - kastar exception")
    void constructor_WithInvalidTimes_ThrowsException() {
        LocalDateTime start = LocalDateTime.of(2026, 1, 7, 11, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 7, 10, 0);

        assertThatThrownBy(() -> new Booking("id", "room", start, end))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Sluttid måste vara efter starttid");
    }
}