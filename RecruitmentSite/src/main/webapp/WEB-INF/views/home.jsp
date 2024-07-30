<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채용공고 목록</title>

</head>
<body>

<%@ include file="header.jsp"%>

<ul>
	<c:choose>
		<c:when test="${not empty jobOpeningList}">
			<c:forEach items="${jobOpeningList}" var="jobOpening">
				<li>
					<a href="home/recruitmentDetail/${jobOpening.jobOpeningId}">
						<p><c:out value="${jobOpening.jobOpeningId}"/></p>
						<p><c:out value="${jobOpening.company.companyName}"/></p>
						<p><c:out value="${jobOpening.company.country}"/></p>
						<p><c:out value="${jobOpening.company.region}"/></p>
						<p><c:out value="${jobOpening.position}"/></p>
						<p><c:out value="${jobOpening.salary}"/></p>
						<p><c:out value="${jobOpening.contents}"/></p>
						<p>
							<c:forEach items="${jobOpening.skill}" var="skill" varStatus="status">
								<c:out value="${skill}"/>
								<c:if test="${!status.last}">
                                    , 
                                </c:if>
							</c:forEach>
						</p>
					</a>
				</li>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>채용공고가 0건 입니다.</p>
		</c:otherwise>
	</c:choose>
	
</ul>

</body>
</html>