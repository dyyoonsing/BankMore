<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/include/headcss.jsp"></jsp:include>
<script type = "text/javascript" src = "https://developers.kakao.com/sdk/js/kakao.min.js" >  </script>
<script type = "text/javascript">
	
	 // 웹 플랫폼 도메인 등 초기화한 앱의 설정이 그대로 적용됩니다.
	 // 초기화한 앱에 현재 도메인이 등록되지 않은 경우 에러가 발생합니다.
	  Kakao.init('-----key-----')
	  function addKakao() {
	    Kakao.Auth.loginForm({
	      success: function(authObj) {
	    	// console.log( authObj );
	            Kakao.API.request({
	                url: "/v2/user/me",
	                success: function (res) {
	                    // console.log( res );
	                	//res.id = '@k' + res.id;
	                    var params = {
	                          kakaoId : res.id,
	                          email : res.kakao_account.email,
	                          name : res.properties.nickname
	                    }
	                    
	                    post_to_url("${ contextPath }/addKakao.do", params, "post")
	
	
	                },
	                fail: function (error) {
	                    alert(JSON
	                        .stringify(error));
	                }
	            });
	      },
	      fail: function(err) {
	        alert(JSON.stringify(err))
	      },
	    })
	  }
	
    
    // 카카오 request에서 세팅한 params를 JSON 형태로 넘긴다. form을 이용해 값을 넘긴다.
    function post_to_url(path, params, method) {

       method = method || "post";
       var form = document.createElement("form");
       form.setAttribute("method", method);
       form.setAttribute("action", path);
       console.log(params)
       for (var key in params) {
          var hiddenField = document.createElement("input");
          hiddenField.setAttribute("type", "hidden");
          hiddenField.setAttribute("name", key);
          console.log(key);
          hiddenField.setAttribute("value", params[key]);
          console.log(params[key]);
          form.appendChild(hiddenField);
       }
       document.body.appendChild(form);
       form.submit();
    }
    
    function checkExit(){
    	if(confirm('탈퇴하면 모든 기록이 삭제됩니다\n정말로 탈퇴하시겠습니까?')){
    		location.href = '${ contextPath }/exit.do'
    	}
    	
    }
    
</script>
</head>
<body>
	<jsp:include page="/include/header.jsp"/>
	<!-- Page info section -->
	<section class="page-info-section">
		<div class="container">
			<h2>마이페이지</h2>
			<div class="site-beradcamb">
				<a href="${ pageContext.request.contextPath }">메인화면</a>
				<span><i class="fa fa-angle-right"></i> 마이페이지</span>
			</div>
		</div>
	</section>
	<!-- Page info end -->
	
	<div class="container p-5">
	<table class="table">
		<tr>
			<th>아이디</th>
			<td>${ pageUser.id }</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${ pageUser.name }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${ pageUser.email }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${ pageUser.tel }</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>( ${ pageUser.post } ) ${ pageUser.basicAddr }<br>${ pageUser.detailAddr }</td>
		</tr>
	    <tr>
	    	<th>가입날짜</th>
	    	<td>${ pageUser.regDate }</td>
	    </tr>
	    <tr>
	    	<th>카카오톡 연동여부</th>
	    	<td>
	    		<c:choose>
	    			<c:when test="${ not empty pageUser.kakaoId }">
	    				연동됨
						<!-- <button class="btn btn-warning">카카오 연동 끊기</button> -->
	    			</c:when>
	    			<c:otherwise>
	    				연동안됨
	    				<a id="addKakao-btn" href="javascript:addKakao()">
						  <img
						    src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"
						    width="222"
						  />
						</a>
	    			</c:otherwise>
	    		</c:choose>
	    	</td>
	    </tr>	
	  </table>
	  <div class="text-center">
		<button class="btn btn-primary" onclick="checkExit()">회원탈퇴</button>
		</div>  	
	</div>
	<jsp:include page="/include/footer.jsp" />
	<jsp:include page="/include/jsFiles.jsp"/>
</body>
</html>