<%@ page import="Model.BibliographyEntry" %>
<%@ page import="Topic.BibliographyManager" %><%--
  Created by IntelliJ IDEA.
  User: todor
  Date: 7/12/2018
  Time: 9:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <form style="margin-left: 15px; margin-top: 20px;" method="post" action="/FilterServlet">
        <div class="row">
            <div class="col-md-5">
                <label for="textSearch">Author or title: </label>
                <input type="text" name="textSearch" id="textSearch"/>
                <input type="submit" value="Show"/>
            </div>

        </div>
    </form>

    <%
        String text = (String) session.getAttribute( "textSearch" );
        if (session.getAttribute( "textSearch" ) != null)
            for(BibliographyEntry bibliographyEntry: BibliographyManager.getAllBiblioBySubstring( text )){
    %>
    <form style="margin-left: 15px; margin-top: 20px;">
        <div class="container">
            <div class="card-columns">
                <div class="card">
                    <div class="card-body" style="margin-bottom: 5%;">
                        <a href="/components/topic/search.jsp?id=<%=bibliographyEntry.getId()%>">
                            <h5 class="card-title">Author: <%= bibliographyEntry.getAuthor() %></h5>
                            <h5 class="card-title">Title: <%= bibliographyEntry.getTitle() %></h5>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <%
            }
    %>
</html>

