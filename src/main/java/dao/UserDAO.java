package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> getAllUsers() throws SQLException;

    User getUserById(Long id) throws SQLException;

    void addUser(User user) throws SQLException;

    void editUser(User user) throws SQLException;

    void deleteUser(User user) throws SQLException;

    default void createTable() throws SQLException {}

    default void dropTable() throws SQLException {}
}
