<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채용공고 수정</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/41.0.0/classic/ckeditor.js"></script>

<script>
$(document).ready(function() {
    
	//ckeditor 초기화
    ClassicEditor.create(document.querySelector('#contents'), {
		ckfinder: {
			uploadUrl: '/recruitment/uploadImage'
		}
	}).then(newEditor => {
		window.editor = newEditor;
		const existingContent = $('#existingContent').val();
		newEditor.setData(existingContent);
	}).catch(error => {
		console.error(error);
	});
    
    //사용기술 변환
    var skillString = $('#skill').val();
    
    var joinSkillString = skillString
    .replace(/^\[|\]$/g, '')
    .split(', ')
    .join(', ');

    $('#skill').val(joinSkillString);
});

//수정버튼 클릭
function editRecruitment(jobOpeningId) {
	if(confirm('수정하시겠습니까?')) {
    	const position = $('#position').val().trim();
        const compensation = $('#compensation').val().trim();
        const contents = editor.getData();
        const skill = $('#skill').val().trim().split(',');
        
        const data = {
        	jobOpeningId: jobOpeningId,
            position: position,
            compensation: compensation,
            contents: contents,
            skill: skill
        };
        
        $.ajax({
            url: '/recruitment/edit',
            type: 'post',
            cache: false,
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType: 'json',
            success: function(response) {
                if(response.success) {
                    alert(response.message);
                    location.href = "/recruitmentDetail/" + jobOpeningId;
                }
            },
            error: function(xhr, status, err) {
            	alert(xhr.responseJSON.message);
				if (xhr.status === 401) location.href = '/login/form';
				console.log(status + ", " + err);
            }
        });
    }
}

//삭제버튼 클릭
function deleteRecruitment(jobOpeningId) {
	if(confirm('삭제하시겠습니까?')) {
		$.ajax({
			url : '/recruitment/delete/'+jobOpeningId,
			type : 'delete',
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
}

//취소버튼 클릭
function cancle(jobOpeningId) {
	if(confirm('취소하시겠습니까?')) {
		location.href = "/recruitmentDetail/" + jobOpeningId;
	}
}
</script>
</head>
<body>

<%@ include file="header.jsp"%>

<div class="body_container">
	<div class="input_container">
		<div>
			<span>채용포지션</span>
			<input type="text" id="position" name="position" value="${jobOpening.position}">
		</div>
		<div>
			<span>채용보상금</span>
			<input type="text" id="compensation" name="compensation" value="${jobOpening.compensation}">
			<span>원</span>
		</div>
		<div>
			<span>사용기술</span>
			<input type="text" id="skill" name="skill" value="${jobOpening.skill}">
		</div>
		<div>
			<div id="contents_title">
				<span>채용내용</span>
			</div>
			<textarea id="contents" name="contents" rows="10" cols="20"></textarea>
		</div>
	</div>
	
	<div class="recruitmentButton_container">
		<button type="button" onclick="editRecruitment(${jobOpening.jobOpeningId})">수정하기</button>
		<button type="button" onclick="cancle(${jobOpening.jobOpeningId})">취소하기</button>
		<button type="button" onclick="deleteRecruitment(${jobOpening.jobOpeningId})">삭제하기</button>
	</div>
	
	
	<textarea id="existingContent" style="display:none;"><c:out value="${jobOpening.contents}"/></textarea>
</div>

</body>
</html>
