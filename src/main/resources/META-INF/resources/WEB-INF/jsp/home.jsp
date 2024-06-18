<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>HomePage</title>
	</head>
	<body>
		<c:forEach var="team" items="${teams}">
			<h2>${team.name}</h2>
			<p>${team.description}</p>
		</c:forEach>
	</body>
</html>