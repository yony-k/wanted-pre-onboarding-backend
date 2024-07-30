<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul>
	<c:forEach items="${jobOpeningList}" var="jobOpening">
		<li>
			<p><c:out value="${jobOpening.jobOpeningId}"/></p>
			<p><c:out value="${jobOpening.companyId}"/></p>
			<p><c:out value="${jobOpening.position}"/></p>
			<p><c:out value="${jobOpening.salary}"/></p>
			<p><c:out value="${jobOpening.contents}"/></p>
		</li>
	</c:forEach>
	
</ul>

<a href="/login/form"><button>로그인</button></a>

</body>
</html>