package com.android.academy.academy_minsk_movie.ui.gallery_movies_details;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.academy.academy_minsk_movie.R;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class GalleryDetailsFragment extends Fragment {

    private static final String ITEM_POSITION = "position";

    private ViewPager viewPager;

    public static Fragment newInstance(int position) {
        // Возвращаем экземпляр фрагмента GalleryDetailsFragment с переданными ему аргументами

        Fragment fragment = new GalleryDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ITEM_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.app_bar_menu_secondary, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_navigate_before:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                Log.wtf("Click before", "");
                return true;
            case R.id.app_bar_navigate_next:
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                return true;
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_details, container, false);
        viewPager = view.findViewById(R.id.view_pager_details);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = 0;

        // Проверяем, есть ли аргументы, переданные фрагменту.
        Bundle args = getArguments();
        if (args != null) {
            position = args.getInt(ITEM_POSITION);
        }

        setupViewPager(position);

    }

    private void setupViewPager(int position) {
        GalleryDetailsAdapter adapter = new GalleryDetailsAdapter(getChildFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        viewPager.setPageTransformer(true, new GalleryDetailsAdapterAnimation());
    }

}
