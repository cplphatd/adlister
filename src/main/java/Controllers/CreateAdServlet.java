package Controllers;

import Models.Ad;
import Models.DataAccessLayer.Ads;
import Models.DataAccessLayer.DaoFactory;

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

        Ad ad = new Ad(
            1, // for now we'll hardcode the user id
            request.getParameter("title"),
            request.getParameter("description")
        );

        ads.insert(ad);
        response.sendRedirect("/ads");
    }
}
