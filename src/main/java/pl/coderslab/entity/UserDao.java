package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDao {

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String DATA_USER_QUERY =
            "select * from workshop2.users";
    private static final String INFO_USER_QUERY =
            "SELECT * FROM users where id = ?";
    private static final String REMOVE_USER_QUERY =
            "delete from workshop2.users where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ? where id = ?";

    public User create(User user) {

        try (Connection conn = DbUtil.connect()) {
            PreparedStatement preState = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            preState.setString(1, user.getUserName());
            preState.setString(2, user.getEmail());
            preState.setString(3, hashPassword(user.getPassword()));
            preState.executeUpdate();

            ResultSet rS = preState.getGeneratedKeys();
            if (rS.next()) {
                int id = rS.getInt(1);
                user.setId(id);
            }
            return user;
        } catch (SQLException e) {
            System.out.println(" Błąd w pobraniu id ");
        }return null;
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    public User read(int userId) {
        try (Connection conn = DbUtil.connect()) {
            try (PreparedStatement pS = conn.prepareStatement(INFO_USER_QUERY)) {
                pS.setInt(1, userId);
                try (ResultSet rS = pS.executeQuery()) {
                    while (rS.next()) {
                        User user = new User();
                        user.setId(rS.getInt("id"));
                        user.setUserName(rS.getString("username"));
                        user.setEmail(rS.getString("email"));
                        user.setPassword(rS.getString("password"));
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("  Błąd w odczycie ");
        }
        return null;
    }
}


