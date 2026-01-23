package com.simplemovie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplemovie.entity.Booking;
import com.simplemovie.entity.Show;
import com.simplemovie.repository.BookingRepository;
import com.simplemovie.repository.ShowRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowRepository showRepository;   // ✅ THIS WAS MISSING

    // Book tickets
    public Booking bookTickets(Booking booking) {

        Show show = showRepository.findById(
                booking.getShow().getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));

        if (booking.getSeatsBooked() > show.getAvailableSeats()) {
            throw new RuntimeException("Not enough seats available");
        }

        show.setAvailableSeats(
                show.getAvailableSeats() - booking.getSeatsBooked());

        showRepository.save(show);

        return bookingRepository.save(booking);
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Cancel booking
    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Show show = booking.getShow();

        // Restore seats
        show.setAvailableSeats(
                show.getAvailableSeats() + booking.getSeatsBooked());

        showRepository.save(show);   // ✅ NOW WORKS

        bookingRepository.deleteById(bookingId);
    }
}
