package app.demo.carryonnotes.service;

import app.demo.carryonnotes.entity.Note;
import app.demo.carryonnotes.pojo.NoteDTO;
import app.demo.carryonnotes.pojo.NoteVO;

import java.util.List;

public interface NotesService {

    List<NoteVO> getAllNotes();

    NoteVO saveNote(NoteDTO note);

    NoteVO updateNote(NoteDTO note);
}
