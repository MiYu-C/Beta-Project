package com.zws.team_project;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ZujiActivity extends AppCompatActivity {

    // ***************************************************************//

    // 关于一些图片的交互
    public static int CHOOSE_PHOTO = 2;
    public static int TAKE_PHOTO = 1;
    private ImageButton addPhoto1;
    private ImageButton addPhoto2;
    private Uri photoUri;
    private ImageView picture;


    // END

    // 图片交互END

    // 百度定位 SDK
    private Button getLocation;
    private TextView positionTextView;
    public LocationClient mlocationClient;
    // 百度定位END


    //****************************************************************//



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuji);
        addPhoto1 = (ImageButton) findViewById(R.id.addPhoto);
        addPhoto2 = (ImageButton) findViewById(R.id.takephoto);
        picture = (ImageView)findViewById(R.id.showzujiphoto);

        getLocation = (Button) findViewById(R.id.getLocation);
        positionTextView = (TextView) findViewById(R.id.location);

        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> permissionList = new ArrayList<>();
                if (ContextCompat.checkSelfPermission(ZujiActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
                }
                if (ContextCompat.checkSelfPermission(ZujiActivity.this, Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(Manifest.permission.READ_PHONE_STATE);
                }
                if (ContextCompat.checkSelfPermission(ZujiActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
                mlocationClient = new LocationClient(getApplicationContext());
                mlocationClient.registerLocationListener(new MyLocationListener());
                if (!permissionList.isEmpty()) {
                    String[] permissions = permissionList.toArray(new String[permissionList.size()]);
                    ActivityCompat.requestPermissions(ZujiActivity.this, permissions, 3);
                } else {
                    requestLocation();
                }
            }
        });

        addPhoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(ZujiActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ZujiActivity.this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });
        addPhoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建File用于存储照片
                File photo = new File(getExternalCacheDir(),"thisphoto.jpg");
                //picture = (ImageView) findViewById(R.id.picture);
                if (photo.exists()) {
                    photo.delete();
                }
                try {
                    photo.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    photo.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 如果安卓版本大于安卓7.0
                if (Build.VERSION.SDK_INT >= 24) {
                    photoUri = FileProvider.getUriForFile(ZujiActivity.this,"com.zws.team_project." +
                            "fileprovider",photo);
                } else {
                    photoUri = Uri.fromFile(photo);
                }
                // 启动相机
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
        });
    }
    public void zujiBT(View v)
    {
        //Intent intent = new Intent(ShezhiActivity.this, MainActivity.class);
        //startActivity(intent);
        ZujiActivity.this.finish();
    }
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            } else if ("content".equalsIgnoreCase(uri.getScheme())){
                // 如果是content，则用普通方法处理
                imagePath = getImagePath(uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())){
                imagePath = uri.getPath();
            }
        }
        displayImage(imagePath);
    }


    private void handleImageBeforeKitkat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
        }
        cursor.close();
        return path;
    }

    private void displayImage(String imagePath){
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "出现了问题", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        // requestCode = 1 为 拍照
        if (requestCode == TAKE_PHOTO && resultCode == RESULT_OK) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(photoUri));
                picture.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        // requestCode = 2 为 图库
        if (requestCode == CHOOSE_PHOTO && resultCode == RESULT_OK) {

            if (Build.VERSION.SDK_INT >= 19) {
                handleImageOnKitKat(data);
            } else {
                handleImageBeforeKitkat(data);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults){
        // 疑权限申请有误
        // 调用图库的请求码
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openAlbum();
        } else {
            Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == 3) {
            if (grantResults.length > 0) {
                for (int result : grantResults) {
                    if(result != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "无定位权限", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                }
                requestLocation();
            } else {
                Toast.makeText(this, "出现了问题", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void requestLocation(){
        initLocation();
        mlocationClient.start();
    }
    private void initLocation(){
        LocationClientOption op = new LocationClientOption();
        //op.setScanSpan(3000);
        op.setIsNeedAddress(true);
        mlocationClient.setLocOption(op);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mlocationClient.stop();
    }
    @Override
    protected void onStop(){
        super.onStop();
        mlocationClient.stop();
    }


    class MyLocationListener implements BDLocationListener {
        StringBuilder currentPosition = new StringBuilder();
        @Override
        public void onReceiveLocation(BDLocation location) {
//            currentPosition.append("当前经度:").append(location.getLatitude()).append("\n");
//            currentPosition.append("当前纬度").append(location.getLongitude()).append("\n");
            if(location.getCountry() == null){
                currentPosition.append("无法获取的位置信息，请打开GPS和网络");
            } else{
                currentPosition.append(location.getCountry() + " ").append(location.getProvince());
                currentPosition.append(location.getCity()).append(location.getDistrict());
                currentPosition.append(location.getStreet()).append("\n");
            }
//            Message message = new Message();
//            message.what = UPDATE_FLAG;
//            message.obj = currentPosition.toString();
//            handler.sendMessage(message);
            positionTextView.post(new Runnable() {
                @Override
                public void run() {
                    positionTextView.setText(currentPosition);
                }
            });
        }

    }
}
