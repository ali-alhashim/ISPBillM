package com.ISPBillM.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Document("service")
public class ServiceEntity {

    @Id
    private String id;

    private String provider; // STC Mobily Zain

    private String type; // SIM Card, 5G Internet , Fiber Internet, MPLS IP-VPN, LandLine

    private BigDecimal monthlyFee;

    private String accountNumber;

    private String serialNumber;

    private LocalDate activationDate;

    private String notes;

    private String status;

    @DBRef
    private EmployeeEntity employee;

    @DBRef
    private BranchEntity branch;

    @DBRef
    private DepartmentEntity department;

    @DBRef
    private List<BillEntity> bills;


    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
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

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public BranchEntity getBranch() {
        return branch;
    }

    public void setBranch(BranchEntity branch) {
        this.branch = branch;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public List<BillEntity> getBills() {
        return bills;
    }

    public void setBills(List<BillEntity> bills) {
        this.bills = bills;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
