<%--
  Created by IntelliJ IDEA.
  User: todor
  Date: 5/27/2018
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<style>
    .authIcon {
        height: 25px;
        width: 25px;
    }

    a:hover {
        text-decoration: none;
    }

</style>

    <nav class="navbar navbar-expand-lg navbar-dark bg-danger" style="margin-bottom: 15px;">
        <a class="navbar-brand" href="/">Vote Forum</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-tooggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
        <%
            if(session.getAttribute( "user" ) == null){
        %>
            <div>
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a href="login.jsp" style="color: white; padding: 4px;">Login <img class="authIcon" src="/imgs/login.svg"></a>
                    </li>
                </ul>
            </div>
            <div>
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a href="register.jsp" style="color: white; padding: 4px;">Register <img class="authIcon" src="/imgs/register.svg"></a>
                    </li>
                </ul>
            </div>
        <%
            }
            if (session.getAttribute( "user" ) != null){
        %>
            <div>
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <span style="color: white; padding: 4px;">User: <%= session.getAttribute("user") %></span>
                    </li>
                </ul>
            </div>
            <div>
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a href="/LogoutServlet" style="color: white; padding: 4px; margin-left: 2px;">Logout <img class="authIcon" src="/imgs/logout.png"></a>
                    </li>
                </ul>
            </div>
        <%
            }
        %>
    </nav>
</html>
