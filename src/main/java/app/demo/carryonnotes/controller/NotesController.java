package app.demo.carryonnotes.controller;

import app.demo.carryonnotes.pojo.NoteDTO;
import app.demo.carryonnotes.pojo.NoteVO;
import app.demo.carryonnotes.service.NotesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("https://carry-on-notes.herokuapp.com/")
@RequestMapping("/api/notes")
public class NotesController {

    private NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("/")
    public ResponseEntity<List<NoteVO>> getNotes() {
        List<NoteVO> notesVO = this.notesService.getAllNotes();
        return new ResponseEntity(notesVO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteVO> getNote(@PathVariable Long id) {
        NoteVO note = this.notesService.getNoteById(id);
        return new ResponseEntity(note, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<NoteVO> createNote(@RequestBody NoteDTO note) {
        NoteVO savedNoteVO = this.notesService.saveNote(note);
        return new ResponseEntity(savedNoteVO, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<NoteVO> updateNote(@RequestBody NoteDTO note) {
        NoteVO updateNoteVO = this.notesService.updateNote(note);
        return new ResponseEntity(updateNoteVO, HttpStatus.OK);
    }
}
