package cc.ramon.backend.models;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "pastes")
public class Paste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 6, unique = true)
    private String id;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    private String title;
    private String content;
    private Time creationDate;

    public Paste(String id, User user, String title, String content, Time creationDate) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
    }

    public Paste(){}

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

    public Time getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Time creationDate) {
        this.creationDate = creationDate;
    }
}
