import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloWorldServlet", urlPatterns = {"/hello"})
public class HelloWorldServlet extends HttpServlet {
    private int counter = 0;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("name");
        String resetCounter = req.getParameter("reset");
        counter += 1;

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        if (resetCounter == null) {
            String dummy = "dummy";
        } else if (resetCounter.equals("true")) {
            counter = 0;
        }

        if (name == null) {
            out.println("<h1>Hello World!</h1>");
        } else if (name.isEmpty()){
            out.println("<h1>Hello World!</h1>");
        } else {
            out.println("<h1>Hello, " + name + "!</h1>");
        }

        out.println("This page has been visited: " + counter + " times");
    }
}
