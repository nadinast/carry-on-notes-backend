package app.demo.carryonnotes.pojo;

public class NoteDTO {
    private Long id;
    private String name;
    private String date;
    private Boolean hasDraft;
    private String text;

    public NoteDTO() {
    }

    public NoteDTO(Long id, String name, String date, Boolean hasDraft, String text) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.hasDraft = hasDraft;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
