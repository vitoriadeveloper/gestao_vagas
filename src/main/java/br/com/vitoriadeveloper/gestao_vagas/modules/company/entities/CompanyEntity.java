package br.com.vitoriadeveloper.gestao_vagas.modules.company.entities;

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
import lombok.Data;
@Entity(name = "company")
@Data
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank()
    // so the username does not contain space
    @Pattern(regexp = "\\S+", message = "The username field does not contain space")
    private String username;

    @Email(message = "Please enter a valid email address")
    private String email;

    @Length(min=6, max=15)
    private String password;

    private String website;
    private String name;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
