package com.ykko.app.ui.table;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.dev.materialspinner.MaterialSpinner;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ykko.app.R;
import com.ykko.app.data.model.FoodMenu;
import com.ykko.app.data.model.Order;
import com.ykko.app.ui.fragments.DatePickerFragment;
import com.ykko.app.ui.fragments.TimePickerFragment;

import java.util.ArrayList;
import java.util.List;

public class TableReseravationFragment extends Fragment {

    private TableReseravationViewModel tableReseravationViewModel;
    FirebaseDatabase database;
    private List<FoodMenu> foodMenuPosts = new ArrayList<>();
    private ArrayList foods = new ArrayList<>();
    private List<String> keys = new ArrayList<>();


    private Order newOrder = new Order();
    String branch = "";
    String date = "";
    String township = "";
    String foodOne = "";
    String foodTwo = "";


    MaterialSpinner foodOneSpinner;
    MaterialSpinner foodTwoSpinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tableReseravationViewModel =
                ViewModelProviders.of(this).get(TableReseravationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_table_reservation, container, false);
        final TextInputEditText nameEditText = root.findViewById(R.id.reserve_name);
        final TextInputEditText phNoEditText = root.findViewById(R.id.reserve_phNo);
        final TextInputEditText numberOfPersonsEditText = root.findViewById(R.id.noOfPersons);
        final TextInputEditText foodDesEditText = root.findViewById(R.id.food_des);
        foodOneSpinner = root.findViewById(R.id.food1_spinner);
        foodOneSpinner.setItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                foodOne = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        foodTwoSpinner = root.findViewById(R.id.food2_spinner);
        foodTwoSpinner.setItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                foodTwo = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        database = FirebaseDatabase.getInstance();


        DatabaseReference menuPostsRef = database.getReference("menuPosts").child("posts");

        ValueEventListener menuPostListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                keys.clear();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    FoodMenu post = keyNode.getValue(FoodMenu.class);
                    foodMenuPosts.add(post);
                }
                for(FoodMenu post: foodMenuPosts){
                    foods.add(post.foodStickName);
                }

                foodOneSpinner.setLabel("Food1");

                ArrayAdapter<String> foodOneAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,foods);
                // Specify the layout to use when the list of choices appears
                foodOneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                foodOneSpinner.setAdapter(foodOneAdapter);

                foodTwoSpinner.setLabel("Food2");
                ArrayAdapter<String> foodTwoAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,foods);
                // Specify the layout to use when the list of choices appears
                foodTwoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                foodTwoSpinner.setAdapter(foodTwoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("", "loadPost:onCancelled", databaseError.toException());
            }
        };
        menuPostsRef.addValueEventListener(menuPostListener);

        MaterialSpinner townshipSpinner = root.findViewById(R.id.township_spinner);
        townshipSpinner.setItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                township = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        townshipSpinner.setLabel("Township");
        ArrayAdapter<CharSequence> townshipAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.townships_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        townshipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        townshipSpinner.setAdapter(townshipAdapter);

        MaterialSpinner branchesSpinner = root.findViewById(R.id.branches_spinner);
        branchesSpinner.setItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branch = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        branchesSpinner.setLabel("Branch");
        ArrayAdapter<CharSequence> branchesAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.branches_array, android.R.layout.simple_spinner_item);
        branchesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchesSpinner.setAdapter(branchesAdapter);


        Button datePickerBtn = root.findViewById(R.id.datePickerBtn);
        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        Button timePickerBtn = root.findViewById(R.id.timePickerBtn);
        timePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });

        Button reserveTableBtn = root.findViewById(R.id.reserve_table_btn);
        reserveTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newOrder.name = nameEditText.getText().toString();
                newOrder.phNo = phNoEditText.getText().toString();
                newOrder.date = "20/Mar/2020 - 12:00 PM";
                newOrder.branch = branch;
                newOrder.township = township;
                newOrder.numberOfPersons = Integer.valueOf(numberOfPersonsEditText.getText().toString());
                newOrder.food1 = foodOne;
                newOrder.food2 = foodTwo;
                newOrder.description = foodDesEditText.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putParcelable("newOrderKey", newOrder);
                Navigation.findNavController(v).navigate(R.id.nav_confirm_order,bundle);
            }
        });

        return root;
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"datePicker");
//        DatePickerDialog dialog = (DatePickerDialog) newFragment.getDialog();
//        DatePicker picker = dialog.getDatePicker();
//        date = picker.getDayOfMonth()+"/"+ (picker.getMonth() + 1)+"/"+picker.getYear();
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(),"timePicker");

    }






}
