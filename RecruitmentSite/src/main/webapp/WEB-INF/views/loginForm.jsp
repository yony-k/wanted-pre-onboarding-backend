<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인폼</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>

//로그인 클릭
function login() {
	
	const userId = $('#userId').val().trim();
	const userPwd = $('#userPwd').val().trim();

	if(!/^[A-z0-9]+$/.test(userId)) {
		alert('아이디는 영어 소문자와 숫자만 포함해야 합니다.');
		return
	}
	
	if (userPwd === "") {
		alert('비밀번호를 입력해주세요.');
		return
    }
	
	const data = {
        userId: userId,
        userPwd: userPwd
    };
	
	$.ajax({
		url : '/login/sighin',
		type : 'post',
		cache : false,
		data : JSON.stringify(data),
		contentType: 'application/json',
		dataType : 'json',
		success : function(response) {
			if(response.success) {
				alert(response.message);
				location.href="/home";
			}
		},
		error : function(xhr, status, err) {
			alert(xhr.responseJSON.message);
			console.log(status + ", " + err);
		}
	});
}

</script>

<style type="text/css">
	
	.body_container {
		display: flex;
		justify-content: center;
	}
	
	.login_container {
		display: flex;
		flex-direction: column;
		align-items: center;
		border: 2px solid #091d36;
		width: 300px;
		padding: 20px;
	}
	
	#login_title {
		margin-bottom: 20px;
	}	
	
	#form_container {
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	
	#form_container > div {
		margin-bottom: 10px;
	}
	
	#form_container > div > input{
		height: 50px;
		width: 200px;
	}
</style>

</head>
<body>

<%@ include file="header.jsp"%>

<div class="body_container">
	<div class="login_container">
		<div id="login_title">로그인</div>
		
		<div>
			<form id="form_container">
				<div>
					<input type="text" id="userId" name="userId" placeholder="아이디를 입력해주세요">
				</div>
			    
			    <div>
			    	<input type="password" id="userPwd" name="userPwd" placeholder="비밀번호를 입력해주세요">
			    </div>
			    <div id="login_button">
			    	<button type="button" onclick="login()">로그인 </button>
			    </div>
			</form>
		</div>
	</div>
</div>

</body>
</html>