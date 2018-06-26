<%@ page import="Model.Photo" %>
<%@ page import="Topic.PhotoManager" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.Comparator" %><%--
  Created by IntelliJ IDEA.
  User: todor
  Date: 5/27/2018
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <style>
        img {
            height: 150px;
        }

        a {
            color: #000;
        }
    </style>

    <form style="margin-left: 15px; margin-top: 20px;" method="post" action="/FilterServlet">
        <div class="row">
            <div class="col-md-5">
                <label for="nbPhoto">Number of photos:</label>
                <input type="text" name="nbPhoto" id="nbPhoto" value="number..."/>
                <input type="submit" value="Show"/>
            </div>
        </div>
    </form>

    <%
        Integer nb = (Integer) session.getAttribute( "nbPhotos" );
        if( session.getAttribute( "nbPhotos" ) != null)
            for(Photo photo : PhotoManager.getAllPhotos().stream().sorted( Comparator.comparing( Photo::getLikes ).reversed() ).collect( Collectors.toList() ))
                if (nb != 0)
            {
                nb--;
    %>
    <form style="margin-left: 15px; margin-top: 20px;" method="post" action="/LikePhotoServlet?photoId=<%=photo.getId()%>&photoLikes=<%=photo.getLikes()%>&photoUserId=<%=photo.getUserId()%>&currentUserMail=<%=session.getAttribute("mail")%>">
        <div class="container">
            <div class="card-columns">
                <div class="card">
                    <div class="card-body" style="margin-bottom: 5%;">
                        <a href="/components/topic/viewTopic.jsp?id=<%=photo.getId()%>">
                            <h5 class="card-title">Likes: <%= photo.getLikes() %></h5>
                            <h5 class="card-title">User: <%= photo.getUserName() %></h5>
                        </a>
                        <button type="submit" class="btn btn-danger">Like</button>
                        <img src="/imgs/pictures/<%=photo.getUrl()%>">
                    </div>
                </div>
            </div>
        </div>
    </form>
    <% } %>

</html>
