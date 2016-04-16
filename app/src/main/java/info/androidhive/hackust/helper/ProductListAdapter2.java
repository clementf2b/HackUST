package info.androidhive.hackust.helper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import info.androidhive.hackust.R;
import info.androidhive.hackust.app.AppController;

public class ProductListAdapter2 extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Product> products;
	private ProductListAdapterListener listener;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public ProductListAdapter2(Activity activity, List<Product> feedItems,
							   ProductListAdapterListener listener) {
		this.activity = activity;
		this.products = feedItems;
		this.listener = listener;
	}

	@Override
	public int getCount() {
		return products.size();
	}

	@Override
	public Object getItem(int location) {
		return products.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_item_product, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();

		TextView name = (TextView) convertView.findViewById(R.id.pName);
		TextView description = (TextView) convertView
				.findViewById(R.id.pDescription);
		TextView price = (TextView) convertView.findViewById(R.id.pPrice);
		ImageLoader imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView image = (NetworkImageView) convertView
				.findViewById(R.id.pImage);

		final Product product = products.get(position);

		name.setText(product.getName());

		description.setText(product.getDescription());

		price.setText("Price: $" + product.getPrice());

		// user profile pic
		image.setImageUrl(product.getImage(), imageLoader);

		return convertView;
	}

	public interface ProductListAdapterListener {
		public void onAddToCartPressed(Product product);
	}

}