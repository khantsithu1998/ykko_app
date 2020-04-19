package com.ykko.app.ui.admin_add_menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ykko.app.R;
import com.ykko.app.data.model.FoodMenu;
import com.ykko.app.ui.FirebaseDatabaseHelper;

public class AddMenuFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_menu, container, false);
        final TextInputEditText foodNameEditText = root.findViewById(R.id.add_food_menu_name);
        final TextInputEditText foodTypeEditText = root.findViewById(R.id.add_food_menu_type);
        final TextInputEditText foodPriceEditText = root.findViewById(R.id.add_food_menu_price);
        TextInputEditText foodAvailableEditText = root.findViewById(R.id.add_food_menu_available);
        Button saveBtn = root.findViewById(R.id.save_menu_button);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = foodNameEditText.getText().toString();
                String type = foodTypeEditText.getText().toString();
                String price = foodPriceEditText.getText().toString();
                FoodMenu newMenu = new FoodMenu(name,type,price);

                FirebaseDatabaseHelper databaseHelper = new FirebaseDatabaseHelper();
                databaseHelper.addData("menuPosts", newMenu, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(getActivity(),"Save Menu Successful",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        return root;
    }
}
