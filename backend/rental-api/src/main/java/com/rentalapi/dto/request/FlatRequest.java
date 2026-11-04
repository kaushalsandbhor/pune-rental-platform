
package com.rentalapi.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class FlatRequest {

    @NotBlank(message = "Flat number is required")
    @Size(max = 20, message = "Flat number cannot exceed 20 characters")
    private String flatNumber;

    @PositiveOrZero(message = "Floor number cannot be negative")
    private Integer floorNumber;

    @NotBlank(message = "Flat type is required")
    private String flatType;

    @NotNull(message = "Monthly rent is required")
    @Positive(message = "Monthly rent must be greater than zero")
    private BigDecimal monthlyRent;

    @PositiveOrZero(message = "Deposit cannot be negative")
    private BigDecimal deposit;

    private boolean parking;

    @NotBlank(message = "Status is required")
    @Pattern(
            regexp = "AVAILABLE|OCCUPIED|MAINTENANCE",
            message = "Invalid flat status"
    )
    private String status;

    // Getters and Setters

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getFlatType() {
        return flatType;
    }

    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
