package com.github.smileycrew.CheckInSystem.checkinsystem;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.stream.Collectors;

// TODO FIND A BETTER PLACE TO PUT EXCEPTIONS
class AlreadyCheckedInException extends Exception {
    public AlreadyCheckedInException(String message) {
        super(message);
    }
}

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    public Guest addGuest(Guest newGuestData) throws AlreadyCheckedInException {
        String email = newGuestData.getEmail();

        Boolean isGuestCheckedIn = isCheckedIn(email);

        if (isGuestCheckedIn) {
            throw new AlreadyCheckedInException("Guest is already checked in.");
        }
        
        String fullName = newGuestData.getFullName();
        String phoneNumber = newGuestData.getPhoneNumber();

        Guest guest = new Guest(email, fullName, phoneNumber);

        guest = guestRepository.insert(guest);

        return guest;
    }
    // get all guests that are checked in today and not expired
    public List<Guest> getGuests() {
        List<Guest> allGuests = guestRepository.findAll();
        List<Guest> filtedGuests = handleFilterGuests(allGuests);
        
        return filtedGuests;
    }
    // filter guests by today and not expired
    public List<Guest> handleFilterGuests(List<Guest> guests) {
        List<Guest> filteredGuests = filterGuestsByToday(guests);
        
        filteredGuests = filterGuestsByNonExpired(filteredGuests);

        return filteredGuests;
    }

    public List<Guest> filterGuestsByToday(List<Guest> guests) {
        LocalDate today = LocalDate.now();

        List<Guest> todaysGuests = guests.stream().filter((guest) -> guest.getCheckedInAt().toLocalDate().equals(today)).collect(Collectors.toList());

        return todaysGuests;
    }

    public List<Guest> filterGuestsByNonExpired(List<Guest> guests) {
        List<Guest> nonExpiredGuests = guests.stream().filter((guest) -> !guest.getIsExpired()).collect(Collectors.toList());

        return nonExpiredGuests;
    }
    
    public Boolean isCheckedIn(String email) {
        List<Guest> guests = getGuests();

        List<Guest> guestsWithMatchingEmail = guests.stream().filter((guest) -> guest.getEmail().equals(email.toLowerCase().toString())).collect(Collectors.toList());

        return !guestsWithMatchingEmail.isEmpty();
    }
    
    // UNUSED SERVICES / CAN BE USED FOR ADMIN LOGIN LATER
    public Optional<Guest> getGuest(ObjectId id) {
        Optional<Guest> guest = guestRepository.findById(id);
    
        return guest;
    }
    // unused dservice
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
