package com.sparecode.vipul.onlynow.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.activity.GalleryActivity;
import com.sparecode.vipul.onlynow.activity.MultiCameraActivity;
import com.sparecode.vipul.onlynow.adapters.GalleryImagesAdapter;
import com.sparecode.vipul.onlynow.adapters.SpinnerAdapter;
import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ClientAddCouponWrapper;
import com.sparecode.vipul.onlynow.model.ClientGetCategoryWrapper;
import com.sparecode.vipul.onlynow.util.Constants;
import com.sparecode.vipul.onlynow.util.Image;
import com.sparecode.vipul.onlynow.util.Params;
import com.sparecode.vipul.onlynow.util.Util;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;
import com.sparecode.vipul.onlynow.webservice.VideoPostRequest;
import com.sparecode.vipul.onlynow.widgets.LatoButton;
import com.sparecode.vipul.onlynow.widgets.LatoEditText;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class ClientAddCouponFragment extends BaseFragment implements ClientGetCategoryBackend.GetClientCategoryDataProvider {

    @Bind(R.id.camera_button)
    Button cameraButton;
    @Bind(R.id.gallery_button)
    Button galleryButton;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.coupon_desc)
    LatoEditText couponDesc;
    @Bind(R.id.shop_name)
    LatoEditText shopName;
    @Bind(R.id.coupon_details)
    LatoEditText couponDetails;
    @Bind(R.id.add_coupon)
    LatoButton addCoupon;
    @Bind(R.id.start_date)
    LatoTextView startDate;
    @Bind(R.id.end_date)
    LatoTextView endDate;
    SimpleDateFormat dfDate;
    @Bind(R.id.category_list)
    Spinner categoryList;
    @Bind(R.id.addimage)
    ImageView addimage;
    private View view;
    private int mYear, mMonth, mDay;

    String startdate, enddate;
    ArrayList<Image> imagesList;
    private List<File> fileList;
    String categoryid;
    private String userChoosenTask;

    public ClientAddCouponFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_client_add_coupon, container, false);
        ButterKnife.bind(this, view);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        //startdate = mYear + "-" + (mMonth + 1) + "-" + mDay;

        // enddate = mYear + "-" + (mMonth + 1) + "-" + mDay;


        ClientGetCategoryBackend clientGetCategoryBackend = new ClientGetCategoryBackend(getActivity(), this);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Log.e("fromdate", year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "");
                                startDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                startdate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                // CheckDates(startdate, enddate);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Log.e("fromdate", year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "");
                                endDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                enddate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                CheckDates(startdate, enddate);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        dfDate = new SimpleDateFormat("yyyy-MM-dd");
        //CheckDates(startdate, enddate);

        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
                selectImage();
            }
        });
        return view;
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {


                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (Util.hasCameraHardware(getActivity()))
                        initiateMultiCapture();
                    else
                        Util.showLongSnack(view, "Sorry. Your device does not have a camera.");
                    //Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    initiateMultiPicker();
                    //Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public boolean CheckDates(String fromdate, String todate) {
        boolean b = false;
        Log.e("--->fromdate", fromdate);
        Log.e("--->todate", todate);
        try {
            if (dfDate.parse(todate).after(dfDate.parse(fromdate))) {
                b = true;

            } else {
                b = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("DATE")
                        .setMessage("Please Select Proper Date")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("------>boolean", b + "");
        return b;

    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.Add_Coupon));
        ((BaseActivity) getActivity()).getImgAdd().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getFab().setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case Constants.TYPE_MULTI_CAPTURE:
                handleResponseIntent(data);
                break;
            case Constants.TYPE_MULTI_PICKER:
                handleResponseIntent(data);
                break;
        }
    }

    private int getColumnCount() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        float thumbnailDpWidth = getResources().getDimension(R.dimen.thumbnail_width) / displayMetrics.density;
        return (int) (dpWidth / thumbnailDpWidth);
    }

    private void handleResponseIntent(Intent intent) {
        addimage.setVisibility(View.GONE);
        imagesList = intent.getParcelableArrayListExtra(Constants.KEY_BUNDLE_LIST);
        fileList = new ArrayList<>();
        for (int i = 0; i < imagesList.size(); i++) {
            AddImage(imagesList.get(i));
        }
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(3, GridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(mLayoutManager);
        GalleryImagesAdapter imageAdapter = new GalleryImagesAdapter(getActivity(), imagesList, getColumnCount(), new Params());
        recyclerView.setAdapter(imageAdapter);
    }

    public void AddImage(Image image) {
        File imgfile = new File(image.imagePath);
        fileList.add(imgfile);
        Log.e("filelist", String.valueOf(fileList.size()));
    }

    public void callService() {

/*
        Log.e("imagelistasd", fileList.toString());
        Log.e("imagelistasd", getUserId());
        Log.e("imagelistasd", shopName.getText().toString());
        Log.e("imagelistasd", couponDesc.getText().toString());
        Log.e("imagelistasd", couponDetails.getText().toString());
        Log.e("imagelistasd", startDate.getText().toString());
        Log.e("imagelistasd", endDate.getText().toString());
        Log.e("imagelistasd", categoryid);
*/
        List<Pair<String, String>> list = new ReqestParameter().toAddCLientCoupon(getUserId(),
                categoryid, shopName.getText().toString(), couponDesc.getText().toString(), couponDetails.getText().toString(), startDate.getText().toString(), endDate.getText().toString());
        if (getActivity() != null) {
            new VideoPostRequest<ClientAddCouponWrapper>().onPostRequest1(getActivity(), RequestApi.ADDCOUPON, list,
                    fileList, ClientAddCouponWrapper.class, new OnResponse<ClientAddCouponWrapper>() {


                        @Override
                        public void onSuccess(ClientAddCouponWrapper clientAddCouponWrapper) {
                            if (clientAddCouponWrapper.getStatus() == 1){
                                clientAddCouponWrapper.getMessage();
                                ((BaseActivity)getActivity()).openClientCouponPage();
                                Toast.makeText(getActivity(),"Coupon Added Successfully",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Snackbar.make(view,clientAddCouponWrapper.getMessage(),Snackbar.LENGTH_SHORT).show();
                                clientAddCouponWrapper.getMessage();
                            }

                            Log.e(this.getClass().getName(), " ::: RESPONSE IS ::: " + clientAddCouponWrapper.toString());
                        }

                        @Override
                        public void onError() {
                            //hideProgress();
                        }
                    });
        }
    }

    @OnClick({R.id.camera_button, R.id.gallery_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera_button:
                if (Util.hasCameraHardware(getActivity()))
                    initiateMultiCapture();
                else
                    Util.showLongSnack(view, "Sorry. Your device does not have a camera.");
                Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gallery_button:
                initiateMultiPicker();
                Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initiateMultiCapture() {
        Intent intent = new Intent(getActivity(), MultiCameraActivity.class);
        Params params = new Params();
        params.setCaptureLimit(3);
        intent.putExtra(Constants.KEY_PARAMS, params);
        startActivityForResult(intent, Constants.TYPE_MULTI_CAPTURE);
    }

    private void initiateMultiPicker() {
        Intent intent = new Intent(getActivity(), GalleryActivity.class);
        Params params = new Params();
        params.setCaptureLimit(3);
        params.setPickerLimit(3);
        intent.putExtra(Constants.KEY_PARAMS, params);
        startActivityForResult(intent, Constants.TYPE_MULTI_PICKER);
    }

    @OnClick(R.id.add_coupon)
    public void onClick() {
        callService();
    }

    @Override
    public void onSuccess(final ClientGetCategoryWrapper clientGetCategoryWrapper) {

        if (clientGetCategoryWrapper.getData()!=null)
        {
            categoryList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    categoryid = clientGetCategoryWrapper.getData().get(position).getId();
                    //Toast.makeText(getActivity(), categoryid, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            categoryList.setAdapter(new SpinnerAdapter(getActivity(), clientGetCategoryWrapper.getData()));
        }

    }

    @Override
    public void onFailure(String msg) {

    }
}
