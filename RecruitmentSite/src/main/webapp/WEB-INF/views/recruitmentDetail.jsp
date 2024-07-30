<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채용공고 상세</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>

</script>

</head>
<body>

<%@ include file="header.jsp"%>

<ul>
	<li><c:out value="${jobOpening.jobOpeningId}"/></li>
	<li><c:out value="${jobOpening.company.companyName}"/></li>
	<li><c:out value="${jobOpening.company.country}"/></li>
	<li><c:out value="${jobOpening.company.region}"/></li>
	<li><c:out value="${jobOpening.position}"/></li>
	<li><c:out value="${jobOpening.salary}"/></li>
	<li>
		<c:forEach items="${jobOpening.skill}" var="skill" varStatus="status">
			<c:out value="${skill}"/>
			<c:if test="${!status.last}">
                               , 
                           </c:if>
		</c:forEach>
	</li>
	<li><c:out value="${jobOpening.contents}"/></li>
	<li></li>
</ul>
</body>
</html>