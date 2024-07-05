package com.github.smileycrew.CheckInSystem.checkinsystem;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// TODO CLEAN UP THE PATH VARIABLES TO MATCH BY ID
@RestController
@RequestMapping("/api/guests")
public class GuestController {
    @Autowired
    private GuestService guestService;

    @PostMapping
    public ResponseEntity<Guest> create(@RequestBody Guest newGuestData) {
        try {
            Guest guest = guestService.addGuest(newGuestData);
            
            return new ResponseEntity<Guest>(guest, HttpStatus.CREATED);
        }
        catch (Exception ex) {
            System.out.println("Guest already exists.");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // TODO: this simple compared to how i have it now
        // return ResponseEntity.created(location).body(guest);
    }

    @GetMapping
    public ResponseEntity<List<Guest>> guests() {
        List<Guest> guests = guestService.getGuests();
        
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Guest>> guest(@PathVariable ObjectId id) {
        Optional<Guest> guest = guestService.getGuest(id);

        return new ResponseEntity<Optional<Guest>>(guest, HttpStatus.OK);
    }

    @PutMapping("/{guestId}")
    public ResponseEntity<Guest> update(@PathVariable String guestId, @RequestBody Guest updatedGuestData) {
        
        Guest guest = guestService.updateGuest(guestId, updatedGuestData);

        return new ResponseEntity<Guest>(guest, HttpStatus.OK);
    }
}