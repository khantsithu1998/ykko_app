package com.ykko.app.ui.admin_food_detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ykko.app.R;
import com.ykko.app.data.model.FoodMenu;
import com.ykko.app.ui.FirebaseDatabaseHelper;

import org.w3c.dom.Text;

public class AdminFoodDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_admin_food_detail, container, false);
        FoodMenu foodMenu = getArguments().getParcelable("menuDetailKey");
        final String key = getArguments().getString("menuDetailKeyKey");
        TextView name = root.findViewById(R.id.food_detail_name);
        TextView type = root.findViewById(R.id.food_detail_type);
        TextView price = root.findViewById(R.id.food_detail_price);
        Button deleteBtn = root.findViewById(R.id.delete_detail_menu_button);

        name.setText(foodMenu.foodStickName);
        type.setText(foodMenu.foodStickType);
        price.setText(foodMenu.price);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabaseHelper databaseHelper = new FirebaseDatabaseHelper();
                databaseHelper.deleteData("menuPosts", key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(getActivity(),"Delete Food Menu",Toast.LENGTH_SHORT).show();
                        getFragmentManager().popBackStack();
                    }
                });
            }
        });
        return root;
    }
}
