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
			<h2>계좌관리</h2>
			<div class="site-beradcamb">
				<a href="/BankMore">메인화면</a>
				<a href="/BankMore/index.jsp"><i class="fa fa-angle-right"></i> My 계좌</a>
				<span><i class="fa fa-angle-right"></i> 계좌관리</span>
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
				
				<c:choose>
					<c:when test="${ empty accountList }">
						<div class="mx-auto">BankMore에 추가한 계좌가 없습니다<br>계좌 관리를 위해서 오픈뱅킹 계좌추가를 해주세요</div>
					</c:when>
					<c:otherwise>
						<div class="p-3">${ userVO.name }님의 총 자산은 ${ totalBalance } 원 입니다</div>
						
						 
						<table class="table text-center">
						    <thead>
						      <tr>
						        <th>은행</th>
						        <th>계좌번호</th>
						        <th>별칭</th>
						        <th>신규일</th>
						        <th>잔액</th>
						      </tr>
						    </thead>
								<c:forEach items="${ accountList }" var="account" varStatus="loop">
									<tr>
										<td>${ account.bankName }</td>
										<td>${ account.accountNo }</td>
										<td>${ account.alias }</td>
										<td>${ account.openDate }</td>
										<td>${ account.balance } 원</td>
									</tr>
								</c:forEach>
						</table>
						
						 
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<!-- selectAllAccount page end -->
	
	
	
	
	
	
	
	
	<jsp:include page="/include/footer.jsp" />
	<jsp:include page="/include/jsFiles.jsp"/>
</body>
</html>