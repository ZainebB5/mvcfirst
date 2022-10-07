package be.intecbrussel.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@Entity

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "f_name")
    private String fName;

    @Column(name = "m_name")
    private String mName;

    @Column(name = "l_name")
    private String lName;

    @Column(name = "email", nullable = false, unique = true , length = 1000)
    private String email;

    @Column(name = "hashed_password", nullable = false, length = 1000)
    private String hashedPassword;

    @Column(name = "validation", nullable = false, length = 8)
    private String validation;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", mName='" + mName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", validation='" + validation + '\'' +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(email, user.email) && Objects.equals(hashedPassword, user.hashedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, hashedPassword);
    }
}
