<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Bank More.</title>
	<meta charset="UTF-8">
	<meta name="description" content="BankMore Main page">	
	<jsp:include page="/include/headcss.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/include/header.jsp"/>

	<!-- Hero section -->
	<section class="hero-section">
		<div class="container">
			<div class="row">
				<div class="col-md-6 hero-text">
					<h2>통합계좌관리시스템 <br><span>Bank More.</span></h2>
					<c:choose>
						<c:when test="${ empty userVO }">
							<button class="site-btn sb-gradients" onclick="window.location.href='${ pageContext.request.contextPath }/signin.do'">회원가입</button>
						</c:when>
						<c:otherwise>
							<h4><span>${ userVO.name }</span>님 반갑습니다</h4>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</section>
	<!-- Hero section end -->

	<!-- Process section -->
	<section class="process-section spad">
		<div class="container">
			<div class="row">
				<div class="col-sm process">
					<div class="process-step" onclick="window.location.href='${ pageContext.request.contextPath}/selectAllAccount.do'">
						<figure class="process-icon">
							<img src="img/process-icons/1.png" alt="#">
						</figure>
						<h4>계좌관리</h4>
					</div>
				</div>
				<div class="col-sm process">
					<div class="process-step" onclick="window.location.href='${ pageContext.request.contextPath}/transfer.do'">
						<figure class="process-icon">
							<img src="img/process-icons/2.png" alt="#">
						</figure>
						<h4>계좌이체</h4>
					</div>
				</div>
				<div class="col-sm process">
					<div class="process-step" onclick="window.location.href='${ pageContext.request.contextPath}/selectAllStatement.do'">
						<figure class="process-icon">
							<img src="img/process-icons/3.png" alt="#">
						</figure>
						<h4>거래내역조회</h4>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Process section end -->

	<!-- About section -->
	<section class="about-section spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 offset-lg-6 about-text">
					<h2>What is BankMore</h2>
					<h5>BankMore is an innovative payment network and a new kind of money.</h5>
					<p>BankMore is one of the most important inventions in all of human history. For the first time ever, anyone can send or receive any amount of money with anyone else, anywhere on the planet, conveniently and without restriction. It’s the dawn of a better, more free world.</p>
				</div>
			</div>
			<div class="about-img">
				<img src="img/about-img.png" alt="">
			</div>
		</div>
	</section>
	<!-- About section end -->
	
	

	

	<!-- Review section -->
	<section class="review-section spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 push-8">
					<img src="img/quote.png" alt="" class="quote mb-5">
					<div class="review-text-slider owl-carousel">
						<div class="review-text">
							<p>"BankMore is exciting because it shows how cheap it can be. Bitcoin is better than currency in that you don’t have to be physically in the same place and, of course, for large transactions, currency can get pretty inconvenient.”</p>
						</div>
						<div class="review-text">
							<p>"BankMore is exciting because it shows how cheap it can be. Bitcoin is better than currency in that you don’t have to be physically in the same place and, of course, for large transactions, currency can get pretty inconvenient.”</p>
						</div>
						<div class="review-text">
							<p>"BankMore is exciting because it shows how cheap it can be. Bitcoin is better than currency in that you don’t have to be physically in the same place and, of course, for large transactions, currency can get pretty inconvenient.”</p>
						</div>
						<div class="review-text">
							<p>"BankMore is exciting because it shows how cheap it can be. Bitcoin is better than currency in that you don’t have to be physically in the same place and, of course, for large transactions, currency can get pretty inconvenient.”</p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 pr-0 pull-3">
					<div class="review-meta-slider owl-carousel pt-5">
						<div class="author-meta">
							<div class="author-avatar set-bg" data-setbg="img/review/1.png"></div>
							<div class="author-name">
								<h4>윤다영</h4>
								<p>CEO Chief Executive Officer</p>
							</div>
						</div>
						<div class="author-meta">
							<div class="author-avatar set-bg" data-setbg="img/review/2.png"></div>
							<div class="author-name">
								<h4>양은희</h4>
								<p>CFO Chief Financial Officer</p>
							</div>
						</div>
						<div class="author-meta">
							<div class="author-avatar set-bg" data-setbg="img/review/3.png"></div>
							<div class="author-name">
								<h4>한미희</h4>
								<p>CTO Chief Technology Officer</p>
							</div>
						</div>
						<div class="author-meta">
							<div class="author-avatar set-bg" data-setbg="img/review/4.png"></div>
							<div class="author-name">
								<h4>전혜원</h4>
								<p>CHO Chief Human resource Officer</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Review section end -->
	
	
	
	
	
	
	<!-- map section -->
	<section class="map-section spad">
		<div class="container map-page">
			<div class="row">
				<div class="col-lg-5 map-info-div">
					<h3>오시는길</h3>
					<div>
						<img alt="" src="/BankMore/img/addr.png">
						<span>경기도 광명시 오리로 904, 광명융합기술원</span>
					</div>
					<div>
						<img alt="" src="/BankMore/img/phone.png">
						<span>010-1234-5678</span>
					</div>
					<div>
						<img alt="" src="/BankMore/img/email.png">
						<span>dysing@bankmore.co.kr</span>
					</div>
				</div>
				<div class="col-lg-7 mt-5 mt-lg-0">
					<div class="map" id="map-canvas"></div>
				</div>
			</div>
		</div>
	</section>
	<!-- Map section end -->
	
	
	
		<!-- Fact section -->
	<section class="fact-section gradient-bg">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-md-6 col-lg-3">
					<div class="fact">
						<h2>60</h2>
						<p>Support <br> Countries</p>
						<i class="ti-basketball"></i>
					</div>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-3">
					<div class="fact">
						<h2>12K</h2>
						<p>Transactions  <br> per hour</p>
						<i class="ti-panel"></i>
					</div>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-3">
					<div class="fact">
						<h2>5B</h2>
						<p>Largest <br> Transactions</p>
						<i class="ti-stats-up"></i>
					</div>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-3">
					<div class="fact">
						<h2>240</h2>
						<p>Years <br> of Experience</p>
						<i class="ti-user"></i>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Fact section end -->
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=-----key-----"></script>
	<script src="/BankMore/js/map.js"></script>
	
	<jsp:include page="/include/footer.jsp" />
	<jsp:include page="/include/jsFiles.jsp"/>

</body>
</html>
