package com.example.desino_app;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import model.Product;

public class ProductLastestAdapter extends RecyclerView.Adapter<ProductLastestAdapter.ViewHolder> {

    private ArrayList<Product> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNombre;
        private TextView txtDescripcion;
        private TextView txtPrecio;

        private ImageView ivImagen;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            txtNombre = (TextView) view.findViewById(R.id.txtNombre);
            txtDescripcion = (TextView) view.findViewById(R.id.txtDescripcion);
            txtPrecio = (TextView) view.findViewById(R.id.txtPrecio);
            ivImagen = (ImageView) view.findViewById(R.id.ivImagen);
        }

    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public ProductLastestAdapter(ArrayList<Product> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_product_lastest, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.txtNombre.setText(localDataSet.get(position).getName());
        viewHolder.txtDescripcion.setText(localDataSet.get(position).getDescription());
        viewHolder.txtPrecio.setText(localDataSet.get(position).getCurrency()+localDataSet.get(position).getPrice());
        Glide.with(viewHolder.ivImagen.getContext()).load(localDataSet.get(position).getPhoto()).into(viewHolder.ivImagen);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
