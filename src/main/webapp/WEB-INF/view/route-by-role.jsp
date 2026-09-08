<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
</head>

<body>

<security:authorize access="hasRole('ADMIN')">
    <meta http-equiv="refresh" content="0; URL=admin/allInfo">
</security:authorize>

<security:authorize access="hasRole('DOCTOR')">
    <meta http-equiv="refresh" content="0; URL=doctor/allPatient">
</security:authorize>

<security:authorize access="hasRole('PATIENT')">
    <meta http-equiv="refresh" content="0; URL=patient/info">
</security:authorize>

</body>

</html>
