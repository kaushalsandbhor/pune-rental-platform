
package com.rentalapi.dto.response;

public class UserResponse {

    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String mobile;
    private String role;

    public UserResponse(
            Long id,
            String fullName,
            String username,
            String role) {

        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
