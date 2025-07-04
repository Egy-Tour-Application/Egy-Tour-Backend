package com.example.egy_tour.service;

import com.example.egy_tour.dto.*;
import com.example.egy_tour.model.*;
import com.example.egy_tour.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TourGuideRepository tourGuideRepository;

    @Autowired
    private TourismSpotRepository tourismSpotRepository;

    @Autowired
    private ProgramRepository programRepository;



    @Transactional
    public Booking createBooking(CreateBookingDTO bookingDTO) {
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        TourGuide tourGuide = tourGuideRepository.findById(bookingDTO.getTourGuideId())
                .orElseThrow(() -> new EntityNotFoundException("TourGuide not found"));

        TourismSpot tourismSpot = tourismSpotRepository.findById(bookingDTO.getTourismSpotId())
                .orElseThrow(() -> new EntityNotFoundException("TourismSpot not found"));

        Booking booking = new Booking();
        booking.setPrice(bookingDTO.getPrice());
        booking.setStartTime(bookingDTO.getStartTime());
        booking.setEndTime(bookingDTO.getEndTime());
        booking.setUser(user);
        booking.setTourGuide(tourGuide);
        booking.setTourismSpot(tourismSpot);

        // Only set program if provided
        if (bookingDTO.getProgramId() != null) {
            Program program = programRepository.findById(bookingDTO.getProgramId())
                    .orElseThrow(() -> new EntityNotFoundException("Program not found"));
            booking.setProgram(program);
        }
        else{
            booking.setProgram(null);
        }

        return bookingRepository.save(booking);
    }

    @Transactional
    public List<Booking> getBookingByUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return bookingRepository.findByUser(user);

    }
    @Transactional
    public BookingResponseDTO checkForBooking(BookingRequestDTO bookingRequestDTO){
        User user = userRepository.findById(bookingRequestDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        TourGuide tourGuide = tourGuideRepository.findById(bookingRequestDTO.getTourGuideId())
                .orElseThrow(() -> new EntityNotFoundException("TourGuide not found"));

        Booking booking = bookingRepository.findByUserAndTourGuideAndStartTimeAndEndTime(
                user,
                tourGuide,
                bookingRequestDTO.getStartTime(),
                bookingRequestDTO.getEndTime()
        );

        if (booking == null) return null;

        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setPrice(booking.getPrice());
        dto.setStartTime(booking.getStartTime());
        dto.setEndTime(booking.getEndTime());
        return dto;
    }

    @Transactional
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        bookingRepository.delete(booking);
    }

    @Transactional
    public Booking updateBooking(Long id, UpdateBookingDTO updateBookingDTO) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        Program program = programRepository.findById(updateBookingDTO.getProgramId())
                .orElseThrow(() -> new EntityNotFoundException("Program not found"));

        booking.setProgram(program);
        return bookingRepository.save(booking);
    }

    public BookingResponseDTO mapToBookingResponse(Booking booking){
        TourGuide tourGuide = booking.getTourGuide();
        User user = tourGuide.getUser();
        UserResponseDTO userDTO = new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getNationality(),
                user.getGender()
        );

        TourGuideResponseDTO tourGuideDTO = new TourGuideResponseDTO(
                tourGuide.getId(),
                tourGuide.getPrice(),
                userDTO,
                null
        );


        TourismSpot tourismSpot = booking.getTourismSpot();
        TourismSpotResponseDTO tourismSpotDTO = new TourismSpotResponseDTO(
                tourismSpot.getId(),
                tourismSpot.getTitle(),
                tourismSpot.getDescription()
        );
        return new BookingResponseDTO(
                booking.getId(),
                booking.getPrice(),
                tourGuideDTO,
                booking.getStartTime(),
                booking.getEndTime(),
                tourismSpotDTO
        );

    }
}
