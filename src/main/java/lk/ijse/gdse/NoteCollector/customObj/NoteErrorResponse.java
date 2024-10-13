package lk.ijse.gdse.NoteCollector.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteErrorResponse implements NoteResponse, Serializable {
    private  int errorCode;
    private  String errorMessage;
}
