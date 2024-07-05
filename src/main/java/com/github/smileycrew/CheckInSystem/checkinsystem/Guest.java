package com.github.smileycrew.CheckInSystem.checkinsystem;

import java.time.Duration;
import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "guests")
public class Guest {
    @Id
    private String id;
    private LocalDateTime checkedInAt;
    private String email;
    private String fullName;
    private String phoneNumber;

    public Guest(String email, String fullName, String phoneNumber) {
        this.email = email.toLowerCase();
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.checkedInAt = LocalDateTime.now();
    }

    public String getId() { return id; }

    public Boolean getIsExpired() {
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(checkedInAt, now);
        
        Boolean isExpired = duration.toHours() >= 1;

        return isExpired;
    }

    public String getEmail() { return email.toLowerCase(); }

    public String getFullName() { return fullName; }

    public String getPhoneNumber() { return phoneNumber; }

    public LocalDateTime getCheckedInAt() { return checkedInAt; }

    public void setId(ObjectId id) { this.id = id.toHexString(); }

    public void setEmail(String email) { this.email = email.toLowerCase(); }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void setScheduledAt() { this.checkedInAt = LocalDateTime.now(); }
}
