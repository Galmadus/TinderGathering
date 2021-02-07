package com.example.tindergathering.ui.edit_profile_activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tindergathering.AccesLocal;
import com.example.tindergathering.MainActivity;
import com.example.tindergathering.R;
import com.example.tindergathering.ui.user.User;

import java.text.ParseException;
import java.util.Date;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView imageView;
    public AccesLocal accesLocal;
    private CheckBox checkBoxCommander;
    private CheckBox checkBoxStandard;
    private CheckBox checkBoxPioneer;
    private CheckBox checkBoxBrawl;
    private CheckBox checkBoxVintage;
    private String formats = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_profile);
        imageView = findViewById(R.id.new_profile_picture);
        Button b= findViewById(R.id.btnSelectPhoto);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });
        Button takePhoto=(Button)findViewById(R.id.btnTakePhoto);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
            }
        });

        accesLocal = new AccesLocal(this);
        try {
            User user = accesLocal.selectUserSQLite(1);
            final EditText editTextMail = findViewById(R.id.mail);
            editTextMail.setText(user.getEmail());
            final EditText editTextName = findViewById(R.id.name);
            editTextName.setText(user.getName());
            final EditText editTextFirstName = findViewById(R.id.firstname);
            editTextFirstName.setText(user.getFirstName());
            final EditText editTextDescription = findViewById(R.id.description);
            editTextDescription.setText(user.getDescription());
            final ImageView imageViewPicture = findViewById(R.id.new_profile_picture);
            imageViewPicture.setImageResource(user.getPicture());
            if(user.getFormats().contains("Commander")){
                checkBoxCommander = findViewById(R.id.format_commander);
                checkBoxCommander.setChecked(true);
            }
            if(user.getFormats().contains("Standard")){
                checkBoxStandard = findViewById(R.id.format_standard);
                checkBoxStandard.setChecked(true);
            }
            if(user.getFormats().contains("Pioneer")){
                checkBoxPioneer = findViewById(R.id.format_pioneer);
                checkBoxPioneer.setChecked(true);
            }
            if(user.getFormats().contains("Brawl")){
                checkBoxBrawl = findViewById(R.id.format_brawl);
                checkBoxBrawl.setChecked(true);
            }
            if(user.getFormats().contains("Vintage")){
                checkBoxVintage = findViewById(R.id.format_vintage);
                checkBoxVintage.setChecked(true);
            }
            if(checkBoxCommander.isChecked()){
                formats += "Commander,";
            }
            if(checkBoxStandard.isChecked()){
                formats += "Standard,";
            }
            if(checkBoxPioneer.isChecked()){
                formats += "Pioneer,";
            }
            if(checkBoxBrawl.isChecked()){
                formats += "Brawl,";
            }
            if(checkBoxVintage.isChecked()){
                formats += "Vintage,";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Button valider = findViewById(R.id.edit_profile_button);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Toast.makeText(view.getContext().getApplicationContext(), "Tapes pas trop fort stp...", Toast.LENGTH_SHORT).show();

                TextView mailView =  findViewById(R.id.mail);
                String mail = mailView.getText().toString();

                TextView passwordView =  findViewById(R.id.password);
                String password = passwordView.getText().toString();

                TextView nameView =  findViewById(R.id.name);
                String name = nameView.getText().toString();

                TextView firstnameView =  findViewById(R.id.firstname);
                String firstname = firstnameView.getText().toString();

                TextView descriptionView =  findViewById(R.id.description);
                String description = descriptionView.getText().toString();

                DatePicker birthdayView =  findViewById(R.id.birthdate);
                String birthday = birthdayView.getDayOfMonth()+"/"+birthdayView.getMonth()+"/"+birthdayView.getYear();

                java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
                Bitmap bm = ((android.graphics.drawable.BitmapDrawable) imageView.getDrawable()).getBitmap();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String imageString = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);

                //int drawableId = Integer.parseInt(findViewById(R.id.new_profile_picture).getTag().toString());

                if(mail.equals("") & password.equals("") & name.equals("") & firstname.equals("") & birthday.equals("")){
                    Toast.makeText(context, "Merci de remplir les champs", Toast.LENGTH_SHORT).show();
                }else{
                    User user = null;
                    try {
                        user = accesLocal.selectUserSQLite(1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    User registration = new User(user.getUsername(), password, new Date(birthday), user.getGender(), mail, imageView.getId(), name, firstname, description, user.getCity(), formats);
                    registration.setId(1);
                    try {
                        accesLocal.updateUserSQLite(registration);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(view.getContext().getApplicationContext(), "Modification terminée", Toast.LENGTH_SHORT).show();
                }


                startMainActivity();
            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode== RESULT_OK){
                    Bundle extras = imageReturnedIntent.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imageView.setImageBitmap(imageBitmap);
                }
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imageView.setImageURI(selectedImage);
                }

                break;
        }
    }
    protected void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}