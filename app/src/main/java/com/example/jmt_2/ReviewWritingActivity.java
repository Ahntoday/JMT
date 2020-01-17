package com.example.jmt_2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ReviewWritingActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;
    private FirebaseUser user;
    private Uri photoUri;
    private String imageFilePath;
    private Bitmap bitmap;
    private final int GET_GALLERY_IMAGE = 200;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private String reviewId;

    Button buttonsMenu[] = new Button[9];
    Button buttonsPlace[] = new Button[6];
    Button buttonsKeyword[] = new Button[9];

    Boolean buttonsMenuState[] = new Boolean[9];
    Boolean buttonsPlaceState[] = new Boolean[6];
    Boolean buttonsKeywordState[] = new Boolean[9];

    Button buttonComplete;
    ImageButton buttonClose;
    Button addCamera;
    ImageView imageView;
    EditText editText;
    EditText editText2;
    TextView textView;
    RatingBar ratingBar;

    int tagPlace;
    int tagMenu;
    int tagKeyword;
    String storeName;
    String nickName;
    String cu;
    String reviewText;
    String menuEaten;
    int numStar;
    String placeWord[] = {"전체", "후문", "상대", "정문", "예대", "전철우"};
    DataSnapshot data;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_writing);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        imageView = (ImageView) findViewById(R.id.imageView);

        textView = (TextView) findViewById(R.id.review_writing_storeName);
        storeName = getIntent().getStringExtra("storeName") + "";
        textView.setText(storeName);

        editText = (EditText) findViewById(R.id.review_text);
        editText2 = (EditText) findViewById(R.id.review_writing_menu_eaten_editText);

        ratingBar = (RatingBar) findViewById(R.id.review_writing_ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.e("star num", numStar + "");
                numStar = (int) rating;
            }
        });

        buttonsMenu[0] = (Button) findViewById(R.id.review_writing_filterButton_allMenu);
        buttonsMenu[1] = (Button) findViewById(R.id.review_writing_filterButton_koreanFood);
        buttonsMenu[2] = (Button) findViewById(R.id.review_writing_filterButton_schoolFood);
        buttonsMenu[3] = (Button) findViewById(R.id.review_writing_filterButton_westernFood);
        buttonsMenu[4] = (Button) findViewById(R.id.review_writing_filterButton_japaneseFood);
        buttonsMenu[5] = (Button) findViewById(R.id.review_writing_filterButton_chineseFood);
        buttonsMenu[6] = (Button) findViewById(R.id.review_writing_filterButton_chickenPizza);
        buttonsMenu[7] = (Button) findViewById(R.id.review_writing_filterButton_fastFood);
        buttonsMenu[8] = (Button) findViewById(R.id.review_writing_filterButton_cafePub);

        buttonsPlace[0] = (Button) findViewById(R.id.review_writing_filterButton_allPlace);
        buttonsPlace[1] = (Button) findViewById(R.id.review_writing_filterButton_backGate);
        buttonsPlace[2] = (Button) findViewById(R.id.review_writing_filterButton_sangdaeGate);
        buttonsPlace[3] = (Button) findViewById(R.id.review_writing_filterButton_frontGate);
        buttonsPlace[4] = (Button) findViewById(R.id.review_writing_filterButton_artGate);
        buttonsPlace[5] = (Button) findViewById(R.id.review_writing_filterButton_geoncheolwooGate);

        buttonsKeyword[0] = (Button) findViewById(R.id.review_writing_filterButton_cheapMoney);
        buttonsKeyword[1] = (Button) findViewById(R.id.review_writing_filterButton_eatAlone);
        buttonsKeyword[2] = (Button) findViewById(R.id.review_writing_filterButton_homework);
        buttonsKeyword[3] = (Button) findViewById(R.id.review_writing_filterButton_comeOutFast);
        buttonsKeyword[4] = (Button) findViewById(R.id.review_writing_filterButton_atmosphere);
        buttonsKeyword[5] = (Button) findViewById(R.id.review_writing_filterButton_teamProject);
        buttonsKeyword[6] = (Button) findViewById(R.id.review_writing_filterButton_comeAgain);
        buttonsKeyword[7] = (Button) findViewById(R.id.review_writing_filterButton_diningTogether);
        buttonsKeyword[8] = (Button) findViewById(R.id.review_writing_filterButton_badPlace);


        for (int i = 0; i < buttonsMenuState.length; i++) {
            buttonsMenuState[i] = false;
        }

        for (int i = 0; i < buttonsPlaceState.length; i++) {
            buttonsPlaceState[i] = false;
        }

        for (int i = 0; i < buttonsKeywordState.length; i++) {
            buttonsKeywordState[i] = false;
        }

        buttonComplete = (Button) findViewById(R.id.review_writing_completeButton);
        buttonClose = (ImageButton) findViewById(R.id.review_writing_buttonClose);

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        for (int i = 0; i < buttonsMenu.length; i++) {
            buttonsMenu[i].setTag(i);
            buttonsMenu[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button newButton = (Button) v;

                    for (Button tempButton : buttonsMenu) {
                        int position = (Integer) v.getTag();

                        if (newButton == tempButton) {
                            if (buttonsMenu[position].getCurrentTextColor() == getResources().getColor(R.color.colorMainGreen)) {
                                setAllButtonUnselected(buttonsMenu);
                                setButtonUnSelected(buttonsMenu[position]);
                                buttonsMenuState[position] = false;
                            } else {
                                setAllButtonUnselected(buttonsMenu);
                                setButtonSelected(buttonsMenu[position]);
                                buttonsMenuState[position] = true;
                            }
                        }

                        tagMenu = position;
                    }
                }
            });
        }

        for (int i = 0; i < buttonsPlace.length; i++) {
            buttonsPlace[i].setTag(i);
            buttonsPlace[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button newButton = (Button) v;
                    int position = (Integer) v.getTag();
                    for (Button tempButton : buttonsPlace) {
                        if (newButton == tempButton) {
                            if (buttonsPlace[position].getCurrentTextColor() == getResources().getColor(R.color.colorMainGreen)) {
                                setAllButtonUnselected(buttonsPlace);
                                setButtonUnSelected(buttonsPlace[position]);
                                buttonsPlaceState[position] = false;
                            } else {
                                setAllButtonUnselected(buttonsPlace);
                                setButtonSelected(buttonsPlace[position]);
                                buttonsPlaceState[position] = true;
                            }
                        }
                        tagPlace = position;
                    }
                }
            });
        }

        for (int i = 0; i < buttonsKeyword.length; i++) {
            buttonsKeyword[i].setTag(i);
            buttonsKeyword[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button newButton = (Button) v;
                    int position = (Integer) v.getTag();
                    for (Button tempButton : buttonsKeyword) {
                        if (newButton == tempButton) {
                            if (buttonsKeyword[position].getCurrentTextColor() == getResources().getColor(R.color.colorMainGreen)) {
                                setAllButtonUnselected(buttonsKeyword);
                                setButtonUnSelected(buttonsKeyword[position]);
                                buttonsKeywordState[position] = false;
                            } else {
                                setAllButtonUnselected(buttonsKeyword);
                                setButtonSelected(buttonsKeyword[position]);
                                buttonsKeywordState[position] = true;
                            }
                        }
                        tagKeyword = position;
                    }
                }
            });
        }

        buttonComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reviewText = editText.getText().toString();
                menuEaten = editText2.getText().toString();

                if (buttonComplete.getCurrentTextColor() == getResources().getColor(R.color.colorWhite)) {
                    setButtonCompleteUnselected();
                } else {
                    setButtonCompleteSelected();
                }

                if (checkAllButtonUnselected(buttonsMenuState)) {
                    setButtonCompleteUnselected();
                    makeToast();
                } else if (checkAllButtonUnselected(buttonsPlaceState)) {
                    setButtonCompleteUnselected();
                    makeToast();
                } else if (checkAllButtonUnselected(buttonsKeywordState)) {
                    setButtonCompleteUnselected();
                    makeToast();
                } else if (imageView.getDrawable() == getDrawable(R.drawable.review_textbox)) {
                    makePhotoToast();
                } else {
                    cu = mAuth.getUid();

                    database.getReference().child("users").child(cu).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            nickName = dataSnapshot.child("userName").getValue().toString();
                            Log.e("nickname", nickName+"");

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                    if (tagPlace == 0 && tagMenu == 0) {
                        database.getReference().child("jmtMarket").child("menus").child(Integer.toString(tagPlace)).child(storeName).setValue(storeName);
                        database.getReference().child("jmtMarket").child("locations").child(Integer.toString(tagPlace)).child(storeName).setValue(storeName);
                        database.getReference().child("jmtMarket").child("keywords").child(Integer.toString(tagPlace)).child(storeName).setValue(storeName);
                    } else if (tagPlace == 0) {
                        database.getReference().child("jmtMarket").child("menus").child("0").child(storeName).setValue(storeName);
                        database.getReference().child("jmtMarket").child("menus").child(Integer.toString(tagPlace)).child(storeName).setValue(storeName);
                        database.getReference().child("jmtMarket").child("locations").child(Integer.toString(tagPlace)).child(storeName).setValue(storeName);
                        database.getReference().child("jmtMarket").child("keywords").child(Integer.toString(tagPlace)).child(storeName).setValue(storeName);
                    } else if (tagMenu == 0) {
                        database.getReference().child("jmtMarket").child("menus").child(Integer.toString(tagPlace)).child(storeName).setValue(storeName);
                        database.getReference().child("jmtMarket").child("locations").child("0").child(storeName).setValue(storeName);
                        database.getReference().child("jmtMarket").child("locations").child(Integer.toString(tagPlace)).child(storeName).setValue(storeName);
                        database.getReference().child("jmtMarket").child("keywords").child(Integer.toString(tagPlace)).child(storeName).setValue(storeName);
                    } else {
                        database.getReference().child("jmtMarket").child("menus").child("0").child(storeName).setValue(storeName);
                        database.getReference().child("jmtMarket").child("menus").child(Integer.toString(tagPlace)).child(storeName).setValue(storeName);
                        database.getReference().child("jmtMarket").child("locations").child("0").child(storeName).setValue(storeName);
                        database.getReference().child("jmtMarket").child("locations").child(Integer.toString(tagPlace)).child(storeName).setValue(storeName);
                        database.getReference().child("jmtMarket").child("keywords").child(Integer.toString(tagPlace)).child(storeName).setValue(storeName);
                    }

                    final ReviewData reviewData1 = new ReviewData(cu+"", nickName+"", storeName+"", numStar, placeWord[tagPlace]+"", reviewText+"", menuEaten+"");
                    Log.e("nickName2", nickName+"");
                    database.getReference().child("users").child(cu).child("review").push().setValue(reviewData1);
                    database.getReference().child("users").child(cu).child("review").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            reviewId = dataSnapshot.getKey();

                            database.getReference().child("reviewData").child(reviewId).setValue(reviewData1);
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                    upload();
                    gotoMainActivity();


                }
            }
        });

        addCamera = findViewById(R.id.addCamera);
        addCamera.setOnClickListener(onClickListener);


    }

    private void setButtonUnSelected(Button button) {
        button.setBackgroundResource(R.drawable.button_filter);
        button.setTextColor(getResources().getColor(R.color.colorUnSelectedText));
    }

    private void setButtonSelected(Button button) {
        button.setBackgroundResource(R.drawable.button_filter_selected);
        button.setTextColor(getResources().getColor(R.color.colorMainGreen));
    }

    private void setButtonCompleteSelected() {
        buttonComplete.setBackgroundResource(R.drawable.button_select_complete_selected);
        buttonComplete.setBackgroundColor(getResources().getColor(R.color.colorMainGreen));
        buttonComplete.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void setButtonCompleteUnselected() {
        buttonComplete.setBackgroundResource(R.drawable.button_select_complete);
        buttonComplete.setTextColor(getResources().getColor(R.color.colorUnSelectedText));
    }


    private boolean checkAllButtonUnselected(Boolean buttonState[]) {
        for (int i = 0; i < buttonState.length; i++) {
            if (buttonState[i] == true)
                return false;
        }
        return true;
    }

    private void setAllButtonUnselected(Button buttons[]) {
        for (Button tempButton : buttons) {
            setButtonUnSelected(tempButton);
        }
    }

    private void makeToast() {
        Toast.makeText(this, "앗! 선택되지 않은 정보가 있어요!", Toast.LENGTH_SHORT).show();
    }

    private void makePhotoToast() {
        Toast.makeText(this, "사진을 선택해주세요", Toast.LENGTH_SHORT).show();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.addCamera:
                    sendTakePhotoIntent();
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        }
    };

    private void sendTakePhotoIntent() {
        int permissionCheck = ContextCompat.checkSelfPermission(ReviewWritingActivity.this, Manifest.permission.CAMERA);

        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(ReviewWritingActivity.this, new String[]{Manifest.permission.CAMERA}, 0);
        } else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }

                if (photoFile != null) {
                    photoUri = FileProvider.getUriForFile(this, getPackageName(), photoFile);

                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            bitmap = BitmapFactory.decodeFile(imageFilePath);
            ExifInterface exif = null;

            try {
                exif = new ExifInterface(imageFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int exifOrientation;
            int exifDegree;

            if (exif != null) {
                exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                exifDegree = exifOrientationToDegrees(exifOrientation);
            } else {
                exifDegree = 0;
            }

            imageView.setImageBitmap(rotate(bitmap, exifDegree));
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TEST_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,      /* prefix */
                ".jpg",         /* suffix */
                storageDir          /* directory */
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }

    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    private Bitmap rotate(Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private void uploadUserInfo(Uri imagePath) {

    }


    private void upload() {
        firebaseStorage = FirebaseStorage.getInstance();
        final StorageReference storageRef = firebaseStorage.getReferenceFromUrl("gs://jmt-project-e3de3.appspot.com").child("ReviewImage").child(user.getUid());
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = storageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                makePhotoToast();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!urlTask.isSuccessful()) ;
                Uri url = urlTask.getResult();
                uploadUserInfo(url);
            }
        });

    }

    private void block() {

    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        //onAttachFragment(new ReviewFragment());
        finish();
    }


}
