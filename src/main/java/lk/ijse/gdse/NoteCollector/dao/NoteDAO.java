package lk.ijse.gdse.NoteCollector.dao;


import lk.ijse.gdse.NoteCollector.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDAO extends JpaRepository<NoteEntity,String> {



}
