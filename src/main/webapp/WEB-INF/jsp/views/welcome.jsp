<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Information</title>
</head>
<body>
    <h1>Welcome, ${user.name}</h1>
    <div>
        <p><strong>ID:</strong> ${user.id}</p>
        <p><strong>Name:</strong> ${user.name}</p>
        <p><strong>Password:</strong> ${user.password}</p>
        <p><strong>Profile Image ID:</strong> ${user.profileImageId}</p>
        <p><strong>User Type:</strong> ${user.userType}</p>
        <p><strong>User Space Info:</strong> ${user.userSpaceInfo}</p>
        <p><strong>User Space Org Info:</strong> ${user.userSpaceOrgInfo}</p>
        <p><strong>Unique ID:</strong> ${user.uniqId}</p>
        <p><strong>Relation Info:</strong> ${user.relationInfo}</p>
        <p><strong>First Invite:</strong> ${user.isFirstInvite}</p>
    </div>
</body>
</html>