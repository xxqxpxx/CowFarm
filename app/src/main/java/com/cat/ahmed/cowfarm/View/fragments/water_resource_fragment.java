package com.cat.ahmed.cowfarm.View.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.cat.ahmed.cowfarm.Adapter.ResourceAdapter;
import com.cat.ahmed.cowfarm.Model.ApiInterface.VillageApi;
import com.cat.ahmed.cowfarm.Model.ResultModel.ResultModelFiterbyResource;
import com.cat.ahmed.cowfarm.R;
import com.cat.ahmed.cowfarm.Retrofit.ApiConnection;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;


public class water_resource_fragment extends Fragment {

    public Activity c;
    String userId;
    public Dialog d;
    public ImageView btn_water, btn_electricity, btn_doctors, btn_farmers, btn_workers;
    FrameLayout container;

    String resourceId;
    Handler handler;
    ProgressDialog progress;
    ResultModelFiterbyResource resultModelFiterbyResource;

    RecyclerView rcvOffers;
    ResourceAdapter adapter;

    List<ResultModelFiterbyResource> resultModelFiterbyResources;


    public water_resource_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_water_resource_fragment, container, false);



        return view;
    }

    private void setData(ResultModelFiterbyResource resultModelUserRequests, String id) {

        adapter = new ResourceAdapter(c, resultModelUserRequests, userId, id);
        rcvOffers.setAdapter(adapter);
        rcvOffers.setLayoutManager(new LinearLayoutManager(c));

        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));


        rcvOffers.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.HORIZONTAL));

        rcvOffers.setHasFixedSize(true);


    }

    private void getAlluserWithResourceNow(final String id) {


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ApiConnection connection = new ApiConnection();
        Retrofit retrofit = connection.connectWith();

        final VillageApi marketApi = retrofit.create(VillageApi.class);

        final Call<ResultModelFiterbyResource> getInterestConnection = marketApi.getUsersByResource(id);

        Response<ResultModelFiterbyResource> response = null;
        try {
            response = getInterestConnection.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!response.isSuccessful()) {
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Toast.makeText(getContext(), jObjError.getString("data"), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(c, "successfully", Toast.LENGTH_LONG).show();
            resultModelFiterbyResource = response.body();
            setData(resultModelFiterbyResource, id);
        }
    }
}
