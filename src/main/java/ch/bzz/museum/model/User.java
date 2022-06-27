package ch.bzz.museum.model;

/**
 * die klasse für einen Benutzer (user)
 */
public class User {
    private String userUUID;
    private String username; //denglish weil sonst der code nicht funktioniert, sorry :)
    private String password; //denglish weil sonst der code nicht funktioniert, sorry :)
    private String role; //denglish weil sonst der code nicht funktioniert, sorry :)

    public User(){
        setRole("guest");
    }

    /**
     * holt userUUID
     *
     * @return wert von userUUID
     */
    public String getUserUUID() {
        return userUUID;
    }

    /**
     * setzt userUUID
     * @param userUUID
     */
    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    /**
     * holt den username
     * @return wert von username
     */
    public String getUsername() {
        return username;
    }

    /**
     * setzt den username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * holt password
     * @return wert von password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setzt password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * holt die rolle
     * @return wert von role
     */
    public String getRole() {
        return role;
    }

    /**
     * setzt die rolle
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
