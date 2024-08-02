<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회사 마이페이지</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
//수정버튼 클릭
function edit(jobOpeningId) {
	if(confirm('수정하시겠습니까?')) {
		location.href="/recruitment/edit/" + jobOpeningId;
	}
}
</script>

<style type="text/css">
	#companyButton_container {
		display: flex;
		flex-direction: row-reverse;
		margin-bottom: 20px;
	}
	
</style>

</head>
<body>

<%@ include file="header.jsp"%>

<div class="body_container">
	<div id="companyButton_container">
		<a href="/recruitment/add"><button type="button">채용공고 작성하기</button></a>
	</div>
	
	<div>
		<c:choose>
			<c:when test="${not empty listByCompany}">
				<ul>
				  <c:forEach items="${listByCompany}" var="jobOpening">
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
								<div>
									<a href="/recruitmentDetail/${jobOpening.jobOpeningId}">상세</a>
								</div>
								<div>
									<button type="button" onclick="edit(${jobOpening.jobOpeningId})">수정하기</button>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
				<p>등록한 채용공고가 0건 입니다.</p>
			</c:otherwise>
		</c:choose>
	</div>
</div>

</body>
</html>