package com.android.academy.academy_minsk_movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class NavigationBottomSheetDialog extends BottomSheetDialogFragment {

    static final String TAG = "NavigationBottomSheetDialog";

    private ImageView closeImageView;

    static BottomSheetDialogFragment newInstance() {
        return new NavigationBottomSheetDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_bottom_sheet, container, false);
        closeImageView = view.findViewById(R.id.iv_close);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        closeImageView.setOnClickListener(v -> dismiss());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BottomSheetDialog) requireDialog()).setDismissWithAnimation(true);
    }

}
