package lk.ijse.gdse.NoteCollector.service;



import lk.ijse.gdse.NoteCollector.customObj.UserResponse;
import lk.ijse.gdse.NoteCollector.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    void saveUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUser(String userId);
    UserResponse getSelectedUser(String userId);
    List<UserDTO> getAllUsers();
    UserDetailsService userDetailsService();

}
