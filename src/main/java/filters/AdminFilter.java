package filters;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            resp.sendRedirect(req.getContextPath());
        } else {
            UserService userService = UserService.getInstance();
            User user = userService.getUserById((Long) session.getAttribute("id"));
            if (user.getRole().equals("admin")) {
                filterChain.doFilter(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/user");
            }
        }

    }

    @Override
    public void destroy() {

    }
}
