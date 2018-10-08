package com.cat.ahmed.cowfarm.Presenter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.cat.ahmed.cowfarm.Adapter.ResourceAdapter;
import com.cat.ahmed.cowfarm.Model.ResultModel.ResultModelFiterbyResource;
import com.cat.ahmed.cowfarm.R;

import java.util.List;

public class customResourcesDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    String userId;
    public Dialog d;
    public ImageView btn_water, btn_electricity , btn_doctors , btn_farmers , btn_workers;
    FrameLayout container;

    String resourceId;
    Handler handler;
    ProgressDialog progress;
    ResultModelFiterbyResource resultModelFiterbyResource;

    RecyclerView rcvOffers;
    ResourceAdapter adapter;

    List<ResultModelFiterbyResource> resultModelFiterbyResources;

    public customResourcesDialog(Activity a, String userId, List<ResultModelFiterbyResource> resultModelFiterbyResources) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
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

        rcvOffers =  findViewById(R.id.rcv_layout );

        btn_water =  findViewById(R.id.btn_water);
        btn_electricity =  findViewById(R.id.btn_electricity);
        btn_doctors =  findViewById(R.id.btn_doctors);
        btn_farmers =  findViewById(R.id.btn_farmers);
        btn_workers =  findViewById(R.id.btn_workers);
        container =  findViewById(R.id.container);

        btn_water.setOnClickListener(this);
        btn_electricity.setOnClickListener(this);
        btn_doctors.setOnClickListener(this);
        btn_farmers.setOnClickListener(this);
        btn_workers.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_water:
                getAlluserWithResource(resultModelFiterbyResources.get(0) , "1");

                break;
            case R.id.btn_electricity:
                getAlluserWithResource(resultModelFiterbyResources.get(1), "2");

                break;
            case R.id.btn_doctors:
                getAlluserWithResource(resultModelFiterbyResources.get(2), "4");

                break;
            case R.id.btn_farmers:
                getAlluserWithResource(resultModelFiterbyResources.get(3), "5");

                break;
            case R.id.btn_workers:
                getAlluserWithResource(resultModelFiterbyResources.get(4), "3");

                break;

            case R.id.btn_close:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    private void getAlluserWithResource(ResultModelFiterbyResource resultModelFiterbyResource, final String id) {
//
//
//     /*   progress = new ProgressDialog(c);
//        progress.setTitle(R.string.pleaseWait);
//        progress.setMessage(c.getString(R.string.loading));
//        progress.setCancelable(false);
//        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);*/
//
//        handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
///*
//                progress.dismiss();
//*/
//                super.handleMessage(msg);
//            }
//
//        };
//
//      //  progress.show();
//        new Thread() {
//            public void run() {
//                //Retrofit
//                ApiConnection connection = new ApiConnection();
//                Retrofit retrofit = connection.connectWith();
//
//                final HashMap<String, String> data = new HashMap<>();
//
//                data.put("user_id", userId);
//                data.put("item", item);
//                data.put("quantity", count);
//
//                final VillageApi marketApi = retrofit.create(VillageApi.class);
//
//                final Call<ResultModelFiterbyResource> getInterestConnection = marketApi.getUsersByResource(id);
//
//                getInterestConnection.enqueue(new Callback<ResultModelFiterbyResource>() {
//                    @Override
//                    public void onResponse(Call<ResultModelFiterbyResource> call, Response<ResultModelFiterbyResource> response) {
//                        try {
//
//                            if (!response.isSuccessful()) {
//                                try {
//                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
//                                    Toast.makeText(c , jObjError.getString("data"), Toast.LENGTH_LONG).show();
//                                } catch (Exception e) {
//                                    Toast.makeText( c , e.getMessage(), Toast.LENGTH_LONG).show();
//                                }
//                            } else {
//
//                                Toast.makeText(c , "successfully", Toast.LENGTH_LONG).show();
//                                setData(resultModelFiterbyResource , id);
//
//                            }
//
//                     //       progress.dismiss();
//
//                        } // try
//                        catch (Exception e) {
//                            Log.i("QP", "exception : " + e.toString());
//                   //         progress.dismiss();
//                        } // catch
//                    } // onResponse
//
//                    @Override
//                    public void onFailure(Call<ResultModelFiterbyResource> call, Throwable t) {
//                        Log.i("QP", "error : " + t.toString());
//                        progress.dismiss();
//                    } // on Failure
//                });
//                // Retrofit
//            }
//        }.start();


         setData(resultModelFiterbyResource, id);

    }

    private void setData(ResultModelFiterbyResource resultModelUserRequests, String id) {

        adapter = new ResourceAdapter(  c , resultModelUserRequests  , userId , id);
        rcvOffers.setAdapter(adapter);
        rcvOffers.setLayoutManager(new LinearLayoutManager(c));

        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));


        rcvOffers.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.HORIZONTAL));

        rcvOffers.setHasFixedSize(true);

    }


}