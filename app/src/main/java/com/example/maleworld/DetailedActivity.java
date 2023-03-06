package com.example.maleworld;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maleworld.models.NewProductsModel;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailedActivity extends AppCompatActivity {
    ImageView detailedImg;
    TextView rating,name,description,price;
    Button addToCart,buyNow;
    ImageView addItems,removedItems;

    private FirebaseFirestore firebaseFirestore;
    NewProductsModel newProductsModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        final Object obj = getIntent().getSerializableExtra("detailed");

        if(obj instanceof NewProductsModel){
              newProductsModel = (NewProductsModel) obj;
        }
        setContentView(R.layout.activity_detailed);
        detailedImg = findViewById(R.id.detailed_img);
        name= findViewById(R.id.detailed_name);
        rating = findViewById(R.id.rating);
        price = findViewById(R.id.detailed_price);
        addToCart = findViewById(R.id.add_to_cart);
        buyNow = findViewById(R.id.buy_now);
        addItems = findViewById(R.id.add_item);
        description = findViewById(R.id.detailed_desc);
        removedItems = findViewById(R.id.remove_item);
        if(newProductsModel!=null){
            Glide.with(getApplicationContext()).load(newProductsModel.getImg_url()).into(detailedImg);
            name.setText(newProductsModel.getName());
            rating.setText(newProductsModel.getRating());
            price.setText(newProductsModel.getPrice());
            description.setText(newProductsModel.getDescription());
        }

    }
}