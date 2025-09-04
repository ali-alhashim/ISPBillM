package com.ISPBillM.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ServiceDto {

    private String id;

    private String provider; // STC Mobily Zain

    private String type; // SIM Card, 5G Internet , Fiber Internet, MPLS IP-VPN, LandLine

    private BigDecimal monthlyFee;

    private String accountNumber;

    private String serialNumber;

    private LocalDate activationDate;

    private String status;

    private String assignedToId;

    private String notes;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getProvider() {
        return provider;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(String assignedToId) {
        this.assignedToId = assignedToId;
    }
}
