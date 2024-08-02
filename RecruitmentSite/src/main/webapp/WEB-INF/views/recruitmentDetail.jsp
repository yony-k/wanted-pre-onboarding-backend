<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채용공고 상세</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>

//지원하기 클릭
function apply(jobOpeningId) {

	if(confirm('지원하시겠습니까?')) {
		$.ajax({
			url : '/apply',
			type : 'post',
			cache : false,
			data: JSON.stringify({ jobOpeningId: jobOpeningId }),
			contentType: 'application/json',
			dataType : 'json',
			success : function(response) {
				if(response.success) alert(response.message);
				location.href="/mypage";
			},
			error : function(xhr, status, err) {
				alert(xhr.responseJSON.message);
				if (xhr.status === 401) location.href = '/login/form';
				console.log(status + ", " + err);
			}
		});
	}
}

</script>

<style type="text/css">
	#another {
		border: 2px solid #091d36;
		padding: 20px;
		margin-bottom: 20px;
		display: flex;
		justify-content: center;
	}
	
	#detail {
		border: 2px solid #091d36;
		padding: 20px;
		margin-bottom: 20px;
	}
	
	#company_container {
		display: flex;
		justify-content: space-between;
		margin-bottom: 20px;
	}
	
	#detail span {
		display: inline-block;
		padding: 10px;
	}
	
</style>

</head>
<body>

<%@ include file="header.jsp"%>

<div class="body_container">
	<div id="detail">
		<div id="company_container">
			<div>
				<div>
					<span>회사명</span>
					<span><c:out value="${jobOpening.company.companyName}"/></span>
				</div>
				<div>
					<span>국가</span>
					<span><c:out value="${jobOpening.company.country}"/></span>
				</div>
				<div>
					<span>지역</span>
					<span><c:out value="${jobOpening.company.region}"/></span>
				</div>
			</div>
			<div>
				<button type="button" onclick="apply('${jobOpening.jobOpeningId}')">지원하기</button>
			</div>
		</div>
		
		<div>
			<div>
				<span>채용포지션</span>
				<span><c:out value="${jobOpening.position}"/></span>
			</div>
			<div>
				<span>채용보상금</span>
				<span><fmt:formatNumber value="${jobOpening.compensation}" type="number" pattern="#,###"/>원</span>
			</div>
			<div>
				<span>사용기술</span>
				<span>
					<c:forEach items="${jobOpening.skill}" var="skill" varStatus="status">
						<c:out value="${skill}"/>
						<c:if test="${!status.last}">, </c:if>
					</c:forEach>
				</span>
			</div>
			<div>
				<span><c:out value="${jobOpening.contents}" escapeXml="false"/></span>
			</div>
		</div>
	</div>
	
	<div>
		<div id="another">이 회사의 다른 채용공고</div>
		
		<div>
			<c:choose>
				<c:when test="${not empty listByCompany}">
					<ul>
					  <c:forEach items="${listByCompany}" var="another">
					  		<c:if test="${another.jobOpeningId != jobOpening.jobOpeningId}">
					  			<li class="jobOpening_container">
									<div class="data_container">
										<div class="company_container">
											<span><c:out value="${another.company.companyName}"/></span>
											<span><c:out value="${another.company.country}"/></span>
											<span><c:out value="${another.company.region}"/></span>
										</div>
										<div class="contents_container">
											<span><c:out value="${another.position}"/></span>
											<span><fmt:formatNumber value="${jobOpening.compensation}" type="number" pattern="#,###"/>원</span>
											<span>
												<c:forEach items="${another.skill}" var="skill" varStatus="status">
													<c:out value="${skill}"/>
													<c:if test="${!status.last}">
					                                    , 
					                                </c:if>
												</c:forEach>
											</span>
										</div>
									</div>
									
									<div class="button_container">
										<a href="/recruitmentDetail/${another.jobOpeningId}">상세</a>
									</div>
								</li>
					  		</c:if>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<p>다른 채용공고가 0건 입니다.</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

</body>
</html>