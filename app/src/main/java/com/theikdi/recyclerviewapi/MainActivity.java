package com.theikdi.recyclerviewapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.theikdi.recyclerviewapi.hotel.HotelAdapter;
import com.theikdi.recyclerviewapi.hotel.HotelApiClient;
import com.theikdi.recyclerviewapi.hotel.HotelApiInterface;
import com.theikdi.recyclerviewapi.hotel.Hotels;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private HotelAdapter hotelAdapter;
    private List<Hotels> hotelsList;
    HotelApiInterface hotelApiInterface;
    HotelAdapter.RecyclerViewClickListener listener;
    ProgressBar progressBar;

    RecyclerView recyclerView2;
    private com.theikdi.recyclerviewapi.hotls_sencond.HotelAdapter hotelAdapter2;
    private List<com.theikdi.recyclerviewapi.hotls_sencond.Hotels> hotelsList2;
    com.theikdi.recyclerviewapi.hotls_sencond.HotelApiInterface hotelApiInterface2;
    com.theikdi.recyclerviewapi.hotls_sencond.HotelAdapter.RecyclerViewClickListener listener2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // RV
        progressBar = findViewById(R.id.progress);
        hotelApiInterface = HotelApiClient.getHotelApiClient().create(HotelApiInterface.class);
        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        // RV ONe
        hotelApiInterface2 = com.theikdi.recyclerviewapi.hotls_sencond.HotelApiClient.getHotelApiClient().create(com.theikdi.recyclerviewapi.hotls_sencond.HotelApiInterface.class);
        recyclerView2 = findViewById(R.id.recycler_view2);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(layoutManager2);

        listener = new HotelAdapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                /*
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                intent.putExtra("name", hotelsList.get(position).getName());
                intent.putExtra("photo", hotelsList.get(position).getPhoto());
                startActivity(intent);
                 */
                Toast.makeText(MainActivity.this,"Row",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLoveClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "ON Love",Toast.LENGTH_LONG).show();
            }
        };
    }

    public void getHotels(){
        Call<List<Hotels>> call = hotelApiInterface.getHotels();
        call.enqueue(new Callback<List<Hotels>>() {
            @Override
            public void onResponse(@Nullable Call<List<Hotels>> call,@Nullable Response<List<Hotels>> response) {
                progressBar.setVisibility(View.GONE);
                assert response != null;
                hotelsList = response.body();
                assert response.body() != null;
                Log.i(MainActivity.class.getSimpleName(), response.body().toString());

                hotelAdapter = new HotelAdapter(hotelsList, MainActivity.this, listener);
                recyclerView.setAdapter(hotelAdapter);
                hotelAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(@Nullable Call<List<Hotels>> call,@Nullable Throwable t) {
                assert t != null;
                Toast.makeText(MainActivity.this, "rp :"+ t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getHotels1(){
        Call<List<com.theikdi.recyclerviewapi.hotls_sencond.Hotels>> call = hotelApiInterface2.getHotels();
        call.enqueue(new Callback<List<com.theikdi.recyclerviewapi.hotls_sencond.Hotels>>() {
            @Override
            public void onResponse(@Nullable Call<List<com.theikdi.recyclerviewapi.hotls_sencond.Hotels>> call,@Nullable Response<List<com.theikdi.recyclerviewapi.hotls_sencond.Hotels>> response) {
                progressBar.setVisibility(View.GONE);
                assert response != null;
                hotelsList2 = response.body();
                assert response.body() != null;
                Log.i(MainActivity.class.getSimpleName(), response.body().toString());

                hotelAdapter2 = new com.theikdi.recyclerviewapi.hotls_sencond.HotelAdapter(hotelsList2, MainActivity.this, listener2);
                recyclerView2.setAdapter(hotelAdapter2);
                hotelAdapter2.notifyDataSetChanged();
            }
            @Override
            public void onFailure(@Nullable Call<List<com.theikdi.recyclerviewapi.hotls_sencond.Hotels>> call,@Nullable Throwable t) {
                assert t != null;
                Toast.makeText(MainActivity.this, "rp :"+ t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Hotels...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                hotelAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                hotelAdapter.getFilter().filter(newText);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getHotels();
        getHotels1();
    }
}