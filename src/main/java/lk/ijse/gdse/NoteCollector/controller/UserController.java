package lk.ijse.gdse.NoteCollector.controller;



import lk.ijse.gdse.NoteCollector.customObj.UserResponse;
import lk.ijse.gdse.NoteCollector.dto.impl.UserDTO;
import lk.ijse.gdse.NoteCollector.exception.DataPersistFailedException;
import lk.ijse.gdse.NoteCollector.exception.UserNotFoundException;
import lk.ijse.gdse.NoteCollector.service.UserService;
import lk.ijse.gdse.NoteCollector.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/vi/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;
    //save user
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //consume - clientge peththe
    //produce - server ek visin
    public ResponseEntity<String>

    saveUser(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") MultipartFile profilePic) {

        //Handle Profile Picture
        try {
            //byte [] imageByteCollection = profilePic.getBytes();
            String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic);
            // build the user object
            UserDTO buildUserDTO = new UserDTO();
            buildUserDTO.setFirstName(firstName);
            buildUserDTO.setLastName(lastName);
            buildUserDTO.setEmail(email);
            buildUserDTO.setPassword(password);
            buildUserDTO.setProfilePic(base64ProfilePic);
            //send to the service layer
            userService.saveUser(buildUserDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable ("id") String userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getSelectedUser(@PathVariable ("id") String userId){
        return userService.getSelectedUser(userId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PatchMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id,
                                             @RequestPart("updateFirstName") String updateFirstName,
                                             @RequestPart("updateLastName") String updateLastName,
                                             @RequestPart("updateEmail") String updateEmail,
                                             @RequestPart("updatePassword") String updatePassword,
                                             @RequestPart("updateProfilePic") MultipartFile updateProfilePic
    ){
        try {
            String updateBase64ProfilePic = AppUtil.toBase64ProfilePic(updateProfilePic);
            var updateUser = new UserDTO();
            updateUser.setUserId(id);
            updateUser.setFirstName(updateFirstName);
            updateUser.setLastName(updateLastName);
            updateUser.setEmail(updateEmail);
            updateUser.setPassword(updatePassword);
            updateUser.setProfilePic(updateBase64ProfilePic);
            userService.updateUser(updateUser);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (UserNotFoundException e){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

