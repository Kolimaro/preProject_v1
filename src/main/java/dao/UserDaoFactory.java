package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {

    public static UserDAO getUserDAO() {
        Properties properties = new Properties();
        try {
            //String path = new File("dao.properties").getAbsolutePath();
            properties.load(new FileInputStream("C:\\Java\\JM\\preProjectTest\\src\\main\\resources\\dao.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String daotype = properties.getProperty("daotype");
        if (daotype.equals("JDBC")) {
            return new UserJdbcDAO();
        } else if (daotype.equals("Hibernate")) {
            return new UserHibernateDAO();
        }
        return null;
    }
}
