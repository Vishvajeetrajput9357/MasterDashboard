<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Emandate Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="hidden"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            width: 100%;
            background-color: #28a745;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        .hidden-inputs {
            display: none;
        }
    </style>
</head>

<body>

    <div class="container">
        <h2>Emandate Form</h2>
        <form id="PostForm" name="PostForm"  action="https://emandateut.hdfcbank.com/Emandate.aspx" method="POST">
            <div class="form-group">
                <label for="UtilCode">UtilCode</label>
                <input type="text" ID="UtilCode" name="UtilCode"
                    value="\xf1f00613b15782a7acd5e9530c42cc753850a659882b3dc9668c48ddb4e4c531">
            </div>

            <div class="form-group">
                <label for="Short_Code">Short Code</label>
                <input type="text" ID="Short_Code" name="Short_Code" value="\x255f1883ff812a8603d39695f1cd9592">
            </div>
            
            <div class="form-group">
                
                <label for="Short_Code">Merchant_PartyName</label>
                <input type="text" ID="Merchant_PartyName" name="Merchant_PartyName" value="">
            </div>

            <div class="form-group">
                <label for="Short_Code">Merchant Category_Code</label>
                <input type="text" ID="Merchant_Category_Code" name="Merchant_Category_Code" value="U099">
            </div>
            <div class="form-group">
                <label for="Short_Code">Merchant_Category_Desc</label>
                <input type="text" ID="Merchant_Category_Desc" name="Merchant_Category_Desc" value="">
            </div>

            <div class="form-group">
                <label for="Short_Code">Merchant_CreditorName</label>
                <input type="text" ID="Merchant_CreditorName" name="Merchant_CreditorName" value="">
            </div>

            <div class="form-group">
                <label for="Short_Code">Merchant_CreditorAccountNo</label>
                <input type="text" ID="Merchant_CreditorAccountNo" name="Merchant_CreditorAccountNo" value="">
            </div>

            <div class="form-group">
                <label for="CheckSum">CheckSum</label>
                <input type="text" ID="CheckSum" name="CheckSum"
                    value="f9e0723663e3eb3b0d9f47d4ed41ce44ebb397fc70ea30e222c771eb131707a3">
            </div>

            <div class="form-group">
                <label for="MsgId">MsgId</label>
                <input type="text" ID="MsgId" name="MsgId" value="KJ2gh72c1bn">
            </div>

            <div class="form-group">
                <label for="Customer_Name">Customer Name</label>
                <input type="text" ID="Customer_Name" name="Customer_Name"
                    value="\xbe8e925d737b597e01335f2ca61f442e077aee20f1df4454e61dfa53b1484632">
            </div>
            <div class="form-group">
                <label for="Customer_TelphoneNo">Customer_TelphoneNo</label>
                <input type="text" ID="Customer_TelphoneNo" name="Customer_TelphoneNo" value="">
            </div>

            <div class="form-group">
                <label for="Customer_Mobile">Customer Mobile</label>
                <input type="text" ID="Customer_Mobile" name="Customer_Mobile"
                    value="\x5f493e434bcef0d333d9476079525641">
            </div>

            <div class="form-group">
                <label for="Customer_EmailId">Customer_EmailId</label>
                <input type="text" ID="Customer_EmailId" name="Customer_EmailId" value="">
            </div>

            <div class="form-group">
                <label for="Customer_AccountNo">Customer Account No</label>
                <input type="text" ID="Customer_AccountNo" name="Customer_AccountNo"
                    value="\x4ba067c9b15f6edaf92d5e8bf0a72364">
            </div>

            <div class="form-group">
                <label for="Customer_StartDate">Customer Start Date</label>
                <input type="text" ID="Customer_StartDate" name="Customer_StartDate" value="2024-10-31">
            </div>

            <div class="form-group">
                <label for="Customer_ExpiryDate">Customer Expiry Date</label>
                <input type="text" ID="Customer_ExpiryDate" name="Customer_ExpiryDate" value="2025-10-31">
            </div>

            <div class="form-group">
                <label for="Customer_DebitAmount">Customer Debit Amount</label>
                <input type="text" ID="Customer_DebitAmount" name="Customer_DebitAmount" value="100.00">
            </div>


            <div class="form-group">
                <label for="Customer_MaxAmount">Customer_MaxAmount</label>
                <input type="text" ID="Customer_MaxAmount" name="Customer_MaxAmount" value="">
            </div>

            <div class="form-group">
                <label for="Channel">Customer_DebitFrequency</label>
                <input type="text" ID="Customer_DebitFrequency" name="Customer_DebitFrequency" value="MNTH">
            </div>

            <div class="form-group">
                <label for="Channel">Customer_SequenceType</label>
                <input type="text" ID="Customer_SequenceType" name="Customer_SequenceType" value="RCUR">
            </div>

            <div class="form-group">
                <label for="Customer_InstructedMemberId">Customer Instructed Member Id</label>
                <input type="text" ID="Customer_InstructedMemberId" name="Customer_InstructedMemberId"
                    value="HDFC0003354">
            </div>

            <div class="form-group">
                <label for="Channel">Customer_Reference1</label>
                <input type="text" ID="Customer_Reference1" name="Customer_Reference1" value="">
            </div>

            <div class="form-group">
                <label for="Channel">Customer_Reference2</label>
                <input type="text" ID="Customer_Reference2" name="Customer_Reference2" value="">
            </div>


            <div class="form-group">
                <label for="Channel">Channel</label>
                <input type="text" ID="Channel" name="Channel" value="NET">
            </div>

            <div class="form-group">
                <label for="Channel">Filler1</label>
                <input type="text" ID="Filler1" name="Filler1" value="">
            </div>


            <div class="form-group">
                <label for="Channel">Filler2</label>
                <input type="text" ID="Filler2" name="Filler2" value="">
            </div>



            <div class="form-group">
                <label for="Channel">Filler3</label>
                <input type="text" ID="Filler3" name="Filler3" value="">
            </div>

            <div class="form-group">
                <label for="Channel">Filler4</label>
                <input type="text" ID="Filler4" name="Filler4" value="">
            </div>

            <div class="form-group">
                <label for="Channel">Filler5</label>
                <input type="text" ID="Filler5" name="Filler5" value="S">
            </div>

            <div class="form-group">
                <label for="Channel">Filler6</label>
                <input type="text" ID="Filler6" name="Filler6" value="HDFC">
            </div>

            <div class="form-group">
                <label for="Channel">Filler7</label>
                <input type="text" ID="Filler7" name="Filler7" value="">
            </div>

            <div class="form-group">
                <label for="Channel">Filler8</label>
                <input type="text" ID="Filler8" name="Filler8" value="">
            </div>

            <div class="form-group">
                <label for="Channel">Filler9</label>
                <input type="text" ID="Filler9" name="Filler9" value="">
            </div>

            <div class="form-group">
                <label for="Channel">Filler10</label>
                <input type="text" ID="Filler10" name="Filler10" value="">
            </div>





            <div class="form-group">
                <label for="Channel">Merchant_Category_Desc</label>
                <input type="text" ID="Merchant_Category_Desc" name="Merchant_Category_Desc" value="">
            </div>

        </form>
    </div>

    <script>

    var vPostForm = document.PostForm;vPostForm.submit();
    </script>

</body>

</html>