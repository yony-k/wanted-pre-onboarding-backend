<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채용공고 추가</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/41.0.0/classic/ckeditor.js"></script>

<script>
$(document).ready(function() {
    
	//ckeditor 초기화
    ClassicEditor.create(document.querySelector('#contents'), {
		ckfinder: {
			uploadUrl: '/recruitment/uploadImage'
		},
	}).then(newEditor => {
		window.editor = newEditor;
	}).catch(error => {
		console.error(error);
	});
    
	//등록버튼 클릭시
    $('#add').click(function(e) {
        e.preventDefault();
        
        if(confirm('등록하시겠습니까?')) {
			
        	//값 가져오기
        	const position = $('#position').val().trim();
            const compensation = $('#compensation').val().trim();
            const contents = editor.getData();
            const skill = $('#skill').val().trim().split(',');
            
            //json객체 생성
            const data = {
                position: position,
                compensation: compensation,
                contents: contents,
                skill: skill
            };
            
            $.ajax({
                url: '/recruitment/add',
                type: 'post',
                cache: false,
                data: JSON.stringify(data),
                contentType: 'application/json',
                dataType: 'json',
                success: function(response) {
                    if(response.success) {
                        alert(response.message);
                        location.href = "/recruitmentDetail/" + response.jobOpeningId;
                    }
                },
                error: function(xhr, status, err) {
                	alert(xhr.responseJSON.message);
    				if (xhr.status === 401) location.href = '/login/form';
    				console.log(status + ", " + err);
                }
            });
        }
    });
});
</script>

</head>
<body>

<%@ include file="header.jsp"%>

<div class="body_container">
	<div class="input_container">
		<div>
			<span>채용포지션</span>
			<input type="text" id="position" name="position">
		</div>
		<div>
			<span>채용보상금</span>
			<input type="number" id="compensation" name="compensation">
			<span>원</span>
		</div>
		<div>
			<span>사용기술</span>
			<input type="text" id="skill" name="skill">
		</div>
		<div>
			<div id="contents_title">
				<span>채용내용</span>
			</div>
			<textarea id="contents" name="contents" rows="10" cols="20"></textarea>
		</div>
	</div>
	
	<div class="recruitmentButton_container">
		<button type="button" id="add">등록</button>
	</div>
</div>

</body>
</html>
