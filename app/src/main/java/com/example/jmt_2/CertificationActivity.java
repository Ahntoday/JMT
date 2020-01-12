package com.example.jmt_2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class CertificationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ImageView ivImage;
    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.backButton).setOnClickListener(onClickListener);
        findViewById(R.id.nextButton).setOnClickListener(onClickListener);
        findViewById(R.id.addCamera).setOnClickListener(onClickListener);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.nextButton:
                case R.id.next:
                    gotoMainActivity();
                    mOnPopupClick(v);
                    break;
                case R.id.backButton:
                case R.id.back:
                    gotoSignUpActivity();
                    break;
                case R.id.addCamera:
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        }
    };

    private void gotoSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoPopUpActivity() {
        Intent intent = new Intent(this, PopUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void mOnPopupClick(View v){
        //데이터 담아서 팝업(액티비티) 호출
        Intent intent = new Intent(this, PopUpActivity.class);
        intent.putExtra("data", "Test Popup");
        startActivityForResult(intent, 1);
    }
    private void gotoMainActivity() {
        Log.e("test", "buttowon");
        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);

        }

    }
}
//
//    ActivityCompat.requestPermissions(this, new String[]
//
//    {
//        Manifest.permission.WRITE_EXTERNAL_STORAGE
//    },REQUEST_PERMISSION_CODE);
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        switch (requestCode) {
//
//            case REQUEST_PERMISSION_CODE:
//
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    //동의 했을 경우
//                } else {
//                    //거부했을 경우
//                    Toast toast = Toast.makeText(this, "기능 사용을 위한 권한 동의가 필요합니다.", Toast.LENGTH_SHORT);
//                    toast.show();
//                }
//                break;
//        }
//    }
//
//    int permissionCheck = ContextCompat.checkSelfPermission(WriteMemoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//    if(ActivityCompat.shouldShowRequestPermissionRationale(WriteMemoActivity .this,Manifest.permission.WRITE_EXTERNAL_STORAGE))
//
//    {
//
//    }
//
//    private final int CAMERA_CODE = 1111;
//
//    private void selectPhoto() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, CAMERA_CODE);
//    }
//
//    private Uri photoUri;
//    private String currentPhotoPath;//실제 사진 파일 경로
//
//    String mImageCaptureName;//이미지 이름
//    private void selectPhoto() {
//        String state = Environment.getExternalStorageState();
//    }
//    if (Environment.MEDIA_MOUNTED.equals(state)) {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            File photoFile = null;
//        }
//        try {
//            photoFile = createImageFile();
//        }
//        catch (IOException ex) {
//
//        } if (photoFile != null) { photoUri = FileProvider.getUriForFile(this, getPackageName(), photoFile); intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri); startActivityForResult(intent, CAMERA_CODE); } } } }
//
//    private File createImageFile() throws IOException {
//        File dir = new File(Environment.getExternalStorageDirectory() + "/path/");
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        mImageCaptureName = timeStamp + ".png";
//        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/path/" + mImageCaptureName);
//        currentPhotoPath = storageDir.getAbsolutePath();
//        return storageDir;
//    }
//
//    private void getPictureForPhoto() {
//        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
//        ExifInterface exif = null;
//        try {
//            exif = new ExifInterface(currentPhotoPath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int exifOrientation;
//        int exifDegree;
//        if (exif != null) {
//            exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//            exifDegree = exifOrientationToDegrees(exifOrientation);
//        } else {
//            exifDegree = 0;
//        }
//        ivImage.setImageBitmap(rotate(bitmap, exifDegree));//이미지 뷰에 비트맵 넣기 }
//
//
//    }
//
//    private final int GALLERY_CODE = 1112;
//
//    private void selectGallery() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setType("image/*");
//        startActivityForResult(intent, GALLERY_CODE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case GALLERY_CODE:
//                    sendPicture(data.getData());//갤러리에서 가져오기
//                    break;
//                case CAMERA_CODE:
//                    getPictureForPhoto();// 카메라에서 가져오기
//                    break;
//                default:
//                    break;
//
//            }
//        }
//    }
//
//    private void sendPicture(Uri imgUri) {
//        String imagePath = getRealPathFromURI(imgUri); // path 경로
//        ExifInterface exif = null;
//        try {
//            exif = new ExifInterface(imagePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//        int exifDegree = exifOrientationToDegrees(exifOrientation);
//        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);// 경로를 통해 비트맵으로 전환
//        ivImage.setImageBitmap(rotate(bitmap, exifDegree));//이미지 뷰에 비트맵 넣기
//    }
//
//    private int exifOrientationToDegrees(int exifOrientation) {
//        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
//            return 90;
//        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
//            return 180;
//        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
//            return 270;
//        }
//        return 0;
//    }
//
//    private Bitmap rotate(Bitmap src, float degree) {// Matrix 객체 생성
//        Matrix matrix = new Matrix(); //회전 각도 셋팅
//        matrix.postRotate(degree);// 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
//        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
//    }
//
//    private String getRealPathFromURI(Uri contentUri) {
//        int column_index = 0;
//        String[] proj = {MediaStore.Images.Media.DATA};
//        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
//        if (cursor.moveToFirst()) {
//            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        }
//        return cursor.getString(column_index);
//    }
//
//    private Bitmap resize(Context context, Uri uri, int resize) {
//        Bitmap resizeBitmap = null;
//
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        try {
//            BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options); // 1번
//
//            int width = options.outWidth;
//            int height = options.outHeight;
//            int samplesize = 1;
//
//            while (true) {//2번
//                if (width / 2 < resize || height / 2 < resize)
//                    break;
//                width /= 2;
//                height /= 2;
//                samplesize *= 2;
//            }
//
//            options.inSampleSize = samplesize;
//            Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options); //3번
//            resizeBitmap = bitmap;
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return resizeBitmap;
//    }
//
//
//        }
