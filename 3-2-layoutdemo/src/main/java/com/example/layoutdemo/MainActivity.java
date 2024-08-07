package com.example.layoutdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import com.example.layoutdemo.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // Setting for Fragments
        Fragment linear_layout = LayoutDemoFragment.newInstance(LayoutDemoFragment.LINEAR_DEMO);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_fragment, linear_layout)
                .addToBackStack(null)
                .commit();

        // Setting for Navigation Bar
        binding.navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item){

                int id = item.getItemId();

                // To show Linear layout demonstration
                if (id == R.id.navigation_linear) {
                    Fragment linear_layout = LayoutDemoFragment.newInstance(LayoutDemoFragment.LINEAR_DEMO);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.layout_fragment, linear_layout)
                            .addToBackStack(null)
                            .commit();
                    return true;
                }
                // To show Relative layout demonstration
                else if (id == R.id.navigation_relative) {
                    Fragment relative_layout = LayoutDemoFragment.newInstance(LayoutDemoFragment.RELATIVE_DEMO);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.layout_fragment, relative_layout)
                            .addToBackStack(null)
                            .commit();
                    return true;
                }
                // To show List view demonstration
                else if (id == R.id.navigation_list) {
                    Fragment list_layout = LayoutDemoFragment.newInstance(LayoutDemoFragment.LIST_DEMO);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.layout_fragment, list_layout)
                            .addToBackStack(null)
                            .commit();
                    return true;
                }
                // To show Recycler demonstration
                else if (id == R.id.navigation_recycler) {
                    Fragment recycler_layout = LayoutDemoFragment.newInstance(LayoutDemoFragment.RECYCLER_DEMO);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.layout_fragment, recycler_layout)
                            .addToBackStack(null)
                            .commit();
                    return true;
                }
                // Handle the "More" menu item
                else if (id == R.id.navigation_more) {
                    // Display additional options in a popup menu
                    // This approach allows us to extend beyond the 5-icon limit of the bottom navigation bar
                    showMoreOptions();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Displays a popup menu with additional layout options.
     *
     * This method creates a PopupMenu anchored to the 'more' navigation item.
     * It inflates the menu from the 'menu_more_options' resource.
     * The method returns true if the menu item is handled, false otherwise.
     */
    private void showMoreOptions() {
        PopupMenu popup = new PopupMenu(this, findViewById(R.id.navigation_more));
        popup.getMenuInflater().inflate(R.menu.menu_more_options, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_grid) {
                Fragment grid_layout = LayoutDemoFragment.newInstance(LayoutDemoFragment.GRID_DEMO);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_fragment, grid_layout)
                        .addToBackStack(null)
                        .commit();
                return true;
            } else if (id == R.id.navigation_constraint) {
                Fragment constraint_layout = LayoutDemoFragment.newInstance(LayoutDemoFragment.CONSTRAINT_DEMO);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_fragment, constraint_layout)
                        .addToBackStack(null)
                        .commit();
                return true;
            }
            // Handle other options
            return false;
        });
        popup.show();
    }


}
