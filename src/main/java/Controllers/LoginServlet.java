package Controllers;

import Models.DataAccessLayer.DaoFactory;
import Models.DataAccessLayer.Users;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/users/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean validAttempt = false;

        Users user = DaoFactory.getUsersDao();
        User currentUser = user.findByUsername(username);

        String currentUsername = currentUser.getUsername();
        String currentPassword = currentUser.getPassword();

        if (currentUsername.equals(username) & currentPassword.equals(password)) {
            validAttempt = true;
        }

        if (validAttempt) {
            request.getSession().setAttribute("user", currentUser);
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/login");
        }
    }
}