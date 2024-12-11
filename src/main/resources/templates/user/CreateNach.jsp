<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">

<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>KJSSTPAY</title>
	<link rel="icon" type="image/svg+xml" href="https://kjpay.dke6xtakwgdt0.amplifyapp.com/assets/kjsst%20pay%20logo%20(1).af8c56e7.png" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous" />
	<link rel="stylesheet" href="style.module.css" />

	<style>
	@import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap');

	* {
	  box-sizing: border-box;
	  margin: 0;
	  padding: 0;
	}

	body {
	  width: 100%;
	  height: 100vh;
	  display: flex;
	  justify-content: center;
	  align-items: center;
	  background-image: radial-gradient(circle farthest-corner at 10% 20%, rgba(0, 152, 155, 1) 0.1%, RGB(245 139 91) 94.2%);
	  background-size: cover;
	  font-family: 'Montserrat', sans-serif;
	  overflow: hidden;
	}

	.loading-container {
	  width: 90%; /* Adjust width dynamically */
	  max-width: 500px;
	  text-align: center;
	  color: #fff;
	  position: relative;
	  padding: 20px; /* Add some padding */
	  margin: 0 auto; /* Center align */
	}

	.loading-container:before {
	  content: '';
	  position: absolute;
	  width: 100%;
	  height: 3px;
	  background-color: #fff;
	  bottom: 0;
	  left: 0;
	  border-radius: 10px;
	  animation: movingLine 2.4s infinite ease-in-out;
	}

	@keyframes movingLine {
	  0% {
	    opacity: 0;
	    width: 0;
	  }
	  33.3%, 66% {
	    opacity: 0.8;
	    width: 100%;
	  }
	  85% {
	    width: 0;
	    left: initial;
	    right: 0;
	    opacity: 1;
	  }
	  100% {
	    opacity: 0;
	    width: 0;
	  }
	}

	.loading-text {
	  line-height: 1.5em;
	  letter-spacing: 2px;
	  margin-bottom: 32px;
	  display: flex;
	  justify-content: center;
	  flex-wrap: wrap;
	}

	.loading-text span {
	  animation: moveLetters 2.4s infinite ease-in-out;
	  transform: translateX(0);
	  position: relative;
	  display: inline-block;
	  opacity: 0;
	  text-shadow: 0px 2px 10px rgba(46, 74, 81, 0.3);

	}

	.loading-text span:nth-child(1) { animation-delay: 0.1s; }
	.loading-text span:nth-child(2) { animation-delay: 0.2s; }
	.loading-text span:nth-child(3) { animation-delay: 0.3s; }
	.loading-text span:nth-child(4) { animation-delay: 0.4s; }
	.loading-text span:nth-child(5) { animation-delay: 0.5s; }
	.loading-text span:nth-child(6) { animation-delay: 0.6s; }

	@keyframes moveLetters {
	  0% {
	    transform: translateX(-15vw);
	    opacity: 0;
	  }
	  33.3%, 66% {
	    transform: translateX(0);
	    opacity: 1;
	  }
	  100% {
	    transform: translateX(12vw);
	    opacity: 0;
		
	  }
	}

	@media (max-width: 768px) {
	  .loading-text {
	    font-size: 6vw; /* Larger for smaller screens */
	    letter-spacing: 1px;
	  }
	}

	@media (max-width: 480px) {
	  .loading-container {
	    padding: 10px;
	  }
	  .loading-text {
	    font-size: 5vw; /* Even larger for extra small devices */
	  }
	}
	</style>

