package Topic;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FilterServlet")
public class Filter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String text = request.getParameter( "textSearch" );
        if (text == null)
            request.getSession().setAttribute( "textSearch", null);
        else
            request.getSession().setAttribute( "textSearch", text);
        response.sendRedirect( "index.jsp" );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
    }
}