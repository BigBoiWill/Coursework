import org.sqlite.SQLiteConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    public static Connection db = null;
public static void main(STring[] args){
    openDatabase("UserDatabase");
    getUsers(); //im making a change so i can upload this to github
}
    public static void main(String[] args) {

        openDatabase("UserDatabase.db");

        try {

            PreparedStatement ps = db.prepareStatement("SELECT UserID, Username, Password FROM Users");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int userID = results.getInt(1);
                String username= results.getString(2);
                String Password = results.getString(3);
                System.out.println(userID + " " + username + " " + Password);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

        closeDatabase();

    }
    public static void main(String[] args) {

        openDatabase("Test.db");

        try {

            PreparedStatement ps = db.prepareStatement("INSERT INTO Users (UserId, Username, Password) VALUES (?, ?, ?)");

            ps.setInt(1, 6);
            ps.setString(2, "Bob");
            ps.setInt(3, 60);

            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

        closeDatabase();

    }

    public static void main(String[] args) {
        openDatabase("UserDatabase.db"); // opens data base
        closeDatabase(); //Closes data base
    }

    private static void openDatabase(String dbFile) {
        try  {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            db = DriverManager.getConnection("jdbc:sqlite:resources/" + dbFile, config.toProperties());
            System.out.println("Database connection successfully established.");
        } catch (Exception exception) {
            System.out.println("Database connection error: " + exception.getMessage());
        }

    }

    private static void closeDatabase(){
        try {
            db.close();
            System.out.println("Disconnected from database.");
        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

}
