package lk.ijse.gdse.NoteCollector.service;



import lk.ijse.gdse.NoteCollector.customObj.NoteResponse;
import lk.ijse.gdse.NoteCollector.dto.impl.NoteDTO;

import java.util.List;

public interface NoteService {
    void saveNote(NoteDTO noteDTO);
    void updateNote(String noteId,NoteDTO noteDTO);
    void deleteNote(String noteId);
    NoteResponse getSelectedNote(String noteId);
    List<NoteDTO> getAllNotes();
}
