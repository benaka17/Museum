package ch.bzz.museum.data;

import ch.bzz.museum.model.User;
import ch.bzz.museum.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * zuständig für die Daten der Benutzer (user)
 */
public class UserData {

    private static final UserData instance = new UserData();

    /**
     * findet einen Benutzer
     * @param username
     * @param password
     * @return user
     */
    public static User findUser(String username, String password){
        User user = new User();
        List<User> userList = readJson();

        for(User entry : userList){
            if (entry.getUsername().equals(username) && entry.getPassword().equals(password)){
                user = entry;
            }
        }
        return user;
    }

    /**
     * liest das JSON der benutzer, gibt es als list zurück
     * @return userlist
     */
    private static List<User> readJson(){
        List<User> userList = new ArrayList<>();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(Config.getProperty("userJSON")));
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users){
                userList.add(user);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return userList;
    }


}
