//Created by Mason Musmacker
//This is the backend of the program that allows all of the UI controllers to interact with the user database.

package teamproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DatabaseInterface {
    private static final String DATABASE_FOLDER = "src\\main\\resources\\UserDatabase\\";

    private DatabaseInterface() {
        // Private constructor to prevent instantiation
    }

    public static void addUserToDatabase(String username, String password, String email, String companyRole, String id) {
        try {
            // Create the "UserDatabase" directory if it doesn't exist
            new File(DATABASE_FOLDER).mkdirs();

            // Construct the full path for the user's file
            String userFilePath = DATABASE_FOLDER + username + ".txt";

            // Create a new user file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFilePath))) {
                // Write user information to the file
                writer.write("Username: " + username + "\n");
                writer.write("Password: " + password + "\n");
                writer.write("ID: " + id + "\n");
                writer.write("Name: " + "\n");
                writer.write("Email: " + email + "\n");
                writer.write("PhoneNumber: " + "\n");
                writer.write("Company Role: " + companyRole + "\n");
                writer.write("Skills: " + "\n");
                writer.write("EfficiencyData: " + "\n");
            }

            System.out.println("User added to the database: " + username);
        } catch (IOException e) {
            System.err.println("Error adding user to the database: " + e.getMessage());
        }
    }
    //When given a name and the data desired, this method will check for the user and return that bit of data if both the user and  data exist
    public static String getUserDataByName(String name, String requestedData) {
        try {
            // Iterate through each file in the database folder
            File folder = new File(DATABASE_FOLDER);
            File[] listOfFiles = folder.listFiles();
    
            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        String filename = file.getName();
                        String username = filename.substring(0, filename.lastIndexOf('.'));
    
                        // Check if the user's name matches the specified name
                        if (name.equalsIgnoreCase(getUserInfo(username, "Name"))) {
                            // If found, retrieve the requested data
                            return getUserInfo(username, requestedData);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error retrieving user data by name: " + e.getMessage());
        }
    
        // User not found or data not available
        return "Empty";
    }

    //Same as above, but uses the id for searching
    public static String getUserDataByID(String id, String requestedData) {
        try {
            // Iterate through each file in the database folder
            File folder = new File(DATABASE_FOLDER);
            File[] listOfFiles = folder.listFiles();
    
            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        String filename = file.getName();
                        String username = filename.substring(0, filename.lastIndexOf('.'));
    
                        // Check if the user's ID matches the specified ID
                        if (id.equalsIgnoreCase(getUserInfo(username, "ID"))) {
                            // If found, retrieve the requested data
                            return getUserInfo(username, requestedData);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error retrieving user data by ID: " + e.getMessage());
        }
    
        // User not found or data not available
        return "Empty";
    }

    //Checks to see if that user is already in the database
    public static boolean searchDatabaseForUser(String username) {
        File userFile = new File(DATABASE_FOLDER + username + ".txt");
        return userFile.exists();
    }

    //Lets you get a specific piece of data from a user. Say, their skills.
    public static String getUserInfo(String username, String requestedData) {
        try {
            if (searchDatabaseForUser(username)) {
                // Construct the full path for the user's file
                String userFilePath = DATABASE_FOLDER + username + ".txt";

                // Read the requested data from the user's file
                try (BufferedReader reader = new BufferedReader(new FileReader(userFilePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains(requestedData)) {
                            return line.substring(requestedData.length() + 2); // +2 to skip ": "
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error retrieving user information: " + e.getMessage());
        }
        return "Empty";
    }

    //This lets you replace a specific user's data with something new. Say, if they want to change their email
    public static void setUserInfo(String username, String fieldToUpdate, String newValue) {
        try {
            if (searchDatabaseForUser(username)) {
                // Construct the full path for the user's file
                String userFilePath = DATABASE_FOLDER + username + ".txt";

                // Create a temporary file to store updated data
                File tempFile = new File(userFilePath + ".temp");

                try (BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains(fieldToUpdate)) {
                            // Update the field with the new value
                            line = fieldToUpdate + ": " + newValue;
                        }
                        // Write the line to the temporary file
                        writer.write(line + "\n");
                    }
                }

                // Delete the original file and rename the temporary file
                new File(userFilePath).delete();
                tempFile.renameTo(new File(userFilePath));

                System.out.println("User information updated: " + username);
            }
        } catch (IOException e) {
            System.err.println("Error updating user information: " + e.getMessage());
        }
    }

    //This checks to see if any of the users in the database have a match for a desired datapoint.
    //For example, if I want to see if any user has the id I'm about to add, I can call this on the datatype "ID"
    public static boolean searchFor(String datatype, String data) {
        // Iterate through each file in the database folder
        File folder = new File(DATABASE_FOLDER);
        File[] listOfFiles = folder.listFiles();
    
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String filename = file.getName();
                    String username = filename.substring(0, filename.lastIndexOf('.'));
    
                    // Check if the user file contains the specified data
                    if (containsData(username, datatype, data)) {
                        return true;
                    }
                }
            }
        }
    
        // Data not found
        return false;
    }
    
    //Checks to see if X user contains the data Y in a designated field. 
    //For example, this could be used to check if the password given matches the one stored in their User data.
    private static boolean containsData(String username, String datatype, String data) {
        // Construct the full path for the user's file
        String userFilePath = DATABASE_FOLDER + username + ".txt";
    
        // Read the specified data from the user's file
        try (BufferedReader reader = new BufferedReader(new FileReader(userFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(datatype) && line.contains(data)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error searching for data: " + e.getMessage());
        }
    
        // Data not found in the user's file
        return false;
    }
}