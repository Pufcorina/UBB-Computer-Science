package Topic;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet")
public class Delete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String categoryId =  req.getParameter( "categoryId" );

        req.getSession().setAttribute( "categoryId", categoryId );


        BibliographyManager.deleteCategory(categoryId);

        // Redirect to index
        resp.sendRedirect("/index.jsp");
    }
}

