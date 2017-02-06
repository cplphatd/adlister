package Controllers;

import Models.DataAccessLayer.DaoFactory;
import Models.DataAccessLayer.Users;
import Models.User;
import Utilities.Password;
import org.mindrot.jbcrypt.BCrypt;

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
        String enteredUsername = request.getParameter("username");
        String enteredPassword = request.getParameter("password");

        Users users = DaoFactory.getUsersDao();
        User currentUser = users.findByUsername(enteredUsername);
        String databasePassword = currentUser.getPassword();

        boolean validAttempt = Password.checkPassword(enteredPassword, databasePassword);

        if (validAttempt) {
            request.getSession().setAttribute("user", currentUser);
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/login");
        }
    }
}
