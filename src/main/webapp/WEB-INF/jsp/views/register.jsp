<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h1>Register</h1>
    <form:form modelAttribute="userForm" method="post">
        <div>
            <form:label path="id">ID:</form:label>
            <form:input path="id"/>
            <form:errors path="id" cssClass="error"/>
        </div>
        <div>
            <form:label path="name">Name:</form:label>
            <form:input path="name"/>
            <form:errors path="name" cssClass="error"/>
        </div>
        <div>
            <form:label path="password">Password:</form:label>
            <form:input path="password" type="password"/>
            <form:errors path="password" cssClass="error"/>
        </div>
        <div>
            <form:label path="profileImageId">Profile Image ID:</form:label>
            <form:input path="profileImageId"/>
        </div>
        <div>
            <form:label path="userType">User Type:</form:label>
            <form:input path="userType"/>
        </div>
        <div>
            <form:label path="userSpaceInfo">User Space Info:</form:label>
            <form:input path="userSpaceInfo"/>
        </div>
        <div>
            <form:label path="userSpaceOrgInfo">User Space Org Info:</form:label>
            <form:input path="userSpaceOrgInfo"/>
        </div>
        <div>
            <form:label path="uniqId">Unique ID:</form:label>
            <form:input path="uniqId"/>
        </div>
        <div>
            <form:label path="relationInfo">Relation Info:</form:label>
            <form:input path="relationInfo"/>
        </div>
        <div>
            <form:label path="isFirstInvite">First Invite:</form:label>
            <form:checkbox path="isFirstInvite"/>
            <form:errors path="isFirstInvite" cssClass="error"/>
        </div>
        <div>
            <button type="submit">Register</button>
        </div>
    </form:form>
</body>
</html>