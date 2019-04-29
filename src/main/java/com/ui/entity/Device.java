package com.ui.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "macAdressCollection")
public class Device {

    private String name;
    private Long startSession;
    private Long endSession;

    public Device(String name, Long startSession, Long endSession) {
        this.name = name;
        this.startSession = startSession;
        this.endSession = endSession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStartSession() {
        return startSession;
    }

    public void setStartSession(Long startSession) {
        this.startSession = startSession;
    }

    public Long getEndSession() {
        return endSession;
    }

    public void setEndSession(Long endSession) {
        this.endSession = endSession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(name, device.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}