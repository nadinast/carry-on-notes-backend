package app.demo.carryonnotes.service;

import app.demo.carryonnotes.entity.Note;
import app.demo.carryonnotes.pojo.NoteDTO;
import app.demo.carryonnotes.pojo.NoteVO;
import app.demo.carryonnotes.repository.NotesRepository;
import app.demo.carryonnotes.repository.UserRepository;
import app.demo.carryonnotes.utils.UserContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotesServiceImpl implements NotesService {

    private final NotesRepository notesRepository;
    private final UserRepository userRepository;
    private final UserContext userContext;

    public NotesServiceImpl(NotesRepository notesRepository, UserRepository userRepository, UserContext userContext) {
        this.notesRepository = notesRepository;
        this.userRepository = userRepository;
        this.userContext = userContext;
    }

    @Override
    public List<NoteVO> getAllNotes() {
        List<Note> notesForUser = this.notesRepository.findAllByUserId(userContext.getLoggedInUserId());
        return notesForUser
                .stream()
                .map(NoteVO::new)
                .collect(Collectors.toList());
    }

    @Override
    public NoteVO saveNote(NoteDTO note) {
        Note noteToSave = new Note(note.getName(), note.getDate(), note.getHasDraft(), note.getText());
        noteToSave.setUser(this.userRepository.findByEmail(this.userContext.getLoggedInUserName()));
        Note savedNote = this.notesRepository.save(noteToSave);
        return new NoteVO(savedNote);
    }

    @Override
    public NoteVO updateNote(NoteDTO note) {
        Optional<Note> optionalNoteToUpdate = this.notesRepository.findById(note.getId());
        if(optionalNoteToUpdate.isPresent()) {
            Note noteToUpdate = this.updateFields(optionalNoteToUpdate.get(), note);
            Note updatedNote = this.notesRepository.save(noteToUpdate);
            return new NoteVO(updatedNote);
        }
        return null;
    }

    @Override
    public NoteVO getNoteById(Long id) {
        Note note = this.notesRepository.findById(id).orElse(new Note());
        return new NoteVO(note);
    }

    private Note updateFields(Note noteToUpdate, NoteDTO noteDTO) {
        noteToUpdate.setName(noteDTO.getName());
        noteToUpdate.setDate(noteDTO.getDate());
        noteToUpdate.setHasDraft(noteDTO.getHasDraft());
        noteToUpdate.setText(noteDTO.getText());
        return noteToUpdate;
    }
}
