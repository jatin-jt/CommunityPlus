package com.complus.community;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.complus.community.models.Complaint;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddComplaint extends AppCompatActivity implements IPickResult{

    EditText name, desc, cat, threat;
    Button addImage,addComplaint;
    ImageView imgView;
    Boolean image = false;
    Uri uri;
    FirebaseUser user;
    DatabaseReference mainRef = FirebaseDatabase.getInstance().getReference();
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    private static final String TAG = "AddComplaint";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_complaint);

        user = FirebaseAuth.getInstance().getCurrentUser();
        name = (EditText) findViewById(R.id.et_comp_name);
        desc = (EditText) findViewById(R.id.et_comp_desc);
        cat = (EditText) findViewById(R.id.et_comp_category);
        threat = (EditText) findViewById(R.id.et_comp_threat);

        addComplaint = (Button) findViewById(R.id.btn_add_complaint);
        addImage = (Button) findViewById(R.id.btn_add_image);

        imgView = (ImageView) findViewById(R.id.img_view_item);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView.setVisibility(View.VISIBLE);
                PickSetup ps = new PickSetup()
                        .setCancelTextColor(Color.parseColor("#7ca800"));
                PickImageDialog.build(ps).show(AddComplaint.this);
            }
        });

        addComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cName = name.getText().toString();
                String cDesc = desc.getText().toString();
                String cCat = cat.getText().toString();
                String cThreat = threat.getText().toString();
                Date newDate = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String date = sdf.format(newDate);
                String cId = mainRef.child("complaints").push().getKey();

                Complaint complaint = new Complaint(cCat,cThreat,cName,cDesc,date,
                        image.toString(),cId,user.getUid());
                mainRef.child("complaints").child(cId).setValue(complaint);

                if (image) {
                    storageRef = FirebaseStorage.getInstance().getReference().child("com" + cId);
                    storageRef.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            Log.d(TAG, "onComplete: upload cocmplete");
                            Toast.makeText(AddComplaint.this, "Picture uploaded successfully!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddComplaint.this, "Picture could not be uploaded", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                mainRef.child("users-complaints").child(user.getUid()).child(cId)
                        .setValue("true").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AddComplaint.this, "Complaint added successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddComplaint.this, HomeActivity.class);
                        finish();
                        startActivity(intent);
                    }
                });

            }
        });

    }
    @Override
    public void onPickResult(PickResult pickResult) {
        if (pickResult.getError() == null) {
            uri = pickResult.getUri();
            imgView.setImageURI(uri);
            image = true;
        } else {
            Toast.makeText(this, pickResult.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
