package com.jts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jts.entity.Listing;
import com.jts.repository.ListingRepository;

@Service
public class ListingService {
    @Autowired
    private ListingRepository listingRepository;

    public void saveListing(Listing listing) {
        listingRepository.save(listing);
    }

    public Listing findById(int id) {
        Optional<Listing> listing = listingRepository.findById(id);
        if (listing.isEmpty()) {
            throw new RuntimeException("Listing not found with id: " + id);
        }
        return listing.get();
    }

    public List<Listing> findAllListings() {
        return listingRepository.findAll();
    }

    public Listing updateListing(Listing listing) {
        Optional<Listing> existingListing = listingRepository.findById(listing.getId());
        if (existingListing.isPresent()) {
            Listing updatedListing = existingListing.get();
            updatedListing.setTitle(listing.getTitle());
            updatedListing.setDescription(listing.getDescription());
            updatedListing.setPrice(listing.getPrice());
            return listingRepository.save(updatedListing);
        }
        throw new RuntimeException("Listing not found with id: " + listing.getId());
    }

    public void deleteListing(int id) {
        Optional<Listing> listing = listingRepository.findById(id);
        if (listing.isEmpty()) {
            throw new RuntimeException("Listing not found with id: " + id);
        }
        listingRepository.deleteById(id);
    }   
}
