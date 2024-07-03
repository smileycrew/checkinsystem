package com.github.smileycrew.CheckInSystem.checkinsystem;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

@Document(collection = "guests")
public class Guest {
    @Id
    private String id;
    private String email;
    private String fullName;
    private String phoneNumber;
    private LocalDateTime scheduledAt;

    public Guest(String email, String fullName, String phoneNumber) {
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.scheduledAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id.toHexString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt() {
        this.scheduledAt = LocalDateTime.now();
    }
}
