import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by David on 1/30/17.
 */
@WebServlet (name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correctUsername = "cplphatd";
        String correctPassword = "password";
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.equalsIgnoreCase(correctUsername) && password.equalsIgnoreCase(correctPassword)) {
            response.sendRedirect("/profile?name=" + username);
        } else {
            response.sendRedirect("/login");
        }
    }
}
