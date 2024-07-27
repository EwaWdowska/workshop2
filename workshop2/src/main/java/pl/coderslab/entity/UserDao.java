package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;

public class UserDao {

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String ALL_USER_QUERY =
            "select * from users";
    private static final String INFO_USER_QUERY =
            "SELECT * FROM users where id = ?";
    private static final String REMOVE_USER_QUERY =
            "delete from users where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ? where id = ?";

    public User create(User user) {
        try (Connection conn = DbUtil.connect()) {
            try(PreparedStatement preState = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ;
                preState.setString(1, user.getUserName());
                preState.setString(2, user.getEmail());
                preState.setString(3, hashPassword(user.getPassword()));
                preState.executeUpdate();

                try (ResultSet rS = preState.getGeneratedKeys()) {
                    if (rS.next()) {
                        int id = rS.getInt(1);
                        user.setId(id);
                    }
                }
            } return user;
        } catch (SQLException e) {
            System.out.println(" Błąd w pobraniu id ");
        }
        return null;
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

    public void update(User user) {
        try (Connection conn = DbUtil.connect()) {
            try (PreparedStatement prS = conn.prepareStatement(UPDATE_USER_QUERY)) {
                prS.setInt(1, user.getId());
                prS.setString(2, user.getUserName());
                prS.setString(2, user.getEmail());
                prS.setString(2, hashPassword(user.getPassword()));

                try (ResultSet rS = prS.executeQuery()) {
                    user.setId(rS.getInt("id"));
                    user.setUserName(rS.getString("userName"));
                    user.setEmail(rS.getString("email"));
                    user.setPassword(hashPassword(rS.getString("password")));
                }
            }
        } catch (SQLException e) {
            System.out.println(" Błąd w aktualizacji danych ");
        }
    }

    public void delete(int userId) {
        try (Connection conn = DbUtil.connect()) {
            try (PreparedStatement prS = conn.prepareStatement(REMOVE_USER_QUERY)) {
                prS.setInt(1, userId);
                prS.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(" Błąd w usuwaniu rekordu");
        }
    }

    private User[] findAll() {
        try (Connection conn = DbUtil.connect()) {
            User[] users = new User[0];
            try (Statement sT = conn.createStatement()) {
                try (ResultSet rS = sT.executeQuery(ALL_USER_QUERY)) {
                    while (rS.next()) {
                        User user = new User();
                        user.setUserName(rS.getString("userName"));
                        user.setEmail(rS.getString("email"));
                        user.setPassword(hashPassword(rS.getString("password")));
                        users = addToArray(user, users);
                    }
                }
            }
            return users;
        } catch (SQLException e) {
            System.out.println("  Błąd w pobieraniu danych do tablicy ");
        }
        return null;
    }


    private User[] addToArray(User u, User[] users) {
        User[] userAll = Arrays.copyOf(users, users.length + 1);
        userAll[users.length] = u;
        return userAll;
    }
}
