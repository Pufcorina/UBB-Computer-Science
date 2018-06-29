package Topic;

import DB.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LikePhotoServlet")
public class LikePhoto extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer nrLikes = Integer.parseInt(request.getParameter( "photoLikes" ));
        nrLikes += 1;
        Integer photoId = Integer.parseInt(request.getParameter( "photoId" ));

        Integer photoUserId = Integer.parseInt( request.getParameter( "photoUserId" ) );

        String mail = request.getParameter( "currentUserMail" );

        if(mail.equals( "null" )) {
            System.out.println(  "This must not happen (mail null) ");
            response.sendRedirect("/index.jsp");
            return;
        }

        PreparedStatement statement = null;
        try {
            String sql = "select id from Users where userEmail = '" + mail + "';";
            statement = DBManager.getConnection().prepareStatement( sql );
            ResultSet resultSet = statement.executeQuery( sql );
            resultSet.next();
            Integer id = resultSet.getInt( "id" );
            if ( !id.equals( photoUserId ) ){
                PhotoManager.likePhoto(nrLikes, photoId);

                response.sendRedirect( "index.jsp" );
            } else {
                System.out.println(  "This must not happen (id equal) ");
                response.sendRedirect("/index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
    }
}
