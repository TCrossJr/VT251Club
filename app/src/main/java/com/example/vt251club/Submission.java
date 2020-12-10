package com.example.vt251club;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;


public class Submission extends AppCompatActivity {
    AutoCompleteTextView searchTextField;
    EditText submissionTextField;
    CheckBox shareCheckBox;
    Button addImageButton;

    private static final int PICK_IMAGE = 10;
    Uri imageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        addImageButton = findViewById(R.id.addImageButton);
        searchTextField = findViewById(R.id.submissionSearch);
        submissionTextField = findViewById(R.id.submissionText);
        shareCheckBox = findViewById(R.id.checkBox);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.town_name_list, android.R.layout.select_dialog_singlechoice);
        AutoCompleteTextView search = findViewById(R.id.submissionSearch);
        search.setThreshold(3);
        search.setAdapter(adapter);

    }

    public void submit(View view){
        String submissionText = String.valueOf((submissionTextField.getText()));
        String searchText = String.valueOf(searchTextField.getText());
        if(!Arrays.asList(getResources().getStringArray(R.array.town_name_list)).contains(searchText)){
            Snackbar.make(view, "That isn't a town", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        }else if(submissionText.matches("")){
            Snackbar.make(view, "You can submit and empty description", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        }
        if(Arrays.asList(getResources().getStringArray(R.array.town_name_list)).contains(searchText) && !submissionText.matches("")){
            //Put code to Enter submission in db here
            
        }
    }

    public void addImage(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = intent.getData();
        }
        addImageButton.setText("Change Image");
    }


}