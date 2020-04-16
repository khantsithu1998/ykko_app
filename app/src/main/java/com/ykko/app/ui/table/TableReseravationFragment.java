package com.ykko.app.ui.table;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.ykko.app.R;
import com.ykko.app.ui.gallery.GalleryViewModel;

public class TableReseravationFragment extends Fragment {

    private TableReseravationViewModel tableReseravationViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tableReseravationViewModel =
                ViewModelProviders.of(this).get(TableReseravationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_table_reservation, container, false);
        Button reserveTableBtn = root.findViewById(R.id.reserve_table_btn);
        reserveTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_confirm_order);
            }
        });
        Spinner townshipSpinner = root.findViewById(R.id.township_spinner);

        ArrayAdapter<CharSequence> townshipAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.townships_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        townshipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        townshipSpinner.setAdapter(townshipAdapter);

        Spinner branchesSpinner = root.findViewById(R.id.branches_spinner);

        ArrayAdapter<CharSequence> branchesAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.branches_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        townshipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        branchesSpinner.setAdapter(branchesAdapter);

        return root;
    }

}
