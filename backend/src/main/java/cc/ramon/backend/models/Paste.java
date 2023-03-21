package cc.ramon.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "pastes")
public class Paste {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paste-generator")
    @GenericGenerator(name = "paste-generator", strategy = "cc.ramon.backend.generator.PasteIdGenerator")
    @Column(name = "id", length = 6, unique = true)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    @JsonBackReference
    private User user;
    private String title;
    @Lob
    @Column(name = "content", columnDefinition = "longtext")
    private String content;
    @Column(name = "creation_date", columnDefinition = "datetime default now()")
    private LocalDateTime creationDate = LocalDateTime.now();

    public Paste() {
    }

    //region getter/setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    //endregion

    @Override
    public String toString() {
        return "Paste{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