</head>
<body>

    <div class="container">
        <form id="PostForm" name="PostForm"  action="https://emandateut.hdfcbank.com/Emandate.aspx" method="POST">
            <div class="form-group">
                <lable style="display: none;"for="UtilCode">UtilCode</label>
                <input hidden  type="text" ID="UtilCode" name="UtilCode"
                    th:value="${utillyCode}">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Short_Code">Short Code</label>
                <input hidden  type="text" ID="Short_Code" name="Short_Code" th:value="${shortCode}">
            </div>
            
            <div class="form-group">
                
                <lable style="display: none;"for="Short_Code">Merchant_PartyName</label>
                <input hidden  type="text" ID="Merchant_PartyName" name="Merchant_PartyName" value="">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Short_Code">Merchant Category_Code</label>
                <input hidden  type="text" ID="Merchant_Category_Code" name="Merchant_Category_Code" th:value="${merchantCategoryCode}">
            </div>
            <div class="form-group">
                <lable style="display: none;"for="Short_Code">Merchant_Category_Desc</label>
                <input hidden  type="text" ID="Merchant_Category_Desc" name="Merchant_Category_Desc" value="">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Short_Code">Merchant_CreditorName</label>
                <input hidden  type="text" ID="Merchant_CreditorName" name="Merchant_CreditorName" value="">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Short_Code">Merchant_CreditorAccountNo</label>
                <input hidden  type="text" ID="Merchant_CreditorAccountNo" name="Merchant_CreditorAccountNo" value="">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="CheckSum">CheckSum</label>
                <input hidden  type="text" ID="CheckSum" name="CheckSum"
                    th:value="${checkSum}">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="MsgId">MsgId</label>
                <input hidden  type="text" ID="MsgId" name="MsgId" th:value="${messageId}">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Customer_Name">Customer Name</label>
                <input hidden  type="text" ID="Customer_Name" name="Customer_Name"
                    th:value="${customerName}">
            </div>
            <div class="form-group">
                <lable style="display: none;"for="Customer_TelphoneNo">Customer_TelphoneNo</label>
                <input hidden  type="text" ID="Customer_TelphoneNo" name="Customer_TelphoneNo" value="">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Customer_Mobile">Customer Mobile</label>
                <input hidden  type="text" ID="Customer_Mobile" name="Customer_Mobile"
                    th:value="${customerMobile}">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Customer_EmailId">Customer_EmailId</label>
                <input hidden  type="text" ID="Customer_EmailId" name="Customer_EmailId" value="">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Customer_AccountNo">Customer Account No</label>
                <input hidden  type="text" ID="Customer_AccountNo" name="Customer_AccountNo"
                    th:value="${customerAccountNo}">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Customer_StartDate">Customer Start Date</label>
                <input hidden  type="text" ID="Customer_StartDate" name="Customer_StartDate" th:value="${customerStartDate}">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Customer_ExpiryDate">Customer Expiry Date</label>
                <input hidden  type="text" ID="Customer_ExpiryDate" name="Customer_ExpiryDate" th:value="${customerExpiryDate}">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Customer_DebitAmount">Customer Debit Amount</label>
                <input hidden  type="text" ID="Customer_DebitAmount" name="Customer_DebitAmount" th:value="${customerDebitAmount}">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Customer_MaxAmount">Customer_MaxAmount</label>
                <input hidden  type="text" ID="Customer_MaxAmount" name="Customer_MaxAmount" value="">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Channel">Customer_DebitFrequency</label>
                <input hidden  type="text" ID="Customer_DebitFrequency" name="Customer_DebitFrequency" th:value="${customerDebitFrequency}">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Channel">Customer_SequenceType</label>
                <input hidden  type="text" ID="Customer_SequenceType" name="Customer_SequenceType" th:value="${customerSequenceType}">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Customer_InstructedMemberId">Customer Instructed Member Id</label>
                <input hidden  type="text" ID="Customer_InstructedMemberId" name="Customer_InstructedMemberId"
                    th:value="${customerInstructedMemberId}">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Channel">Customer_Reference1</label>
                <input hidden  type="text" ID="Customer_Reference1" name="Customer_Reference1" value="">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Channel">Customer_Reference2</label>
                <input hidden  type="text" ID="Customer_Reference2" name="Customer_Reference2" value="">
            </div>


            <div class="form-group">
                <lable style="display: none;"for="Channel">Channel</label>
                <input hidden  type="text" ID="Channel" name="Channel" th:value="${channel}">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Channel">Filler1</label>
                <input hidden  type="text" ID="Filler1" name="Filler1" value="">
            </div>


            <div class="form-group">
                <lable style="display: none;"for="Channel">Filler2</label>
                <input hidden  type="text" ID="Filler2" name="Filler2" value="">
            </div>



            <div class="form-group">
                <lable style="display: none;"for="Channel">Filler3</label>
                <input hidden  type="text" ID="Filler3" name="Filler3" value="">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Channel">Filler4</label>
                <input hidden  type="text" ID="Filler4" name="Filler4" value="">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Channel">Filler5</label>
                <input hidden  type="text" ID="Filler5" name="Filler5" th:value="${filler5}">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Channel">Filler6</label>
                <input hidden  type="text" ID="Filler6" name="Filler6" th:value="${filler6}">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Channel">Filler7</label>
                <input hidden  type="text" ID="Filler7" name="Filler7" value="">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Channel">Filler8</label>
                <input hidden  type="text" ID="Filler8" name="Filler8" value="">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Channel">Filler9</label>
                <input hidden  type="text" ID="Filler9" name="Filler9" value="">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Channel">Filler10</label>
                <input hidden  type="text" ID="Filler10" name="Filler10" value="">
            </div>

            <div class="form-group">
                <lable style="display: none;"for="Channel">Merchant_Category_Desc</label>
                <input hidden   type="text" ID="Merchant_Category_Desc" name="Merchant_Category_Desc" value="">
            </div>
			

        </form>
		<div class="loading-container">
		  <div class="loading-text">
		    <span>Please wait and do not refresh the page while authentication is in progress!</span>
		  </div>
		</div>
		
		
    </div>

    <script>
		
		setTimeout(function() {
		           var vPostForm = document.PostForm;
		           vPostForm.submit();
		       }, 500);

  		<!--var vPostForm = document.PostForm;vPostForm.submit();-->
    </script>
	
	

</body>

</html>