<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>

<html lang="uk">

<body>

<h2>Інформація про пацієнта:</h2>

<br>

<form:form action="savePatient" modelAttribute="patient">

    <form:hidden path="id"/>

    Username: <form:input path="username"/>
    <br><br>
    Ім'я: <form:input path="name"/>
    <br><br>
    Прізвище: <form:input path="surname"/>
    <br><br>
    Вік: <form:input path="age"/>
    <br><br>
    Стать: <form:input path="sex"/>
    <br><br>
    Email: <form:input path="email"/>
    <br><br>
    Username лікаря: <form:input path="doctorUsername"/>
    <br><br>
    <input type="submit" value="OK">

</form:form>

</body>

</html>