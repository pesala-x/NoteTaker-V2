package lk.ijse.gdse.NoteCollector.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "notes")
public class NoteEntity {

    @Id
    private String noteId;
    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private UserEntity user;
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String createDate;
}
