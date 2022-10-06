package be.intecbrussel.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


@Entity
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;

    @ManyToMany
    @JoinTable(name = "chats",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "receiver_id"))
    private Set<UserEntity> receivers = new LinkedHashSet<>();

    @Column(name = "subject", nullable = false)
    private String subject;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "views_count")
    private Integer viewsCount;

    @Column(name = "likes_count")
    private Integer likesCount;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id=" + id +
                ", sender=" + sender +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", viewsCount=" + viewsCount +
                ", likesCount=" + likesCount +
                ", active=" + active +
                '}';
    }
}
