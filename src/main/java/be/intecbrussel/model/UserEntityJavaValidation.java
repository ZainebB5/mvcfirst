package be.intecbrussel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@Entity


public class UserEntityJavaValidation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "f_name")
    private String fName;
    @Column(name = "m_name")
    private String mName;

    @NotBlank
    @Column(name = "l_name")
    private String lName;

    @Column(name = "email", nullable = false, unique = true , length = 1000)
    private String email;


    @Min(6)
    @Column(name = "hashed_password", nullable = false, length = 1000)
    private String hashedPassword;

    @Column(name = "validation", nullable = false, length = 8)
    private String validation;

    @Column(name = "active", nullable = false)
    private Boolean active = false;


}
