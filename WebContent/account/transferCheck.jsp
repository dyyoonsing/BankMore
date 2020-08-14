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
</head>
<body>
<jsp:include page="/include/header.jsp"></jsp:include>
<!-- Page info section -->
	<section class="page-info-section">
		<div class="container">
			<h2>계좌이체</h2>
			<div class="site-beradcamb">
				<a href="/BankMore">메인화면</a>
				<a href="/BankMore"><i class="fa fa-angle-right"></i> My 계좌</a>
				<span><i class="fa fa-angle-right"></i> 계좌이체</span>
			</div>
		</div>
	</section>
<!-- Page info end -->

<!-- transfer-1 select Account -->
	<div class="container p-5 allAccount-con">
		<div class="row">
			<!-- side menu col-md-3   -->
			<jsp:include page="/include/accountSideMenu.jsp"></jsp:include>
			
			<div class="col-md-9">
				<div class="pb-2 text-primary font-weight-bold"> 계좌이체 성공 - 내역 확인 </div> 
				<div class="pl-3 pr-3">
				<table class="table-borderless table p text-center">
					<tr>
						<th class="board-md">출금계좌</th>
						<td>${ statement.accountNo }</td>
					</tr>
					<tr>
						<th class="board-md">금액</th>
						<td>${ statement.amount }</td>
					</tr>
					<tr>
						<th class="board-md">내계좌 표시</th>
						<td>${ statement.myDesc }</td>
					</tr>
					<tr>
						<th class="board-md">받는분 계좌</th>
						<td>${ statement.transAccountNo }</td>
					</tr>
					<tr>
						<th class="board-md">받는분</th>
						<td>${ transName }</td>
					</tr>
					
				</table>
				</div>
			</div>
		</div>
	</div>
<!-- transfer-1 select Account -->





<jsp:include page="/include/footer.jsp" />
<jsp:include page="/include/jsFiles.jsp"/>
</body>
</html>