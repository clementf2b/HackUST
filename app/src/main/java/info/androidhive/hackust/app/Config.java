package info.androidhive.hackust.app;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;

public class Config {

	// PayPal app configuration
	public static final String PAYPAL_CLIENT_ID = "AcsY-j69pqseFQDc3ZhixNIdBv7WJAnanBqoyloCUN24SAuDUmdFREK2hxWvJ8BRlPtOZwgJ86BHqDUn";
	public static final String PAYPAL_CLIENT_SECRET = "EA2F7fjGlO6q4zLbGm3IYTB_Tbv7nReTquPVcNfxi9w3Cos6966qvutAhRJwCYvns4UjBhp7WENKKZMH";

	public static final String PAYPAL_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;
	public static final String PAYMENT_INTENT = PayPalPayment.PAYMENT_INTENT_SALE;
	public static final String DEFAULT_CURRENCY = "HKD";

	// Our php+mysql server urls
	public static final String URL_PRODUCTS = "http://10.89.133.147/PayPalServer/v1/products";
	public static final String URL_WATER = "http://10.89.133.147/PayPalServer/v1/water";
	public static final String URL_ELETRIC = "http://10.89.133.147/PayPalServer/v1/eletric";
	public static final String URL_PHONE = "http://10.89.133.147/PayPalServer/v1/phone";
	public static final String URL_VERIFY_PAYMENT = "http://10.89.133.147/PayPalServer/v1/verifyPayment";

}
