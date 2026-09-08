<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>

<html lang="uk">

<head>
    <style>
        h2{
            color: blue;
        }
        body {
            background-color: aliceblue;
        }
        textarea{
            height: 200px;
            width: 50%;
        }
        .submit {
            color: #fff;
            background-color: cornflowerblue;
            font-size: 18px;
        }
    </style>
</head>

<body>

<h2>Напишіть повідомлення:</h2>

<br>

<form:form action="saveMessage" modelAttribute="message">

    <form:hidden path="username"/>

    <form:textarea path="text"/>
    <br><br>
    <input type="submit" value="OK" class="submit">

</form:form>

</body>

</html>