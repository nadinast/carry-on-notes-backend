package app.demo.carryonnotes.pojo;

import app.demo.carryonnotes.entity.Note;

public class NoteVO {
    private Long id;
    private String name;
    private String date;
    private Boolean hasDraft;
    private String text;

    public NoteVO(Long id, String name, String date, Boolean hasDraft, String text) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.hasDraft = hasDraft;
        this.text = text;
    }

    public NoteVO(Note note) {
        this.id = note.getId();
        this.name = note.getName();
        this.date = note.getDate();
        this.hasDraft = note.getHasDraft();
        this.text = note.getText();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Boolean getHasDraft() {
        return hasDraft;
    }

    public String getText() {
        return text;
    }
}
