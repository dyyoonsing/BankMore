<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>회원가입</title>
	<meta charset="UTF-8">
	<meta name="description" content="Signin Form">
	<jsp:include page="/include/headcss.jsp"></jsp:include>
	<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
	<script>
		window.onload = function(){
			if(${ not empty kakaoVO }){
				//카카오로 로그인했으면 hidden으로 넣고 이름, 이메일 값 넣고 안적히게 하기
				var f = document.signinF;
				f.name.value = '${ kakaoVO.name }'
				f.email.value = '${ kakaoVO.email }'
				//f.name.readOnly = true;
				//f.email.readOnly = true;
				
			}
		}
		
		function checkId(){
			
			var value = document.signinF.id.value;
			var link = "${ contextPath }" + "/checkId.do?id=" + value;
			console.log(value)
			
			$.ajax({
				type : 'GET',
				url : link,
				success : function(data){
					console.log('통신성공');
					$('#checkIdDiv').html(data)
				},
				error : function(){
					alert('통신실패');
				}
			})
		}
		
		function openPost() {
			new daum.Postcode({
				oncomplete: function(data) {
					$('[name=post]').val(data.zonecode); // 우편번호 (5자리)
					$('[name=basicAddr]').val(data.address);
					$('[name=detailAddr]').val(data.buildingName);
				}
			}).open();
		}
	
	</script>
</head>
<body>
	<jsp:include page="/include/header.jsp"/>
	
	<!-- Page info section -->
	<section class="page-info-section">
		<div class="container">
			<h2>회원가입</h2>
			<div class="site-beradcamb">
				<a href="${ pageContext.request.contextPath }">메인화면</a>
				<span><i class="fa fa-angle-right"></i>회원가입</span>
			</div>
		</div>
	</section>
	<!-- Page info end -->
	
	<section class="container signin-section">
		<div class="row justify-content-md-center">
			<div class="col-8 signin-col signin-div">
				<!-- <br><h3>환영합니다</h3><br> -->
				<form name="signinF" action="${ pageContext.request.contextPath }/signinProcess.do" method="post" onsubmit="return true">
					<input type="hidden" name="kakaoId" value="${ kakaoVO.kakaoId }">
					<div class="form-group">
						<label for="loginid">아이디</label>
						<div class="input-group">
							<input type="text" id="loginid" class="form-control" placeholder="아이디를 입력하세요" name="id">
						<div class="input-group-append">
							 <button class="btn btn-primary" type="button" onclick="javascript:checkId()">중복체크</button>
						</div>
						</div>
						<h6 id="checkIdDiv" class="float-right pt-2"></h6>						  
					</div>
					<div class="form-group">
						<label for="password">비밀번호</label>
						<input type="password" id="password" class="form-control" placeholder="비밀번호를 입력하세요" name="password">
					</div>
					<div class="form-group">
						<label for="passwordCh">비밀번호 확인</label>
						<input type="password" id="passwordCh" class="form-control" placeholder="비밀번호를 다시 입력하세요" name="passwordCh">
					</div>
					<div class="form-group">
						<label for="memName">이름</label>
						<input type="text" id="memName" class="form-control" placeholder="이름을 입력하세요" name="name">
					</div>
					<div class="form-group">
						<label for="email">이메일</label>
						<input type="email" id="email" class="form-control" placeholder="@를 포함하여 작성하세요" name="email">
					</div>
					<div class="form-group">
						<label for="tel">전화번호</label>
						<input type="text" id="tel" class="form-control" placeholder="-없이 숫자만 입력하세요" name="tel">
					</div>
					<div class="container">
						<div class="row">
							<div class="col-md-4 signin-post-col">
								<div class="form-group">
									<label for="post">우편번호</label>
									<div class="input-group">
										<input type="text" id="post" class="form-control" placeholder="우편번호를 검색하세요" name="post">
									<div class="input-group-append">
										<button class="btn btn-primary" type="button" onclick="javascript:openPost()">찾기</button>
									</div>
									</div>
								</div>
							</div>
							<div class="col-md-8 signin-post-col">
								<div class="form-group">
									<label for="basicAddr">기본주소</label>
									<input type="text" id="basicAddr" class="form-control" placeholder="기본주소를 입력하세요" readonly name="basicAddr">
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="form-group">
						<label for="detailAddr">상세주소</label>
						<input type="text" id="detailAddr" class="form-control" placeholder="상세주소를 입력하세요" name="detailAddr">
					</div>
					<button type="submit" class="btn btn-primary btn-block" id="signinBtn">회원가입</button>
				
				</form>
				
			</div>
			
		</div>
	</section>
	
	



	<jsp:include page="/include/footer.jsp" />
	
	<!--====== Javascripts & Jquery ======-->
	<script src="/BankMore/js/jquery-3.2.1.min.js"></script>
	<script src="/BankMore/js/owl.carousel.min.js"></script>
	<script src="/BankMore/js/main.js"></script>
</body>
</html>