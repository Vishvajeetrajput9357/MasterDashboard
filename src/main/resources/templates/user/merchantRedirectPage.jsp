<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="utf-8" />
	 <meta name="viewport" content="width=device-width, initial-scale=1" />
	 <title>Fidypay</title>
	<link rel="icon" type="image/png" href="data:image/png;base64,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" />

	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
	     integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous" />
	     <link rel="stylesheet" th:href="@{/Css/style.module.css}" />
		 
    <script th:inline="javascript">
        function onLoadSubmit() {
            // 3-second delay before form submission
            setTimeout(function() {
                var user = [[${redirectURL}]];
                // Submit the form after the delay
                document.getElementById('login').submit();
            }, 3000);  // 3000 milliseconds = 3 seconds
        }
    </script>
</head>

<body th:onload="onLoadSubmit()">
	
	<header style="box-shadow: 0 5px 10px rgb(0 0 0 / 0.2)">
	       <div class="header_section"
	           style="padding: 3px; display: flex; justify-content: space-between; align-items: center; margin: 0px 30px;">
	           <div>
	               <img th:src="@{/Images/fidypaylogo.png}" class="logo_img" style="width: 150px; height: 50px" />
	           </div>
	           <div>
	               <img th:src="${merchantlogo}" style="width: 80px; height: 50px" />
	           </div>
	       </div>
	   </header>

	   <div class="section_container">
	       <div class="section_container_wrapper">
	           <div class="section_wrapper_left">
		              		 <div style="margin-top: 20px;">
			                   <h5 style="color: rgb(56, 23, 118); letter-spacing: 1.5px; text-align: center;">
			                       <span th:utext="${Success}"></span>
			                   </h5>
		                   	  <div>
	                       <div>Redirecting to the merchant site.. </div>
						   <br>
						   <br>
						  <center> <div class="loader"></div></center>
	                   </div>
			   
			   <form name="login" id="login" th:action="${redirectURL}" method="post">
			           <input type="hidden" name="mode" th:value="${mode}" />
			           <input type="hidden" name="txnid" th:value="${txnid}" />
			           <input type="hidden" name="date" th:value="${date}" />
			           <input type="hidden" name="amount" th:value="${amount}" />
			           <input type="hidden" name="status" th:value="${status}" />
			       </form>
	       </div>
		   </div>

	       <style>
	           .resend_OTP {
	               letter-spacing: 2px;
	               color: rgb(56, 23, 118);
	               font-size: 14px;
	           }

	           @media (max-width: 768px) {
	               .section_container_wrapper {
	                   width: auto;
	                   flex-direction: column;
	               }

	               .section_container_wrapper .section_wrapper_left {
	                   width: 100%;
	               }

	               .section_wrapper_left {
	                   display: flex;
	                   flex-direction: column;
	                   width: 100%;
	               }

	               .vr {
	                   display: none;
	               }
	           }

	           .section_wrapper_right input {
	               background-color: rgb(239, 236, 236);
	               padding: 10px;
	               border-color: transparent;
	               border-radius: 8px;
	               font-size: 18px;
	               color: darkgrey;
	           }

	           .section_container_wrapper {
	               height: auto;
	               margin: 70px 30px;
	               padding: 25px 15px;
	               display: flex;
	               background-color: rgba(255, 255, 255, 0.7);
	               box-shadow: 0 15px 15px rgb(0 0 0 / 0.2);
	               border-radius: 8px;
	               justify-content: space-around;
	               width: auto;

	               @media (max-width: 768px) {
	                   display: flex;
	                   align-items: center;
	                   justify-content: center;
	               }
	           }

	           .label-input-pair {
	               display: inline-block;
	               margin-right: 5px;
	           }

	           .checkbox-label-pair span {
	               display: inline-flex;
	               align-items: center;
	           }

	           .checkbox-label-pair input[type="checkbox"] {
	               margin-right: 10px;
	           }
			   

			  
			   

			   .loader {
			   animation: rotate 1s infinite;
			   height: 50px;
			   width: 50px;
			   }

			   .loader:before,
			   .loader:after {
			   border-radius: 50%;
			   content: '';
			   display: block;
			   height: 20px;
			   width: 20px;
			   }
			   .loader:before {
			   animation: ball1 1s infinite;
			   background-color: #cb2025;
			   box-shadow: 30px 0 0 #f8b334;
			   margin-bottom: 10px;
			   }
			   .loader:after {
			   animation: ball2 1s infinite;
			   background-color: #00a096;
			   box-shadow: 30px 0 0 #97bf0d;
			   }

			   @keyframes rotate {
			   0% {
			   -webkit-transform: rotate(0deg) scale(0.8);
			   -moz-transform: rotate(0deg) scale(0.8);
			   }
			   50% {
			   -webkit-transform: rotate(360deg) scale(1.2);
			   -moz-transform: rotate(360deg) scale(1.2);
			   }
			   100% {
			   -webkit-transform: rotate(720deg) scale(0.8);
			   -moz-transform: rotate(720deg) scale(0.8);
			   }
			   }

			   @keyframes ball1 {
			   0% {
			   box-shadow: 30px 0 0 #f8b334;
			   }
			   50% {
			   box-shadow: 0 0 0 #f8b334;
			   margin-bottom: 0;
			   -webkit-transform: translate(15px,15px);
			   -moz-transform: translate(15px, 15px);
			   }
			   100% {
			   box-shadow: 30px 0 0 #f8b334;
			   margin-bottom: 10px;
			   }
			   }

			   @keyframes ball2 {
			   0% {
			   box-shadow: 30px 0 0 #97bf0d;
			   }
			   50% {
			   box-shadow: 0 0 0 #97bf0d;
			   margin-top: -20px;
			   -webkit-transform: translate(15px,15px);
			   -moz-transform: translate(15px, 15px);
			   }
			   100% {
			   box-shadow: 30px 0 0 #97bf0d;
			   margin-top: 0;
			   }
			   }
	       </style>

    

</body>
</html>

	