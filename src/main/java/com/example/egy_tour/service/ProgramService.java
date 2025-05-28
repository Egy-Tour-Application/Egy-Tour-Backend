package com.example.egy_tour.service;

import com.example.egy_tour.dto.CreateBookingDTO;
import com.example.egy_tour.dto.CreateProgramDTO;
import com.example.egy_tour.dto.ProgramResponseDTO;
import com.example.egy_tour.dto.UpdateProgramDTO;
import com.example.egy_tour.model.*;
import com.example.egy_tour.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    TourismSpotRepository tourismSpotRepository;
    @Autowired
    private TourGuideRepository tourGuideRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;


    public Program createProgram(Long userId, CreateProgramDTO programDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Program program = new Program();
        program.setName(programDTO.getName());
        program.setDescription(programDTO.getDescription());
        program.setUser(user);

        // Set program and save it first so it gets an ID
        program = programRepository.save(program);

        // Fetch and assign existing bookings by ID
        List<Booking> bookings = new ArrayList<>();
        for (Long bookingId : programDTO.getBookingIds()) {
            Booking booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new RuntimeException("Booking not found: " + bookingId));

            booking.setProgram(program); // set the foreign key
            bookings.add(booking);
        }

        // Save the updated bookings
        bookingRepository.saveAll(bookings);

        // Set the program's bookings list
        program.setBookings(bookings);

        return program;
    }


    public List<ProgramResponseDTO> getProgramByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Program> programs = programRepository.findByUser(user);

        return programs.stream().map(program -> {
            List<Long> bookingIds = program.getBookings()
                    .stream()
                    .map(booking -> booking.getId())
                    .toList();

            return new ProgramResponseDTO(
                    program.getId(),
                    program.getName(),
                    program.getDescription(),
                    bookingIds
            );
        }).toList();
    }

    @Transactional
    public void deleteProgram(Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new EntityNotFoundException("Program not found"));

        // Set program in each booking to null
        if (program.getBookings() != null) {
            for (Booking booking : program.getBookings()) {
                booking.setProgram(null);
            }
        }

        programRepository.delete(program);
    }


    public Program updateProgram(Long programId, UpdateProgramDTO programDTO) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program not found"));


        List<Booking> currentBookings = program.getBookings();
        if (currentBookings != null && !currentBookings.isEmpty()) {
            for (Booking booking : currentBookings) {
                booking.setProgram(null);
            }
            bookingRepository.saveAll(currentBookings);
        }


        List<Booking> newBookings = new ArrayList<>();
        for (Long bookingId : programDTO.getBookingIds()) {
            Booking booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new RuntimeException("Booking not found: " + bookingId));

            booking.setProgram(program);
            newBookings.add(booking);
        }

        bookingRepository.saveAll(newBookings);


        program.setBookings(newBookings);

        return programRepository.save(program);
    }
}
