<%--
  Created by IntelliJ IDEA.
  User: todor
  Date: 5/27/2018
  Time: 7:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="true" %>
<form style="margin-left: 15px; margin-top: 20px;" method="post" action="/AddPhotoServlet">
    <div class="row">
        <div class="col-md-5">
            <label for="urlPhoto">Photo url</label>
            <input type="file" name="urlPhoto" id="urlPhoto" value="select images..."/>
        </div>

        <div class="col-md-5">
            <input type="submit" value="Upload"/>
        </div>
    </div>
</form>
