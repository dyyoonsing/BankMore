<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	
	<!-- Header section -->
	<header class="header-section clearfix">
		<div class="container-fluid">
			<a href="${ pageContext.request.contextPath }" class="site-logo">
				<img src="/BankMore/img/logo.png">
			</a>
			<div class="responsive-bar"><i class="fa fa-bars"></i></div>
			<a href="" class="user"><i class="fa fa-user"></i></a>
				<c:choose>
					<c:when test="${ not empty userVO }">
						<a href="${ pageContext.request.contextPath }/logout.do" class="site-btn">로그아웃</a>
					</c:when>
					<c:otherwise>
						<a href="${ pageContext.request.contextPath }/login.do" class="site-btn">로그인</a>
					</c:otherwise>
				</c:choose>
			<nav class="main-menu">
				<ul class="menu-list">
					<li class="dropdown">
					 <c:if test="${ userVO.type ne 'S' }">
					 	  <a class="dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    My 계좌
						  </a>
						  <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
							    <a class="dropdown-item" href="${ pageContext.request.contextPath}/selectAllAccount.do">계좌 관리</a>
							    <a class="dropdown-item" href="${ pageContext.request.contextPath}/accountAddDelete.do">오픈뱅킹 연결</a>
							    <a class="dropdown-item" href="${ pageContext.request.contextPath }/selectAllStatement.do">거래내역조회</a>
							    <a class="dropdown-item" href="${ pageContext.request.contextPath }/transfer.do">계좌이체</a>
						  </div>
					 
					 </c:if>
					  
					
					</li>
					<li><a href="${ pageContext.request.contextPath }/boardList.do">Q&amp;A 게시판</a></li>
					<c:if test="${ not empty userVO }">
						<li><a href="${ pageContext.request.contextPath }/myPage.do">${ userVO.name }님의 페이지</a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</header>
	<!-- Header section end -->
	
