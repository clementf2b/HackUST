package info.androidhive.hackust;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalItem;
import com.paypal.android.sdk.payments.PayPalService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.hackust.app.AppController;
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
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);
		btnCheckout = (Button) findViewById(R.id.btnCheckout);

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

		showpDialog();

		// Making json object request
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
				Config.URL_WATER, null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());

						try {
							JSONArray products = response
									.getJSONArray("water");

							// looping through all product nodes and storing
							// them in array list
							for (int i = 0; i < products.length(); i++) {

								JSONObject product = (JSONObject) products
										.get(i);

								String id = product.getString("id");
								String name = product.getString("name");
								String description = product
										.getString("description");
								String image = product.getString("image");
								BigDecimal price = new BigDecimal(product
										.getString("price"));
								String sku = product.getString("sku");

								Product p = new Product(id, name, description,
										image, price, sku);

								productsList.add(p);
							}



							// notifying adapter about data changes, so that the
							// list renders with new data
							adapter.notifyDataSetChanged();

						} catch (JSONException e) {
							e.printStackTrace();
							Toast.makeText(getApplicationContext(),
									"Error: " + e.getMessage(),
									Toast.LENGTH_LONG).show();
						}

						// hiding the progress dialog
						hidepDialog();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						Toast.makeText(getApplicationContext(),
								error.getMessage(), Toast.LENGTH_SHORT).show();
						// hide the progress dialog
						hidepDialog();
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq);
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
