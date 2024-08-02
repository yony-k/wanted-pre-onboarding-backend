<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채용공고 검색 결과</title>

<style type="text/css">
	#serch {
		border: 3px solid #091d36;
		padding: 20px;
		margin-bottom: 20px;
		display: flex;
		justify-content: center;
	}

</style>

</head>
<body>

<%@ include file="header.jsp"%>

<div id="serch">검색결과</div>

<div class="body_container">
	<c:choose>
		<c:when test="${not empty serchResult}">
			<ul>
			  <c:forEach items="${serchResult}" var="jobOpening">
					<li class="jobOpening_container">
						<div class="data_container">
							<div class="company_container">
								<span><c:out value="${jobOpening.company.companyName}"/></span>
								<span><c:out value="${jobOpening.company.country}"/></span>
								<span><c:out value="${jobOpening.company.region}"/></span>
							</div>
							<div class="contents_container">
								<span><c:out value="${jobOpening.position}"/></span>
								<span><fmt:formatNumber value="${jobOpening.compensation}" type="number" pattern="#,###"/>원</span>
								<span>
									<c:forEach items="${jobOpening.skill}" var="skill" varStatus="status">
										<c:out value="${skill}"/>
										<c:if test="${!status.last}">
		                                    , 
		                                </c:if>
									</c:forEach>
								</span>
							</div>
						</div>
						
						<div class="button_container">
							<a href="/recruitmentDetail/${jobOpening.jobOpeningId}">상세</a>
						</div>
					</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p>검색결과가 없습니다.</p>
		</c:otherwise>
	</c:choose>
</div>

</body>
</html>