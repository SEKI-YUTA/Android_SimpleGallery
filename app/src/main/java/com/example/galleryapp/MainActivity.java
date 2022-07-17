package com.example.galleryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.galleryapp.Fragments.ImageListFragment;
import com.example.galleryapp.Fragments.AudioListFragment;
import com.example.galleryapp.Fragments.VideoListFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private final int PERMISSION_REQCODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navItem_about:
                        Toast.makeText(MainActivity.this, "About item", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.navItem_images:
                        Toast.makeText(MainActivity.this, "Images", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new ImageListFragment())
                                .commit();
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.navItem_musics:
                        Toast.makeText(MainActivity.this, "Musics", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new AudioListFragment())
                                .commit();
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.navItem_videos:
                        Toast.makeText(MainActivity.this, "Videos", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new VideoListFragment())
                                .commit();
                        drawerLayout.closeDrawers();
                        return true;

                    default:
                        Toast.makeText(MainActivity.this, "no matched", Toast.LENGTH_SHORT).show();
                        return false;
                }
            }
        });
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE
                + Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQCODE);
        } else {

        }

    }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // パーミッションが許可された時の処理
        if(requestCode == PERMISSION_REQCODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new ImageListFragment())
                    .commit();
        }
    }

//    private final View.OnLongClickListener listener = new View.OnLongClickListener() {
//        @Override
//        public boolean onLongClick(View view) {
//            MyDialogFragment dialog = new MyDialogFragment();
//            dialog.show(getSupportFragmentManager(), "dialog");
//            return false;
//        }
//    };
//
//    public View.OnLongClickListener getListener() {
//        MyDialogFragment dialog = new MyDialogFragment();
//        dialog.show(getSupportFragmentManager(), "aaa");
//        return listener;
//    }
}