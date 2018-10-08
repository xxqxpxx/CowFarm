package com.cat.ahmed.cowfarm.Presenter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.cat.ahmed.cowfarm.Adapter.ResourceAdapter;
import com.cat.ahmed.cowfarm.Model.ApiInterface.VillageApi;
import com.cat.ahmed.cowfarm.Model.ResultModel.ResultModelFiterbyResource;
import com.cat.ahmed.cowfarm.R;
import com.cat.ahmed.cowfarm.Retrofit.ApiConnection;
import com.cat.ahmed.cowfarm.View.fragments.water_resource_fragment;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class customResourcesDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity context;
    String userId;
    public Dialog d;
    public ImageView btn_water, btn_electricity, btn_doctors, btn_farmers, btn_workers;
    FrameLayout container;

    String resourceId  ;
    Handler handler;
    ProgressDialog progress;
    ResultModelFiterbyResource resultModelFiterbyResource;

    RecyclerView rcvOffers;
    ResourceAdapter adapter;

    List<ResultModelFiterbyResource> resultModelFiterbyResources;

    public customResourcesDialog(Activity a, String userId, List<ResultModelFiterbyResource> resultModelFiterbyResources) {
        super(a);
        // TODO Auto-generated constructor stub
        this.context = a;
        this.userId = userId;
        this.resultModelFiterbyResources = resultModelFiterbyResources;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_resource);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        rcvOffers = findViewById(R.id.rcv_layout);

        getAlluserWithResourceNow("1");

        btn_water = findViewById(R.id.btn_water);
        btn_electricity = findViewById(R.id.btn_electricity);
        btn_doctors = findViewById(R.id.btn_doctors);
        btn_farmers = findViewById(R.id.btn_farmers);
        btn_workers = findViewById(R.id.btn_workers);
      //  container = findViewById(R.id.container);

        btn_water.setOnClickListener(this);
        btn_electricity.setOnClickListener(this);
        btn_doctors.setOnClickListener(this);
        btn_farmers.setOnClickListener(this);
        btn_workers.setOnClickListener(this);


       // startًWaterFragment();

    }

   /* private void startًWaterFragment() {
       getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new water_resource_fragment())
                .commit();
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_water:
                getAlluserWithResourceNow("1");
                btn_water.setImageResource(R.drawable.resource_water_on);
                break;
            case R.id.btn_electricity:
                getAlluserWithResourceNow("2");
                btn_electricity.setImageResource(R.drawable.resource_elec_on);

                break;
            case R.id.btn_doctors:
                getAlluserWithResourceNow("4");
                btn_doctors.setImageResource(R.drawable.resource_doc_on);

                break;
            case R.id.btn_farmers:
                getAlluserWithResourceNow("5");
                btn_farmers.setImageResource(R.drawable.resource_farmer_on);

                break;
            case R.id.btn_workers:
                getAlluserWithResourceNow("3");
                btn_workers.setImageResource(R.drawable.resource_worker_on);
                break;

            case R.id.btn_close:
                dismiss();
                break;
            default:
                break;
        }
    }


    private void setData(ResultModelFiterbyResource resultModelUserRequests, String id) {

        if (rcvOffers != null) {
            rcvOffers.removeAllViews();
        }

        if ( adapter != null )
            adapter.notifyDataSetChanged();




        adapter = new ResourceAdapter(context, resultModelUserRequests, userId, id);
        rcvOffers.setAdapter(adapter);
        rcvOffers.setLayoutManager(new LinearLayoutManager(context));

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
           //     Toast.makeText(getContext(), jObjError.getString("data"), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
           //     Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
          //  Toast.makeText(context, "successfully", Toast.LENGTH_LONG).show();
            resultModelFiterbyResource = response.body();
            setData(resultModelFiterbyResource, id);
        }
    }
}