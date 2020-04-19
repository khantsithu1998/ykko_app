package com.ykko.app.ui.admin_reservations_details;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ykko.app.R;
import com.ykko.app.data.model.Order;
import com.ykko.app.ui.FirebaseDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AdminReservationDetailsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_admin_reservation_details, container, false);

        Order order = getArguments().getParcelable("orderDetailKey");
        final String key = getArguments().getString("orderDetailKeyKey");

        TextView confirmNameTextView = root.findViewById(R.id.admin_confirm_name);
        TextView confirmPhNoTextView = root.findViewById(R.id.admin_confirm_phNo);
        TextView confirmTownshipTextView = root.findViewById(R.id.admin_confirm_township);
        TextView confirmBranchTextView = root.findViewById(R.id.admin_confirm_branch);
        TextView confirmDateTextView = root.findViewById(R.id.admin_confirm_date);
        TextView confirmNoOfPersonsTextView = root.findViewById(R.id.admin_confirm_noOfPerson);
        TextView confirmFoodOneTextView = root.findViewById(R.id.admin_confirm_food1);
        TextView confirmFoodTwoTextView = root.findViewById(R.id.admin_confirm_food2);
        TextView confirmDesTextView = root.findViewById(R.id.admin_confirm_des);
        Button deleteBtn = root.findViewById(R.id.admin_delete_res_btn);

        confirmNameTextView.setText(order.name);
        confirmPhNoTextView.setText(order.phNo);
        confirmTownshipTextView.setText(order.township);
        confirmBranchTextView.setText(order.branch);
        confirmDateTextView.setText(order.date);
        confirmNoOfPersonsTextView.setText(String.valueOf(order.numberOfPersons));
        confirmFoodOneTextView.setText(order.food1);
        confirmFoodTwoTextView.setText(order.food2);
        confirmDesTextView.setText(order.description);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabaseHelper databaseHelper = new FirebaseDatabaseHelper();
                databaseHelper.deleteData("orderPosts", key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(getActivity(),"Delete Reservation",Toast.LENGTH_SHORT).show();
                        getFragmentManager().popBackStack();
                    }
                });

            }
        });
        return root;
    }
}
