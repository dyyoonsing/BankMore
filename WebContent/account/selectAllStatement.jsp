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
	
	function selectStatement(accountNo){
		
		var link = "${ contextPath }" + "/statementProcess.do?accountNo=" + accountNo;
		console.log(accountNo)
		
		$.ajax({
			type : 'GET',
			url : link,
			success : function(data){
				console.log('통신성공')
				$('#statementDetail').html(data)
			},
			error : function(){
				alert('통신실패');
			}
		})
	}
	
	
	
		
	</script>	
	

</head>
<body>
	<jsp:include page="/include/header.jsp"></jsp:include>
	
	<!-- Page info section -->
	<section class="page-info-section">
		<div class="container">
			<h2>거래내역조회</h2>
			<div class="site-beradcamb">
				<a href="/BankMore">메인화면</a>
				<a href="/BankMore/index.jsp"><i class="fa fa-angle-right"></i> My 계좌</a>
				<span><i class="fa fa-angle-right"></i> 거래내역조회</span>
			</div>
		</div>
	</section>
	<!-- Page info end -->
	
	
	<!-- selectAllStatement page start -->
	<div class="container p-5 allAccount-con">
		<div class="row">
			<!-- side menu col-md-3   -->
			<jsp:include page="/include/accountSideMenu.jsp"></jsp:include>
			
			<div class="col-md-9">
				
				
				
				<c:choose>
				<c:when test="${ empty accountList }">
					<div class="p-2">
					존재하는 계좌가 없습니다
					</div>
				</c:when>
				<c:otherwise>
				<div id="selectAllStatement">	
						
						<div class="list-group w-100">
						<c:forEach items="${ accountList }" var="account">
							<a href="javascript:selectStatement('${ account.accountNo }')" class="list-group-item list-group-item-action">
							    ${ account.bankName } ${ account.accountNo }	<span class="float-right">${ account.balance } 원</span>
							</a>
						</c:forEach>
						</div>
				</div>
				
				
				<div id="statementDetail" class="pt-3"></div>
				
				
				
				</c:otherwise>
				</c:choose>
				
				
				
				
				
				
				
				
				
				
				
				
			</div>
		</div>
	</div>
	<!-- selectAllStatement page end -->
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<jsp:include page="/include/footer.jsp" />
	<jsp:include page="/include/jsFiles.jsp"/>
</body>
</html>