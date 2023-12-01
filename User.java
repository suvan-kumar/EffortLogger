//Created by Mason Musmacker
//Used to store a "currentUser" after login. Useful for verification and security in both the login page AND teambuilder data compartimentalization
package teamproject;

public class User {
    private String username;
    // Add other user-related fields as needed
    //used to instantiate the current user.. perhaps not required if I made this static but I prefer it this way
    public User(String username) {
        this.username = username;
    }
    //returns the username of the current user
    public String getUsername() {
        return username;
    }
}
