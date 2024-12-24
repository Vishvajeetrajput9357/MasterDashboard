package com.Master_Dashboard.request;

//import org.springframework.core.env.Environment;

public class ResponseMessage {

//	public final String BASE_URL;
//	public final String BASE_URL_UPI;
//	public final String BASE_URL_For_phonPe_status;
//	public final String BASE_URL_dev;
//	public final String PROFILE;

//	public ResponseMessage(Environment env) {
//		this.BASE_URL = env.getProperty("app.BASE_URL");
//		this.BASE_URL_UPI = env.getProperty("app.BASE_URL_UPI");
//		this.BASE_URL_For_phonPe_status = env.getProperty("app.BASE_URL_phonPe_status");
//		this.BASE_URL_dev = env.getProperty("app.BASE_URL_dev");
//		this.PROFILE = env.getProperty("app.PROFILE");
//	}


	public static final   String SUCCESS = "0x0200";
	public static final   String UNAUTHORISED = "0x0201";
	public static final   String FAILED = "0x0202";
	public static final   String MISSING_PARAMETER = "0x0203";
	public static final   String CONNECTION_TIMEOUT = "0x0204";
	public static final   String SOMETHING_WENT_WRONG = "0x0205";
	public static final   String BAD_REQUEST = "0x0202";
	public static final   String PENDING = "0x0206";

	// Response Key
	public static final   String CODE = "code";
	public static final   String STATUS = "status";
	public static final   String DESCRIPTION = "description";
	public static final   String FIELD = "field";

	// Response Field
	public static final   String FIELD_I = "Client-Id, Client-Secret";

	public static final   String STATUS_PENDING = "Pending";
	public static final   String STATUS_SUCCESS = "Success";
	public static final   String STATUS_FAIL = "Failed";
	public static final   String STATUS_REFUND = "Refunded";
	public static final   String STATUS_REFUND_INITIATED = "RefundInitiated";

	public static final   String API_STATUS_SUCCESS = "Success";
	public static final   String API_STATUS_PENDING = "Pending";
	public static final   String API_STATUS_FAILED = "Failed";
	public static final   String PENDING_FOR_KYC = "0x0209";
	public static final   String PENDING_FOR_SELF_APPROVAL = "0x0210";
	public static final   String API_STATUS_REFUND = "REFUND";
	public static final   String MANDATE_URL_SUCCESS = "Mandate request has been intiated successfully.";

	public static final   String UNAUTHORISED_DESCRIPTION = "Unauthorized Access to the Platform.";
	public static final   String MERCHANT_KYC = "Merchant KYC not complete.";
	public static final   String PENDING_FOR_SELF_APPROVAL_DESCRIPTION = "Please contact to support team. Pending for self approval.";
	public static final   String DEBIT_AMOUNT_NOT_AVAILABLE = "Debit Amount not available";
	public static final   String MERCHANTTRXNREFID_ALREADY_EXIST = "merchantTrxnRefId already exist please try unique id";
	public static final   String MERCHANT_ALREADY_EXIST = "Merchant is alreay exist.";
	public static final   String MERCHANT_SUCCESS_REGISTERED = "Registration completed successfully.";
	public static final   String REF_ID_ALREADY_EXIST = "refId already exist please try unique id";
	public static final   String MISSING_PARAMETER_DESCRIPTION = "Some Parameter are missing ";
	public static final   String CONNECTION_TIMEOUT_DESCRIPTION = "Connection Timeout";
	public static final   String SOMETHING_WENT_WRONG_DESCRIPTION = "Something Went Wrong";
	public static final   String SERVICE_NOT_AVILABLE = "Service Not Available";
	public static final   String ONE_LAKH_LIMIT = "UPI ID/VPA OR Merchant TrxnRefId are missing OR Amount limit Exceed 1 lac limit";
	public static final   String TWO_LAKH_LIMIT = "UPI ID/VPA OR Merchant TrxnRefId are missing OR Amount limit Exceed 2 lac limit";
	public static final   String VPA_MISSING = "VPA are missing ";
	public static final   String TRANSACTION_REFUND = "Transaction Already Refunded";
	public static final   String TRANSACTION_ID_NOT_VALID = "trxn_id  is not valid";
	public   static final Object TRXNID_ALREADY_EXSIST = "merchantTrxnRefId already exist";
	public static final   String EKYC_EMAIL = "mobile and email already exist";
	public static final   String TRANSACTION_REFUNDED = "Transaction Refund Successfully";
	public   static final String MERCHANTTRXNREFID_NOT_EXIST = "merchantTrxnRefId not exist";
	public static final   String TRANSACTION_NOT_FOUND = "Transaction Not Found";
	public static final   String SERVICEID_NOT_EXIST = "To subscribe for this service, get in touch with the support team.";
	public static final   String INVALID_JSON_FORMATE = "Invalid Request";
	public static final   String HEADERS_MISSING = "Missing Required Headers";
	public static final   String HEADERS_PARAM = "Missing Required Query Params";
	public   static final String INVALID_VPA = "Invalid UPI ID";
	public   static final String DATA = "Data";
	public   static final String INVALID_EMAIL_OR_PASSWORD = "Invalid user email or password";
	public   static final String ENACH_TRANSACTION_LIST = "ENach Transaction List";

	public   static final String PAYMENT_CANNOT = "Payment cannot be accepted at this time. Please try again later.";
	public static final String MERCHANT_TRXN_ID_ = "merchantTrxnRefId";

}