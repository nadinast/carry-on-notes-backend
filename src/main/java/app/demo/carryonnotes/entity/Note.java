package app.demo.carryonnotes.entity;

import javax.persistence.*;

@Entity(name="note")
public class Note {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="name", length=70, nullable=false)
    private String name;

    @Column(name="date", length=25, nullable=false)
    private String date;

    @Column(name="hasDraft", nullable=false)
    private Boolean hasDraft;

    @Column(name="text", length=5000)
    private String text;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Note() {
    }

    public Note(String name, String date, Boolean hasDraft, String text) {
        this.name = name;
        this.date = date;
        this.hasDraft = hasDraft;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getHasDraft() {
        return hasDraft;
    }

    public void setHasDraft(Boolean hasDraft) {
        this.hasDraft = hasDraft;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
