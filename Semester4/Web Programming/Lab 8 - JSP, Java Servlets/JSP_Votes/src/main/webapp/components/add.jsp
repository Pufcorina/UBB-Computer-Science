<%--
  Created by IntelliJ IDEA.
  User: todor
  Date: 7/12/2018
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<form style="margin-left: 15px; margin-top: 20px;" method="post" action="/AddServlet">
    <table>
        <td style="border: black">
            <label for="author1">Author: </label>
            <input type="text" name="author1" id="author1"/>
            <br/>
            <label for="title1">Title: </label>
            <input type="text" name="title1" id="title1"/>
            <br/>
            <label for="nbOfPages1">Number of pages: </label>
            <input type="text" name="nbOfPages1" id="nbOfPages1"/>
            <br/>
            <label for="year1">Year: </label>
            <input type="text" name="year1" id="year1"/>
            <br/>
            <label for="cost1">Cost: </label>
            <input type="text" name="cost1" id="cost1"/>
        </td>
        <td>
            <label for="author2">Author: </label>
            <input type="text" name="author2" id="author2"/>
            <br/>
            <label for="title2">Title: </label>
            <input type="text" name="title2" id="title2"/>
            <br/>
            <label for="nbOfPages2">Number of pages: </label>
            <input type="text" name="nbOfPages2" id="nbOfPages2"/>
            <br/>
            <label for="year2">Year: </label>
            <input type="text" name="year2" id="year2"/>
            <br/>
            <label for="cost2">Cost: </label>
            <input type="text" name="cost2" id="cost2"/>
        </td>
        <td>
            <label for="author3">Author: </label>
            <input type="text" name="author3" id="author3"/>
            <br/>
            <label for="title3">Title: </label>
            <input type="text" name="title3" id="title3"/>
            <br/>
            <label for="nbOfPages3">Number of pages: </label>
            <input type="text" name="nbOfPages3" id="nbOfPages3"/>
            <br/>
            <label for="year3">Year: </label>
            <input type="text" name="year3" id="year3"/>
            <br/>
            <label for="cost3">Cost: </label>
            <input type="text" name="cost3" id="cost3"/>
        </td>
        <input type="submit" value="Add"/>
    </table>
</form>
</html>
