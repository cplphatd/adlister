package Controllers;

import Models.Ad;
import Models.DataAccessLayer.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by David on 2/2/17.
 */
@WebServlet(name = "Controllers.SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ad> ads;
        if (!request.getParameter("id").isEmpty()) {
            Long idNumber = Long.parseLong(request.getParameter("id"));

            ads = DaoFactory.getAdsDao().searchAdsByID(idNumber);
        } else {
            String title = request.getParameter("title");

            ads = DaoFactory.getAdsDao().searchAdsByTitle(title);
        }

        request.setAttribute("ads", ads);
        request.getRequestDispatcher("/results").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ads/search.jsp").forward(request, response);
    }
}
