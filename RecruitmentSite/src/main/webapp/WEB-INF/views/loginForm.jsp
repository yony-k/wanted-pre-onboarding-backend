<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>

function sighIn() {
	
	var userId = $('#userId').val().trim();
	var userPwd = $('#userPwd').val().trim();

	if(!/^[A-z0-9]+$/.test(userId)) {
		alert('아이디는 영어 소문자와 숫자만 포함해야 합니다.');
		return
	}
	
	if (userPwd === "") {
		alert('비밀번호를 입력해주세요.');
		return
    }
	
	var data = {
        userId: userId,
        userPwd: userPwd
    };
	
	$.ajax({
		url : '/login/sighin',
		type : 'post',
		cache : false,
		data : data,
		dataType : 'json',
		success : function(response) {
			if(response.success) {
				alert('로그인 성공');
				location.href="/home";
			} else {
				alert("아이디 혹은 비밀번호를 확인해주세요.");
			}
			
		},
		error : function(xhr, status, err) {
			if(xhr.status === 401) {
				
			} else {
				alert(status + ", " + err);
			}
		}
	});

}

</script>

</head>
<body>

<%@ include file="header.jsp"%>

로그인 페이지

<form>
    <label for="userId">아이디:</label>
    <input type="text" id="userId" name="userId">
    
    <label for="userPwd">비밀번호:</label>
    <input type="password" id="userPwd" name="userPwd">
    
    <button type="button" onclick="sighIn()">로그인</button>
</form>


</body>
</html>