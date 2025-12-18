package com.jts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.jts.service.ListingService;
import com.jts.entity.Listing;


@RestController
@RequestMapping("/api")
public class ListingController {

    @Autowired
    private ListingService listingService;
    @RequestMapping("/listings")

    public List<Listing> getAllListings() {
        return listingService.findAllListings();
    }

    @GetMapping("/listings/{id}")
    public Listing getListingById(@PathVariable int id) {
        return listingService.findById(id);
    }

    @PostMapping("/listings")
    public Listing createListing(@RequestBody Listing listing) {
        listingService.saveListing(listing);
        return listing;
    }

    @PutMapping("/listings/{id}")
    public Listing updateListing(@PathVariable int id, @RequestBody Listing updatedListing) {
        updatedListing.setId(id);
        return listingService.updateListing(updatedListing);
    }

    @DeleteMapping("/listings/{id}")
    public Listing deleteListing(@PathVariable int id) {
        Listing existingListing = listingService.findById(id);
        listingService.deleteListing(id);
        return existingListing;
        
    }
}