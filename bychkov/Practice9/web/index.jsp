<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 17.03.2020
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Canculator</title>
  </head>
  <body>
    <form action="calc">
      <input type="number" name="firstArg">
      <select name="op">
        <option value="plus">+</option>
        <option value="minus">-</option>
      </select>
      <input type="number" name="secondArg">
      <input type="submit" value="Calculate">
    </form>
  </body>
</html>
