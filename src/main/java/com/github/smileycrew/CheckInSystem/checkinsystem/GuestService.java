package com.github.smileycrew.CheckInSystem.checkinsystem;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    public Guest addGuest(Guest newGuest) {
        String email = newGuest.getEmail();
        String fullName = newGuest.getFullName();
        String phoneNumber = newGuest.getPhoneNumber();

        Guest guest = new Guest(email, fullName, phoneNumber);

        guestRepository.insert(guest);

        return guest;
    }

    public Optional<Guest> getGuest(ObjectId id) {
        Optional<Guest> guest = guestRepository.findById(id);

        return guest;
    }
    
    public List<Guest> getGuests() {
        LocalDate today = LocalDate.now();
        
        List<Guest> guests = guestRepository.findAll().stream().filter((guest) -> guest.getScheduledAt().toLocalDate().equals(today)).collect(Collectors.toList());
        
        return guests;
    }
    
    public Guest updateGuest(String guestId, Guest updatedGuestData) {
        ObjectId id = new ObjectId(guestId);

        Optional<Guest> optionalGuest = getGuest(id);

        // TODO ADD SOME IF LOGIC HERE

        Guest guest = optionalGuest.get();

        guest.setEmail(updatedGuestData.getEmail());
        guest.setFullName(updatedGuestData.getFullName());
        guest.setPhoneNumber(updatedGuestData.getPhoneNumber());

        guestRepository.save(guest);

        return guest;
    }
}
