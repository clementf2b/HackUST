package info.androidhive.hackust;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalItem;
import com.paypal.android.sdk.payments.PayPalService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.hackust.app.Config;
import info.androidhive.hackust.helper.Product;
import info.androidhive.hackust.helper.ProductListAdapter;
import info.androidhive.hackust.helper.ProductListAdapter.ProductListAdapterListener;

public class Main extends Activity implements ProductListAdapterListener {
	private static final String TAG = Main.class.getSimpleName();

	private ListView listView;
	private Button btnCheckout;
	public static final String URL_list = "http://10.89.133.147/PayPalServer/v1/list.php";

	// To store all the products
	private List<Product> productsList;

	// To store the products those are added to cart
	private List<PayPalItem> productsInCart = new ArrayList<PayPalItem>();

	private ProductListAdapter adapter;

	// Progress dialog
	private ProgressDialog pDialog;

	private static final int REQUEST_CODE_PAYMENT = 1;

	// PayPal configuration
	private static PayPalConfiguration paypalConfig = new PayPalConfiguration()
			.environment(Config.PAYPAL_ENVIRONMENT).clientId(
					Config.PAYPAL_CLIENT_ID);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		listView = (ListView) findViewById(R.id.billlist);
		btnCheckout = (Button) findViewById(R.id.btnback);

		productsList = new ArrayList<Product>();
		adapter = new ProductListAdapter(this, productsList, this);

		listView.setAdapter(adapter);

		pDialog = new ProgressDialog(this);
		pDialog.setCancelable(false);

		// Starting PayPal service
		Intent intent = new Intent(this, PayPalService.class);
		intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);
		startService(intent);

		// Checkout button click listener
		btnCheckout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {



			}
		});

		// Fetching products from server
		fetchProducts();
	}

	/**
	 * Fetching the products from our server
	 * */
	private void fetchProducts() {
		// Showing progress dialog before making request

		pDialog.setMessage("Fetching products...");


								String id = "5";
								String name = "Water";
								String description = "May";
								String image = "http://10.89.133.147/PayPalServer/water.png\n";
								BigDecimal price = new BigDecimal("261");
								String sku = "sku-8493948kk4";

								Product p = new Product(id, name, description,
										image, price, sku);

								productsList.add(p);

	}


	private void showpDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hidepDialog() {
		if (pDialog.isShowing())
			pDialog.dismiss();
	}

	public void onAddToCartPressed(Product product)
	{

	}
}
