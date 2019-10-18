package com.android.academy.academy_minsk_movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class ThreadsBottomSheetDialog extends BottomSheetDialogFragment {

    static final String TAG = "ThreadsBottomSheetDialog";

    private ImageView closeImageView;
    private ImageView indicatorImageView;
    private RadioGroup toolsRadioGroup;
    private SwitchMaterial indicatorScaleSwitch;
    private MaterialButton startButton;

    static BottomSheetDialogFragment newInstance() {
        return new ThreadsBottomSheetDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_threads_bottom_sheet, container, false);

        closeImageView = view.findViewById(R.id.threads_iv_close);
        indicatorImageView = view.findViewById(R.id.threads_iv_indicator);
        toolsRadioGroup = view.findViewById(R.id.threads_rg_thread_tools);
        indicatorScaleSwitch = view.findViewById(R.id.threads_switch_indicator_scale);
        startButton = view.findViewById(R.id.threads_btn_start);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toggleScaleIndicator(indicatorScaleSwitch.isChecked());
        setListeners();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BottomSheetDialog) requireDialog()).setDismissWithAnimation(true);
    }

    private void setListeners() {
        closeImageView.setOnClickListener(v -> dismiss());

        toolsRadioGroup.setOnCheckedChangeListener((group, checkedId) -> toggleStartButton());

        indicatorScaleSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> toggleScaleIndicator(isChecked));

        startButton.setOnClickListener(v -> startCalculating());
    }

    private void startCalculating() {
        switch (toolsRadioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_ui_thread:
                Toast.makeText(getContext(), "UI Thread", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_handler_thread:
                Toast.makeText(getContext(), "Handler", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_async_task:
                Toast.makeText(getContext(), "Async Task", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_coroutines:
                Toast.makeText(getContext(), "Coroutines", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getContext(), getString(R.string.threads_choose_tool), Toast.LENGTH_SHORT).show();
        }
    }

    private void toggleScaleIndicator(boolean isChecked) {
        if (isChecked) {
            indicatorImageView.startAnimation(AnimationUtils.loadAnimation(this.getContext(), R.anim.animation_infinity_scale));
        } else {
            indicatorImageView.clearAnimation();
        }
    }

    private void toggleStartButton() {
        startButton.setEnabled(true);
    }

}
