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
<script type="text/javascript" src="/BankMore/js/checkData.js"></script>
<script type="text/javascript">

	
	function checkData(){
		let f = document.transferForm;
		
		if(isNull(f.sendNo, "계좌번호를 입력하지 않았습니다"))
			return false;
		
		if(isNull(f.amount, "이체할 금액을 입력하지 않았습니다"))
			return false;
		
		return true;		
	}
	
	
	
</script>
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
				
				
				<c:choose>
					<c:when test="${ empty selectedAccountList }">
						<div class="mx-auto">BankMore에 등록된 계좌가 없습니다<br>계좌 이체 서비스를 이용하기 위해 BankMore에 계좌를 등록해주세요</div>
					</c:when>
					<c:otherwise>
					
						<div id="transfer-div">	
						
							<form action="${ contextPath }/transferCheck.do" method="post" class="w-100" name="transferForm" onsubmit="return checkData()">
							
							
							<div class="pb-2 text-primary font-weight-bold"><span>STEP 1 </span> 본인 계좌를 선택하세요 </div> 
								<c:forEach items="${ selectedAccountList }" var="account">
								<ul class="list-group w-100"> 
									<li class="list-group-item" >
								  		<div class="custom-control custom-radio">
								    		<input required type="radio" class="custom-control-input" id="noTransfer${ account.accountNo }" name="no" value="${ account.accountNo }">
								    		<label class="custom-control-label" for="noTransfer${ account.accountNo }">
								    			${ account.bankName } ${ account.accountNo }
								    			<br>${ account.balance }원
								    		</label>
								  		</div>
									</li>
								</ul>
								</c:forEach>
							<br>
							<div class="pb-2 text-primary font-weight-bold"><span>STEP 2 </span> 송금할 계좌 은행명과 계좌번호를 입력하세요  </div> 
							
							 <label for="sel1">은행명</label>
							  <select class="form-control" name="bank">
							    <option value="하나은행">하나은행</option>
							    <option value="국민은행">국민은행</option>
							    <option value="농협은행">농협은행</option>
							    <option value="신한은행">신한은행</option>
							    <option value="우리은행">우리은행</option>
							  </select>
  							<label for="sendAccountNo">계좌번호</label>
  							<input type="text" class="form-control" placeholder="계좌번호를 입력하세요" name="sendNo">
								
							<br>
							<div class="pb-2 text-primary font-weight-bold"><span>STEP 3 </span> 송금할 금액을 입력하세요  </div> 
							<label for="transferAmount">금액</label>
  							<input type="text" class="form-control" placeholder="금액을 입력하세요" name="amount">
							
							<br>	
							<div class="pb-2 text-primary font-weight-bold"><span>STEP 4 </span> 계좌에 표시할 내용을 입력하세요 (선택사항)</div> 
							<label for="transferAmount">내 계좌 표시</label>
  							<input type="text" class="form-control" placeholder="내용을 입력하세요" name="desc">
							
								<button type="submit" class="btn btn-primary mt-3 float-right">
									이체하기
								</button>
							</form>
						</div>
						
						 
					</c:otherwise>
				</c:choose>
				
			</div>
		</div>
	</div>
<!-- transfer-1 select Account -->


<jsp:include page="/include/footer.jsp" />
<jsp:include page="/include/jsFiles.jsp"/>
</body>
</html>