<%--
  Created by IntelliJ IDEA.
  User: todor
  Date: 5/27/2018
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Vote system</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </head>
  <body>
    <div>
      <%@include file="/components/pageElems/navbar.jsp"%>

      <%
        if(session.getAttribute( "user" ) == null){
            String redirectURL;

            if(request.getParameter( "error" ) == null){
                redirectURL = "/components/auth/auth.jsp";
            } else {
                redirectURL = "/components/auth/auth.jsp?error=" + request.getParameter( "error" );
            }
            response.sendRedirect( redirectURL );
        }
      %>
      <div class="container">
          <%
              if(session.getAttribute( "user" ) != null) {
          %>
          <%@ include file="/components/topic/addPicture.jsp" %>
          <%
              }
          %>
          <%@include file="/components/topic/viewPictures.jsp"%>
      </div>
    </div>
  </body>
</html>
