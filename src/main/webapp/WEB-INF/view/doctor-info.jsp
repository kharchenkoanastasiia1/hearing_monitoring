<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>

<html lang="uk">

<body>

<h2>Інформація про лікаря:</h2>

<br>

<form:form action="saveDoctor" modelAttribute="doctor">

    <form:hidden path="id"/>

    Username: <form:input path="username"/>
    <br><br>
    Ім'я: <form:input path="name"/>
    <br><br>
    Прізвище: <form:input path="surname"/>
    <br><br>
    Email: <form:input path="email"/>
    <br><br>
    <input type="submit" value="OK">

</form:form>

</body>

</html>