<%--
  Created by IntelliJ IDEA.
  User: todor
  Date: 7/12/2018
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<form style="margin-left: 15px; margin-top: 20px;" method="post" action="/DeleteServlet">
    <div class="row">
        <div class="col-md-5">
            <label for="categoryId">Category id: </label>
            <input type="text" name="categoryId" id="categoryId"/>
            <input type="submit" value="Delete"/>
        </div>

    </div>
</form>
</html>
