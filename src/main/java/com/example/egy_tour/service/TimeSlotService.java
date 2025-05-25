package com.example.egy_tour.service;

import com.example.egy_tour.dto.CreateTimeSlotDTO;
import com.example.egy_tour.dto.SearchTimeSlotsDTO;
import com.example.egy_tour.model.Address;
import com.example.egy_tour.model.TimeSlot;
import com.example.egy_tour.model.TourGuide;
import com.example.egy_tour.repository.AddressRepository;
import com.example.egy_tour.repository.TimeSlotRepository;
import com.example.egy_tour.repository.TourGuideRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private TourGuideRepository tourGuideRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public TimeSlot createTimeSlot(Long tourGuideId, CreateTimeSlotDTO createTimeSlotDTO) {
        TourGuide tourGuide = tourGuideRepository.findById(tourGuideId)
                .orElseThrow(() -> new EntityNotFoundException("Tour guide not found"));

        Address address = addressRepository.findById(createTimeSlotDTO.getAddressId())
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartTime(createTimeSlotDTO.getStartTime());
        timeSlot.setEndTime(createTimeSlotDTO.getEndTime());
        timeSlot.setTourGuide(tourGuide);
        timeSlot.setAddress(address);

        return timeSlotRepository.save(timeSlot);
    }

    public List<TimeSlot> getTimeSlotsByTourGuide(Long tourGuideId) {
        TourGuide tourGuide = tourGuideRepository.findById(tourGuideId)
                .orElseThrow(() -> new EntityNotFoundException("Tour guide not found"));
        return timeSlotRepository.findByTourGuide(tourGuide);
    }

    @Transactional
    public TimeSlot updateTimeSlot(Long timeSlotId, CreateTimeSlotDTO updateTimeSlotDTO) {
        TimeSlot timeSlot = timeSlotRepository.findById(timeSlotId)
                .orElseThrow(() -> new EntityNotFoundException("Time slot not found"));

        Address address = addressRepository.findById(updateTimeSlotDTO.getAddressId())
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        timeSlot.setStartTime(updateTimeSlotDTO.getStartTime());
        timeSlot.setEndTime(updateTimeSlotDTO.getEndTime());
        timeSlot.setAddress(address);

        return timeSlotRepository.save(timeSlot);
    }

    @Transactional
    public void deleteTimeSlot(Long timeSlotId) {
        TimeSlot timeSlot = timeSlotRepository.findById(timeSlotId)
                .orElseThrow(() -> new EntityNotFoundException("Time slot not found"));
        timeSlotRepository.delete(timeSlot);
    }

    public List<TourGuide> findAvailableTourGuides(SearchTimeSlotsDTO searchDTO) {
        List<TimeSlot> availableTimeSlots = timeSlotRepository.findAvailableTimeSlots(
                searchDTO.getStartTime(),
                searchDTO.getEndTime());

        // Get unique tour guides from the available time slots
        return availableTimeSlots.stream()
                .map(TimeSlot::getTourGuide)
                .distinct()
                .collect(Collectors.toList());
    }
}