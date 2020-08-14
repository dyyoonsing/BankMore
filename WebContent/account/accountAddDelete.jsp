<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Bank More.</title>
	<meta charset="UTF-8">
	<meta name="description" content="select All Account page">
	<jsp:include page="/include/headcss.jsp"></jsp:include>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$('.tabmenu').click(function(){
				var activeTab = $(this).attr('data-tab');
				
				$.ajax({
					type : 'GET',
					url : "/BankMore" + activeTab,
					success : function(data){
						if(activeTab == "/accountListAll.do"){
							$('#nav-tabs-not-default-account-id').removeClass("active");
							$('#nav-tabs-default-account-id').addClass("active");
							$('#tabcontent').empty();
							$('#tabcontent').html(data);							
						}else if(activeTab == "/accountListSelected.do"){
							$('#nav-tabs-default-account-id').removeClass("active");
							$('#nav-tabs-not-default-account-id').addClass("active");
							$('#tabcontent').empty();
							$('#tabcontent').html(data);
						}
					},
					error : function(){
						alert('통신실패');
					}
				})
				
			})
			$('#nav-tabs-default-account').click();
		})
	
	</script>	
	

</head>
<body>
	<jsp:include page="/include/header.jsp"></jsp:include>
	
	<!-- Page info section -->
	<section class="page-info-section">
		<div class="container">
			<h2>오픈뱅킹연결</h2>
			<div class="site-beradcamb">
				<a href="/BankMore">메인화면</a>
				<a href="/BankMore/index.jsp"><i class="fa fa-angle-right"></i> My 계좌</a>
				<span><i class="fa fa-angle-right"></i> 오픈뱅킹연결</span>
			</div>
		</div>
	</section>
	<!-- Page info end -->
	
	
	<!-- selectAllAccount page start -->
	<div class="container p-5 allAccount-con">
	<div class="row">
		<!-- side menu col-md-3   -->
		<jsp:include page="/include/accountSideMenu.jsp"></jsp:include>
		
		<div class="col-md-9">
			
			<!-- nav tab 바 섹션 시작 -->
			<ul class="nav nav-tabs">
				<li class="nav-item tabmenu" id="nav-tabs-default-account" data-tab="/accountListAll.do" >
					<a id="nav-tabs-default-account-id" class="nav-link active" href="#tabcontent-default">외부계좌 등록</a>
				</li>
				<li class="nav-item tabmenu" id="nav-tabs-not-default-account" data-tab="/accountListSelected.do" >
					<a id="nav-tabs-not-default-account-id" class="nav-link" href="#tabcontent-not-default">등록계좌 삭제</a>
				</li>
			</ul>
			<!-- nav tab 바 섹션 끝 -->	
			<div id="tabcontent"></div>
			
		</div>
	</div>
	</div>
	<!-- selectAllAccount page end -->
	
	
	
	
	
	
	
	<jsp:include page="/include/footer.jsp" />
	<jsp:include page="/include/jsFiles.jsp"/>
</body>
</html>