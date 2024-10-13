package lk.ijse.gdse.NoteCollector.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {


    public static String createNote(){
        return "NOTE "+ UUID.randomUUID().toString();
    }

    public static String createUserId(){
        return "USER-"+UUID.randomUUID();
    }

    public static String toBase64ProfilePic(MultipartFile profilePic){
        String profileBase = null;
        try {
            byte []  profilePicBase = profilePic.getBytes();
            return Base64.getEncoder().encodeToString(profilePicBase);
        }catch (Exception e){
            e.printStackTrace();
        }
        return profileBase;
    }
}



//mapping
//note details