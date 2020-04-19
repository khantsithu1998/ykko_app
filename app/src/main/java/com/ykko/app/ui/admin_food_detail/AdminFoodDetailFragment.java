package com.ykko.app.ui.admin_food_detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ykko.app.R;
import com.ykko.app.data.model.FoodMenu;

import org.w3c.dom.Text;

public class AdminFoodDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_admin_food_detail, container, false);
        FoodMenu foodMenu = getArguments().getParcelable("menuDetailKey");
        TextView name = root.findViewById(R.id.food_detail_name);
        TextView type = root.findViewById(R.id.food_detail_type);
        TextView price = root.findViewById(R.id.food_detail_price);

        name.setText(foodMenu.foodStickName);
        type.setText(foodMenu.foodStickType);
        price.setText(foodMenu.price);

        return root;
    }
}
