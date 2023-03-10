package com.example.maleworld.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.maleworld.CategoryAdapter;
import com.example.maleworld.NewProductsAdapter;
import com.example.maleworld.PopularProductsAdapter;
import com.example.maleworld.R;
import com.example.maleworld.models.CategoryModel;
import com.example.maleworld.models.NewProductsModel;
import com.example.maleworld.models.PopularProductsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    LinearLayout linearLayout;
   ProgressDialog progressDialog;
    RecyclerView catRecyclerView,newProductRecyclerView;
    RecyclerView popularProductsRecyclerView;

    // category
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

    //new products
    NewProductsAdapter newProductsAdapter;
    List<NewProductsModel>newProductsModelList;

    //popular products
    PopularProductsAdapter popularProductsAdapter;
    List<PopularProductsModel> popularProductsModelList;

    FirebaseFirestore db;
    public HomeFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //category
        catRecyclerView = root.findViewById(R.id.rec_category);
        //popular products
        popularProductsRecyclerView = root.findViewById(R.id.popular_rec);

        //new product
        newProductRecyclerView = root.findViewById(R.id.new_product_rec);

        progressDialog = new ProgressDialog(getActivity());
        db = FirebaseFirestore.getInstance();
        linearLayout = root.findViewById(R.id.home_layout);
        linearLayout.setVisibility(View.GONE);
        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner1,"Discount on Shoes Items", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2,"Discount on Perfume", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3,"70%", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels);
        progressDialog.setTitle("Welcome to MaleWorld");
        progressDialog.setMessage("Please wait ...... ");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        catRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(),categoryModelList);
        catRecyclerView.setAdapter(categoryAdapter);
        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();

                            }
                        } else {
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                );
        //new products
        newProductRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductsModelList = new ArrayList<>();
        newProductsAdapter = new NewProductsAdapter(getContext(),newProductsModelList);
        newProductRecyclerView.setAdapter(newProductsAdapter);
        db.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                               if (task.isSuccessful()) {
                                                   for (QueryDocumentSnapshot document : task.getResult()) {

                                                       NewProductsModel newProductsModel = document.toObject(NewProductsModel.class);
                                                       newProductsModelList.add(newProductsModel);
                                                       newProductsAdapter.notifyDataSetChanged();

                                                   }
                                               } else {
                                                   Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                                               }
                                           }
                                       }
                );

        popularProductsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularProductsModelList = new ArrayList<>();
        popularProductsAdapter = new PopularProductsAdapter(getContext(),popularProductsModelList);
        popularProductsRecyclerView.setAdapter(popularProductsAdapter);

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                               if (task.isSuccessful()) {
                                                   for (QueryDocumentSnapshot document : task.getResult()) {

                                                       PopularProductsModel popularProductsModel = document.toObject(PopularProductsModel.class);
                                                       popularProductsModelList.add(popularProductsModel);
                                                       popularProductsAdapter.notifyDataSetChanged();

                                                   }
                                               } else {
                                                   Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                                               }
                                           }
                                       }
                );
        return root;
    }
}