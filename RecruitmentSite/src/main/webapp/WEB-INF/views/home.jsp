<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채용공고 목록</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>

//검색어 검증
function validateSearch() {
    const keyword = $('input[name="keyword"]').val().trim();
    if (keyword === '') {
        alert('검색어를 입력해 주세요.');
        return false;
    }
    return true;
}

</script>

<style type="text/css">
	
	/* 검색 블럭 스타일 */
	#serch {
		display: flex;
		justify-content: center;
		margin-bottom: 20px;
	}
	
</style>

</head>
<body>

<%@ include file="header.jsp"%>

<div class="body_container">
	<div id="serch">
		<form action="/serch" method="get" onsubmit="return validateSearch()">
			<select id="category" name="category">
				<option value="all" selected="selected">전체</option>
			    <option value="position">채용포지션</option>
			    <option value="contents">채용내용</option>
			    <option value="skill">사용스킬</option>
			</select>
			<input type="text" id="keyword" name="keyword">
			<button type="submit">검색</button>
		</form>
	</div>
	
	<div>
		<c:choose>
			<c:when test="${not empty jobOpeningList}">
				<ul>
				  <c:forEach items="${jobOpeningList}" var="jobOpening">
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
				<p>채용공고가 0건 입니다.</p>
			</c:otherwise>
		</c:choose>
	</div>
</div>

</body>
</html>