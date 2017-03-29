package com.sparecode.vipul.onlynow.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.activity.MainActivity;
import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ChangeProfilePictureWrapper;
import com.sparecode.vipul.onlynow.model.LoginWrapper;
import com.sparecode.vipul.onlynow.permission.PiemissionsCallback;
import com.sparecode.vipul.onlynow.permission.PiemissionsRequest;
import com.sparecode.vipul.onlynow.permission.PiemissionsUtils;
import com.sparecode.vipul.onlynow.util.Utility;
import com.sparecode.vipul.onlynow.view.CircleImageView;
import com.sparecode.vipul.onlynow.webservice.PostRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;
import com.sparecode.vipul.onlynow.widgets.LatoButton;
import com.sparecode.vipul.onlynow.widgets.LatoEditText;

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

public class ClientEditprofileFragment extends BaseFragment implements UpdateProfileBackend.UpdateProfileDataProvider{

    @Bind(R.id.edit_profilepicture)
    CircleImageView editProfilepicture;
    @Bind(R.id.ediprofile_firstname)
    LatoEditText ediprofileFirstname;
    @Bind(R.id.ediprofile_lastname)
    LatoEditText ediprofileLastname;
    @Bind(R.id.update_profile)
    LatoButton updateProfile;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    File destination;
    private View view;;
    private static final int PERMISSIONS_CODE = 13370;
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE
            ,Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE

    };
    public ClientEditprofileFragment() {
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

        view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        ButterKnife.bind(this, view);
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

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.edit_profiles));
        ((BaseActivity) getActivity()).getImgToolBarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).openClientSettingPage();
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.edit_profilepicture, R.id.update_profile})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_profilepicture:
                selectImage();
                break;
            case R.id.update_profile:

                UpdateProfileBackend updateProfileBackend = new UpdateProfileBackend(getActivity(),getUserId(),ediprofileFirstname.getText().toString().trim(),ediprofileFirstname.getText().toString().trim(),this);
                callService();
                break;
        }
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

        editProfilepicture.setImageBitmap(thumbnail);
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

        editProfilepicture.setImageBitmap(bm);
    }

    public void callService() {

        /*Log.e("imagegallery", String.valueOf(destination));
        Log.e("imagegallery", getUserId());*/

        List<Pair<String, String>> list = new ReqestParameter().toUpdateProfilePicture(getUserId());

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
    public void onSuccesscull(LoginWrapper updateProfileWrapper) {
        ((MainActivity)getActivity()).setUserData1(updateProfileWrapper.getData());
        Snackbar.make(view,updateProfileWrapper.getMessage(),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String msg) {
        Snackbar.make(view,"try again",Snackbar.LENGTH_SHORT).show();
    }
}
