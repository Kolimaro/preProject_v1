package servlet;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/web/addUser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        userService.addUser(firstName, lastName, login, password, role);
        List<User> users = userService.getAllUsers();
        req.setAttribute("usersFromDB", users);

        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/web/allUsers.jsp");
        dispatcher.forward(req, resp);
    }
}
