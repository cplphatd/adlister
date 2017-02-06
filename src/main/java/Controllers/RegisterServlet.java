package Controllers;

import Models.DataAccessLayer.DaoFactory;
import Models.DataAccessLayer.Users;
import Models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by David on 2/4/17.
 */
@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/users/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Users users = DaoFactory.getUsersDao();
        String desiredUsername = request.getParameter("username");
        String desiredEmail = request.getParameter("email");
        String desiredPassword = request.getParameter("password");
        int numberOfRounds = 12;

        if (
                desiredUsername != null
                && desiredEmail != null
                && desiredPassword != null
                && !desiredUsername.isEmpty()
                && desiredEmail.contains("@")
                && desiredEmail.contains(".")
                && !desiredPassword.isEmpty()
            ) {

            String hashPassword = BCrypt.hashpw(
                    desiredPassword,
                    BCrypt.gensalt(numberOfRounds)
            );

            User newUser = new User(
                    desiredUsername,
                    desiredEmail,
                    hashPassword
            );

            if (users.insert(newUser) != null) {
                request.getSession().setAttribute("user", newUser);
                response.sendRedirect("/profile");
            }
        } else {
            response.sendRedirect("/register");
        }
    }
}
