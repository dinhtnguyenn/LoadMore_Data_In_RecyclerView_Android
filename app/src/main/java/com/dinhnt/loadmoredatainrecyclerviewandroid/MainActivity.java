package com.dinhnt.loadmoredatainrecyclerviewandroid;

import static com.dinhnt.loadmoredatainrecyclerviewandroid.service.ServiceAPI.baseURL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dinhnt.loadmoredatainrecyclerviewandroid.adapter.ListProductAdapter;
import com.dinhnt.loadmoredatainrecyclerviewandroid.models.Product;
import com.dinhnt.loadmoredatainrecyclerviewandroid.service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    private RecyclerView recyclerView;
    private ProgressBar pbLoading;
    private TextView txtNoti;
    private int currentIndex = 0;
    private int elementsNumber = 10;
    private ArrayList<Product> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayout linearLoading = findViewById(R.id.linearLoading);
        pbLoading = findViewById(R.id.pbLoading);
        txtNoti = findViewById(R.id.txtNoti);
        NestedScrollView nestedScroll = findViewById(R.id.nestedScroll);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        getData();

        nestedScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    count++;
                    linearLoading.setVisibility(View.VISIBLE);
                    if (count < elementsNumber) {
                        getData();
                    }
                }
            }
        });

    }

    private void getData() {
        recyclerView.setVisibility(View.VISIBLE);

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.getData(currentIndex, elementsNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<Product> products) {
        if(products.size() > 0){
            list.addAll(products);
            ListProductAdapter adapter = new ListProductAdapter(this, list);
            recyclerView.setAdapter(adapter);
            currentIndex++;
        }else {
            pbLoading.setVisibility(View.GONE);
            txtNoti.setText("Hết rồi!!! Còn gì đâu mà xem");
        }
    }

    private void handleError(Throwable error) {

    }
}