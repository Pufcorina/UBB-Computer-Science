package Topic;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "FilterServlet")
public class FilterPhoto extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer nb = Integer.parseInt( request.getParameter( "nbPhoto" ) );
        if (nb == null)
            request.getSession().setAttribute( "nbPhotos", 0);
        else
            request.getSession().setAttribute( "nbPhotos", nb);
        response.sendRedirect( "index.jsp" );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
    }
}
