<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>KJSSTPAY</title>
	<link rel="icon" type="image/png" th:href="@{/Images/merchantlogo.png}" />

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous" />
	<link rel="stylesheet" th:href="@{/Css/style.module.css}" />

	<script th:inline="javascript">
		function onLoadSubmit() {
			// 3-second delay before form submission
			setTimeout(function () {
				var user = [[${ redirectURL }]];
				// Submit the form after the delay
				document.getElementById('login').submit();
			}, 3000);  // 3000 milliseconds = 3 seconds
		}
	</script>
</head>

<body>

	<!--<header style="box-shadow: 0 5px 10px rgb(0 0 0 / 0.2)">
	       <div class="header_section"
	           style="padding: 3px; display: flex; justify-content: space-between; align-items: center; margin: 0px 30px;">
	           <div>
	               <img th:src="@{/Images/merchantLogo.png}" class="logo_img" style="width: 150px; height: 50px" />
	           </div>
	           <div>
	               <img th:src="@{/Images/merchantLogo.png}" style="width: 80px; height: 50px" />
	           </div>
	       </div>	
	   </header> 

	<div class="section_container">
		<div class="section_container_wrapper">
			<div class="section_wrapper_left">
				<div style="margin-top: 20px;">
					<h5 style="color: rgb(56, 23, 118); letter-spacing: 1.5px; text-align: center;">
						<span th:utext="${message}"></span>
					</h5>
					<div>
						<div>
						
						</div>

						<div>Redirecting to the merchant site.. </div>
						<br>
						<br>
						<center>
							<div class="loader"></div>
						</center>
					</div>
				</div>
			</div>-->
			<script>
			setTimeout(function () {
					window.location.href = "https://hemendra-rajput.dixzdxouk0355.amplifyapp.com/";
				},);

			</script>
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
						-webkit-transform: translate(15px, 15px);
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
						-webkit-transform: translate(15px, 15px);
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