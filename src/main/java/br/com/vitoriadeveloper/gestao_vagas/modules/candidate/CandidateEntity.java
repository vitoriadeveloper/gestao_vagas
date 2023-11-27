package br.com.vitoriadeveloper.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
// so you dont need to keep puting get and setters
import lombok.Data;

@Data
public class CandidateEntity {

    private UUID id;
    private String name;

    @NotBlank()
    // so the username does not contain space
    @Pattern(regexp = "\\S+", message = "The username field does not contain space")
    private String username;

    @Email(message = "Please enter a valid email address")
    private String email;

    @Length(min=6, max=15)
    private String password;
    private String description;
    private String curriculum;

}
