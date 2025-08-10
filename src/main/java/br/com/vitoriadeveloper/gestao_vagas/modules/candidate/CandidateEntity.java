package br.com.vitoriadeveloper.gestao_vagas.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
// so you dont need to keep puting get and setters
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @NotBlank()
    // so the username does not contain space
    @Pattern(regexp = "\\S+", message = "The username field does not contain space")
    private String username;

    @Email(message = "Please enter a valid email address")
    private String email;

    @Length(min=6, max=60)
    private String password;
    private String description;
    private String curriculum;
    @CreationTimestamp
    private LocalDateTime createdAt;

}
