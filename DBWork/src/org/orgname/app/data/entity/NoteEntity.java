package org.orgname.app.data.entity;

public class NoteEntity {
    private int id;
    private String text;
    private int idusers;

    public NoteEntity(int id, String text, int idusers) {
        this.id = id;
        this.text = text;
        this.idusers = idusers;
    }

    public NoteEntity(String text, int idusers) {
        this(-1, text, idusers);
    }

    @Override
    public String toString() {
        return "NoteEntity{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", idusers=" + idusers +
                "}\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }
}
