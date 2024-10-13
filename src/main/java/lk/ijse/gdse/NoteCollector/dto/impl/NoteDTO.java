package lk.ijse.gdse.NoteCollector.dto.impl;


import lk.ijse.gdse.NoteCollector.customObj.NoteResponse;
import lk.ijse.gdse.NoteCollector.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteDTO implements SuperDTO, NoteResponse {
    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String createDate;
    private  String userId;
}
