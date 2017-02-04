package Controllers;

import Models.Ad;
import Models.DataAccessLayer.Ads;
import Models.DataAccessLayer.DaoFactory;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controllers.CreateAdServlet", urlPatterns = "/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
            .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Ads ads = DaoFactory.getAdsDao();

        User currentUser = (User) request.getSession().getAttribute("user");

        Ad ad = new Ad(
            currentUser.getId(),
            request.getParameter("title"),
            request.getParameter("description")
        );

        if (ads.insert(ad) != null) {
            response.sendRedirect("/ads");
        } else {
            response.sendRedirect("/create");
        }
    }
}
