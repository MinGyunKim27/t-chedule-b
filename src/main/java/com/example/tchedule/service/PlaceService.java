package com.example.tchedule.service;

import com.example.tchedule.model.Place;
import com.example.tchedule.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Place addPlace(Place place) {
        return placeRepository.save(place);
    }
}
