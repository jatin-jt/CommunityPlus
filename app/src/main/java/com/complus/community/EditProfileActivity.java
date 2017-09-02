package com.complus.community;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.complus.community.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {

    private static final String TAG = "EditProfileActivity";

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
    EditText etName, etContact, etAddress, etPin;
    User mUser;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etName = (EditText) findViewById(R.id.et_username);
        etContact = (EditText) findViewById(R.id.et_contact);
        etAddress = (EditText) findViewById((R.id.et_address));
        etPin = (EditText) findViewById(R.id.et_pincode);

        btnEdit = (Button) findViewById(R.id.btn_editprofile);

        //check if already present
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    mUser = dataSnapshot.getValue(User.class);
                    Log.d(TAG, "onDataChange: "+ dataSnapshot);
                    Log.d(TAG, "onDataChange: hhH"+mUser);
                    etName.setText(mUser.getName());
                    etContact.setText(mUser.getContact());
                    etAddress.setText(mUser.getAddress());
                    etPin.setText(mUser.getPincode());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    mUser = new User(user.getUid(), etName.getText().toString(), etContact.getText().toString(),
                            etAddress.getText().toString(), etPin.getText().toString(), mUser.getPoints());
                }catch (NullPointerException e)
                {
                    mUser = new User(user.getUid(), etName.getText().toString(), etContact.getText().toString(),
                            etAddress.getText().toString(),etPin.getText().toString(), "0");
                }
                    userRef.setValue(mUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(EditProfileActivity.this, "Saved changes! :)", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                            intent.putExtra("userid", user.getUid());
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditProfileActivity.this, "Changes could not be saved. Please try again later!", Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });


    }
}
