package be.intecbrussel.model;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@With
@Data

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

    @Column(name = "email", nullable = false, unique = true)
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
}
