package com.cat.ahmed.VTIFarm.Presenter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cat.ahmed.VTIFarm.Adapter.RequestsAdapter;
import com.cat.ahmed.VTIFarm.Model.ApiInterface.RequestApi;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelUserRequests;
import com.cat.ahmed.VTIFarm.R;
import com.cat.ahmed.VTIFarm.Retrofit.ApiConnection;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class customRequestsDialog  extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    String userId  ;
    public Dialog d;

    ImageView img_buidling_type ;
    TextView info;
    Button btn_close ;

    Handler handler;
    ProgressDialog progress;

    ResultModelUserRequests resultModelUserRequests;

    RecyclerView rcvOffers;
    RequestsAdapter adapter;

    public customRequestsDialog(Activity a , String userId ) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.userId = userId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_requests);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        rcvOffers =  findViewById(R.id.rcv_layout );

        btn_close  =  findViewById(R.id.btn_close);


        getUserReceivedRequests();


        btn_close.setOnClickListener(this);
    }

    private void getUserReceivedRequests() {

        progress = new ProgressDialog(c);
        progress.setTitle(R.string.pleaseWait);
        progress.setMessage(c.getString(R.string.loading));
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.dismiss();
                super.handleMessage(msg);
            }

        };
        progress.show();
        new Thread() {
            public void run() {
                //Retrofit
                ApiConnection connection = new ApiConnection();
                Retrofit retrofit = connection.connectWith();

                final RequestApi requestApi = retrofit.create(RequestApi.class);

                final Call<ResultModelUserRequests> getInterestConnection = requestApi.get_all_user_received_requests(userId);

                getInterestConnection.enqueue(new Callback<ResultModelUserRequests>() {
                    @Override
                    public void onResponse(Call<ResultModelUserRequests> call, Response<ResultModelUserRequests> response) {
                        try {

                            if (!response.isSuccessful()) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    Toast.makeText(c, jObjError.getString("data"), Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    Toast.makeText( c , e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                resultModelUserRequests = response.body();
                                setData(resultModelUserRequests);
                                Toast.makeText(c , "successfully", Toast.LENGTH_LONG).show();

                            }

                            progress.dismiss();

                        } // try
                        catch (Exception e) {
                            Log.i("QP", "exception : " + e.toString());
                            progress.dismiss();
                        } // catch
                    } // onResponse

                    @Override
                    public void onFailure(Call<ResultModelUserRequests> call, Throwable t) {
                        Log.i("QP", "error : " + t.toString());
                        progress.dismiss();
                    } // on Failure
                });
                // Retrofit
            }
        }.start();

    }

    private void setData(ResultModelUserRequests resultModelUserRequests) {

        adapter = new RequestsAdapter( resultModelUserRequests , c , userId);
        rcvOffers.setAdapter(adapter);
        rcvOffers.setLayoutManager(new LinearLayoutManager(c));

        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));


        rcvOffers.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.HORIZONTAL));

        rcvOffers.setHasFixedSize(true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.upgrade:
                upgradeBuilding();
                break;*/
            case R.id.btn_close:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}


