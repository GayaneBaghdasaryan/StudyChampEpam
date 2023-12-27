package org.capston.project.epam.dto.accounts;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignupDto {
    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private String email;
}
