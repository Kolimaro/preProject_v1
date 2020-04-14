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

@WebServlet("/admin/delete")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserService.getInstance();

        long id = Long.parseLong(req.getParameter("id"));
        userService.deleteUser(id);
        List<User> users = userService.getAllUsers();
        req.setAttribute("usersFromDB", users);

        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/web/allUsers.jsp");
        dispatcher.forward(req, resp);
    }
}
