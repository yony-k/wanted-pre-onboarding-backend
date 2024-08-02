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

//로그아웃 클릭
function logout() {
	
	$.ajax({
		url : '/login/logout',
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(response) {
			if(response.success) {
				alert(response.message);
				location.href="/home";
			}
		},
		error : function(xhr, status, err) {
			alert(xhr.responseJSON.message);
			if (xhr.status === 401) location.href = '/login/form';
			console.log(status + ", " + err);
		}
	});

}

</script>

<style type="text/css">
	/* 헤더 스타일 */
	#header_container {
		display: flex;
		justify-content: space-between;
		border: 3px solid #091d36;
		margin-bottom: 20px;
		padding: 20px;
	}
	
	#login_container {
		display: flex;
		flex-direction: row;
		align-items: center;
	}
	
	#userName {
		margin: 0;
		margin-right: 20px;
	}
	
	#separation {
		width: 1px;
		height: 1em;
		margin: 0px 12px;
		background-color: gray;
	}
	
	#header_container a:link {
	  color : #2863a8;
	  text-decoration: none;
	}
	
	#header_container a:visited {
	  color : #2863a8;
	}
	
	#homeButton {
		display: flex;
		align-items: center;
	}
	
	/* 채용공고 한 블럭 스타일 */
	.body_container {
		border: 3px solid #091d36;
		padding: 20px;
	}
	
	.body_container ul {
		list-style-type: none;
		margin: 0;
		padding: 0;
	}
	
	.body_container li {
		border: 1px solid #1b3f6f;
	}
	
	.jobOpening_container {
		display: flex;
		justify-content: space-between;
		padding: 20px;
		margin-bottom: 20px;
	}
	
	.data_container {
		display: flex;
	}
	
	.data_container span {
		padding: 10px;
	}
	
	.company_container {
		display: flex;
		flex-direction: column;
		margin-right: 30px;	
	}
	
	.contents_container {
		display: flex;
		flex-direction: column;	
	}
	
	.button_container {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		align-items: center;
	}
	
	.button_container a:link {
		color : #2863a8;
		text-decoration: none;
	}
	
	.button_container a:visited {
		color : #2863a8;
	}
	
	.button_container button {
		border: none;
		outline: none;
		background-color: inherit ;
		cursor: pointer;
	}
	
	/* 채용공고 등록, 수정 스타일 */
	.input_container {
		border: 2px solid #091d36;
		margin-bottom: 20px;
		padding: 20px;
	}
	
	.input_container > div {
		padding: 10px;
	}
	
	.input_container > div > span {
		display: inline-block;
		width: 7em;
	}
	
	#contents_title {
		display: flex;
		justify-content: center;
		border: 1px solid #091d36;
		padding: 20px;
		margin-bottom: 20px;
	}
	
	.ck-editor__editable {
    	min-height: 500px;
	}
	
	.recruitmentButton_container {
		display: flex;
		justify-content: center;
		align-items: ceter;
	}
	
	.recruitmentButton_container > button {
		margin-left: auto;
		margin-right: auto;
	}
	
</style>

</head>
<body>

<div id="header_container">
	<div id="homeButton">
		<a href="/home">홈으로</a>
	</div>
	
	<div id="login_container">
		<c:choose>
			<c:when test="${empty user}">
				<a href="/login/form"><button type="button" class="logButton">로그인</button></a>
			</c:when>
			<c:otherwise>
				<p id="userName"><c:out value="${user.userName}"/>님</p>
				<a href="/mypage">마이페이지</a>
				<!-- 공간구분용 -->
				<div id="separation"></div>
				<button type="button" onclick="logout()" class="logButton">로그아웃</button>
			</c:otherwise>
		</c:choose>
	</div>
</div>

</body>
</html>