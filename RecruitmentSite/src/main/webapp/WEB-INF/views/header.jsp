<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헤더페이지</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>

function logout() {
	
	$.ajax({
		url : '/login/logout',
		type : 'post',
		cache : false,
		data : data,
		dataType : 'json',
		success : function(response) {
			alert('로그아웃 성공');
			location.href="/home";
		},
		error : function(xhr, status, err) {
			alert(status + ", " + err);
		}
	});

}

</script>

</head>
<body>

<c:choose>
	<c:when test="${empty user}">
		<a href="/login/form"><button>로그인</button></a>
	</c:when>
	<c:otherwise>
		<p><c:out value="${user.userName}"/>님</p>
		<button type="button" onclick="logout()">로그아웃</button>
	</c:otherwise>
</c:choose>

</body>
</html>