package com.github.smileycrew.CheckInSystem.checkinsystem;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Duration;
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

    public Boolean getIsExpired() {
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(scheduledAt, now);
        
        Boolean isExpired = duration.toHours() >= 1;

        return isExpired;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setId(ObjectId id) {
        this.id = id.toHexString();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setScheduledAt() {
        this.scheduledAt = LocalDateTime.now();
    }
}
