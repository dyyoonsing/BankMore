<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
<meta charset="UTF-8">
<meta name="description" content="LoginForm page">
<jsp:include page="/include/headcss.jsp"></jsp:include>
<script type = "text/javascript" src = "https://developers.kakao.com/sdk/js/kakao.min.js" >  </script>
<script type = "text/javascript">
	
	 // 웹 플랫폼 도메인 등 초기화한 앱의 설정이 그대로 적용됩니다.
	 // 초기화한 앱에 현재 도메인이 등록되지 않은 경우 에러가 발생합니다.
	  Kakao.init('-----key-----')
	  function loginFormWithKakao() {
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
	                    
	                    post_to_url("${ contextPath }/loginProcess.do", params, "post")
	
	
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
    
</script>
</head>
<body>
	<jsp:include page="/include/header.jsp"/>
	
	<!-- Page info section -->
	<section class="page-info-section">
		<div class="container">
			<h2>로그인</h2>
			<div class="site-beradcamb">
				<a href="${ pageContext.request.contextPath }">메인화면</a>
				<span><i class="fa fa-angle-right"></i> 로그인</span>
			</div>
		</div>
	</section>
	<!-- Page info end -->
	
	<section class="container h-75 login-section">
		<div class="row d-flex justify-content-center h-75 align-items-center">
			<div class="col-6 text-center login-div">
				<form name="loginF" action="${ pageContext.request.contextPath }/loginProcess.do" method="post" onsubmit="return true">
				<br><h3>환영합니다</h3><br>
					<input type="text" name="id" placeholder="아이디" value="hong">
					<br>
					<input type="password" name="password" placeholder="비밀번호" value="1234">
					<br>
					<input type="submit" value="로그인" id="loginSubmit">
				</form>
				<a id="login-form-btn" href="javascript:loginFormWithKakao()">
				  <img
				    src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"
				    width="222"
				  />
				</a>
			</div>
			
		</div>
	</section>
	
				
	<jsp:include page="/include/footer.jsp" />
	<jsp:include page="/include/jsFiles.jsp"/>
	
</body>
</html>