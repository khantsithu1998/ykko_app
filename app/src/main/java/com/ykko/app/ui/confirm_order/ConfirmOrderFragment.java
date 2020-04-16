package com.ykko.app.ui.confirm_order;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ykko.app.R;

public class ConfirmOrderFragment extends Fragment {

    private ConfirmOrderViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(ConfirmOrderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_confirm_order, container, false);
        Button confirmTableBtn = root.findViewById(R.id.confirm_table_btn);
        confirmTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_reserved);
            }
        });


        return root;
    }



}
