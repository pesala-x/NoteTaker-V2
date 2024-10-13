package lk.ijse.gdse.NoteCollector.service;


import jakarta.transaction.Transactional;
import lk.ijse.gdse.NoteCollector.customObj.UserErrorResponse;
import lk.ijse.gdse.NoteCollector.customObj.UserResponse;
import lk.ijse.gdse.NoteCollector.dao.UserDAO;
import lk.ijse.gdse.NoteCollector.dto.impl.UserDTO;
import lk.ijse.gdse.NoteCollector.entity.UserEntity;
import lk.ijse.gdse.NoteCollector.exception.DataPersistFailedException;
import lk.ijse.gdse.NoteCollector.exception.UserNotFoundException;
import lk.ijse.gdse.NoteCollector.util.AppUtil;
import lk.ijse.gdse.NoteCollector.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private final UserDAO userDAO;

    @Autowired
    private  final Mapping mapping;

    @Override
    public void saveUser(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.createUserId());
        UserEntity savedUser =
                userDAO.save(mapping.convertToUserEntity(userDTO));
        if(savedUser == null && savedUser.getUserId() == null ) {
            throw new DataPersistFailedException("Cannot data saved");
        }
    }
    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<UserEntity> tmpUser = userDAO.findById(userDTO.getUserId());
        if(!tmpUser.isPresent()){
            throw  new UserNotFoundException("User Not Found");
        }else {
            tmpUser.get().setFirstName(userDTO.getFirstName());
            tmpUser.get().setLastName(userDTO.getLastName());
            tmpUser.get().setEmail(userDTO.getEmail());
            tmpUser.get().setPassword(userDTO.getPassword());
            tmpUser.get().setProfilePic(userDTO.getProfilePic());
        }
    }
    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> selectedUserId = userDAO.findById(userId);
        if(!selectedUserId.isPresent()){
            throw new UserNotFoundException("User not found");
        }else {
            userDAO.deleteById(userId);
        }
    }
    @Override
    public UserResponse getSelectedUser(String userId) {
        if(userDAO.existsById(userId)){
            UserEntity userEntityByUserId = userDAO.getUserEntityByUserId(userId);
            return mapping.convertToUserDTO(userEntityByUserId);
            }else {
                return new UserErrorResponse(0, "User not found");
        }
    }
    @Override
    public List<UserDTO> getAllUsers() {
      List<UserEntity> getAllUsers = userDAO.findAll();
      return  mapping.convertUserToDTOList(getAllUsers);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return email ->

                userDAO.findByEmail(email)
                        .orElseThrow(()-> new UserNotFoundException("User Not found"));
    }

}
