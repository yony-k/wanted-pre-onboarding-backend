<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 마이페이지</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>

//지원취소 클릭
function cancleApply(jobOpeningId) {

	if(confirm('지원을 취소하시겠습니까?')) {
		$.ajax({
			url : '/apply/cancle/' + jobOpeningId,
			type : 'delete',
			cache : false,
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

</head>
<body>

<%@ include file="header.jsp"%>

<div class="body_container">
	<c:choose>
		<c:when test="${not empty applicationList}">
			<ul>
			  <c:forEach items="${applicationList}" var="jobOpening">
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
								<button type="button" onclick="cancleApply('${jobOpening.jobOpeningId}')">지원취소</button>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p>지원건수가 0건 입니다.</p>
		</c:otherwise>
	</c:choose>
</div>

</body>
</html>