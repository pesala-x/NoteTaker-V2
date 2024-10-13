package lk.ijse.gdse.NoteCollector.dao;

import lk.ijse.gdse.NoteCollector.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDAO extends JpaRepository<UserEntity,String> {
    UserEntity getUserEntityByUserId(String userId);

}
