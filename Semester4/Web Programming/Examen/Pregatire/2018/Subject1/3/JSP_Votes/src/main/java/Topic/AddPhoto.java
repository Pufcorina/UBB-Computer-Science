package Topic;

import Auth.AuthManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddPhotoServlet")
public class AddPhoto extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String mail = (String) req.getSession().getAttribute("mail");
//        String url =  req.getParameter( "urlPhoto" );
//
//        req.getSession().setAttribute( "urlPhoto", url );
//
//
//        if(mail == null) {
//            System.out.println(  "This must not happen (mail null) ");
//            resp.sendRedirect("/index.jsp");
//            return;
//        }
//
//        Integer id = AuthManager.getUserIdByMail( mail );
//
//        if(id == 0) {
//            System.out.println( "This must not happen (id is 0) ");
//            resp.sendRedirect("/index.jsp");
//            return;
//        }
//
//        if (url != null && url.equals( "" ))
//            // Add the new photo
//            PhotoManager.addNewPhoto(url, id);

        // Redirect to index
        resp.sendRedirect("/index.jsp");
    }
}
