package com.sparecode.vipul.onlynow.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ChangeProfilePictureWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.model.UpdateProfileWrapper;
import com.sparecode.vipul.onlynow.permission.PiemissionsCallback;
import com.sparecode.vipul.onlynow.permission.PiemissionsRequest;
import com.sparecode.vipul.onlynow.permission.PiemissionsUtils;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.util.Utility;
import com.sparecode.vipul.onlynow.view.CircleImageView;
import com.sparecode.vipul.onlynow.webservice.PostRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;
import com.sparecode.vipul.onlynow.widgets.LatoButton;
import com.sparecode.vipul.onlynow.widgets.LatoEditText;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hitesh on 20/3/17.
 */

public class UpdateProfileFragment extends BaseFragment implements UpdateProfileBackendUser.UpdateProfileDataProvider {


    @Bind(R.id.firstname)
    LatoEditText firstname;
    @Bind(R.id.lastname)
    LatoEditText lastname;
    @Bind(R.id.ClickUpdateProfile)
    LatoButton ClickUpdateProfile;
    UpdateProfileBackendUser updateProfileBackend;
    View view;
    @Bind(R.id.UserImage)
    CircleImageView UserImage;
    Context mContext;
    @Bind(R.id.UpdateProfile)
    LinearLayout UpdateProfile;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    File destination;
    private static final int PERMISSIONS_CODE = 13370;
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE
            ,Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE

    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.updateprofile, container, false);
        ButterKnife.bind(this, view);

        UserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        final PiemissionsRequest request = new PiemissionsRequest(PERMISSIONS_CODE, PERMISSIONS);
        request.setCallback(new PiemissionsCallback() {
            @Override
            public void onGranted() {
                Log.e("log----::", "Permission Granted");
                //locationHelper = new LocationHelper(getActivity(), ClientEditprofileFragment.this);
            }

            @Override
            public boolean onDenied(HashMap<String, Boolean> rationalizablePermissions) {
                Log.e("log---::", "Permission Denied");

                return true;
            }
        });
        PiemissionsUtils.requestPermission(request);
        return view;
    }

    SignupWrapper signupWrapper;

    private void getUser() {
        signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);
        Log.e("::", "SIGNUP WRAPPER::" + signupWrapper.getData());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getUser();
        if (!signupWrapper.getData().getImage().trim().isEmpty()) {
            Picasso.with(mContext).load(signupWrapper.getData().getImage()).resize(250, 250).placeholder(R.drawable.placeholder).into(UserImage);
        }
    }

    @OnClick(R.id.ClickUpdateProfile)
    void onClickUpdateProfile() {
        callService();
        updateProfileBackend = new UpdateProfileBackendUser(getActivity(), signupWrapper.getData().getId(), firstname.getText().toString(), lastname.getText().toString(), this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(getActivity());

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        Log.e("file", String.valueOf(destination));
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserImage.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG,90,bytes);

                destination = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),System.currentTimeMillis() + ".jpg");
                Log.e("gallery", String.valueOf(destination));
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        UserImage.setImageBitmap(bm);
    }
    public void callService() {

        /*Log.e("imagegallery", String.valueOf(destination));
        Log.e("imagegallery", signupWrapper.getData().getId());*/

        List<Pair<String, String>> list = new ReqestParameter().toUpdateProfilePicture(signupWrapper.getData().getId());

        new PostRequest<ChangeProfilePictureWrapper>().onPostRequest(getActivity(), RequestApi.UPDATEPROFILEPICTURE, list, "image", destination, ChangeProfilePictureWrapper.class, new OnResponse<ChangeProfilePictureWrapper>() {
            @Override
            public void onSuccess(ChangeProfilePictureWrapper changeProfilePictureWrapper) {
                if (changeProfilePictureWrapper.getStatus() == 1)
                {
                    Toast.makeText(getActivity(),"Profile picture Updated Successfully",Toast.LENGTH_SHORT).show();
                }
                else
                {

                }
            }

            @Override
            public void onError() {
                Toast.makeText(getActivity(),"Please try again",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText("Update Profile");
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgToolBarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).openProfilePage();
            }
        });
    }

    @Override
    public void onSuccess(UpdateProfileWrapper updateProfileWrapper) {
        Snackbar.make(view, updateProfileWrapper.getMessage(), Snackbar.LENGTH_SHORT).show();
        ((BaseActivity)getActivity()).openProfilePage();

    }

    @Override
    public void onFailure(String msg) {
        if (getActivity() != null)
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
