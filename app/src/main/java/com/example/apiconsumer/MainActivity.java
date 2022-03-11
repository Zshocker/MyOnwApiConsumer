package com.example.apiconsumer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    AlertDialog Dialg;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Create_Search_Dialog();
        setContentView(R.layout.activity_main);
        findViewById(R.id.gets).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialg.show();
            }
        });
    }
    private void Create_Search_Dialog(){
        AlertDialog.Builder build=new AlertDialog.Builder(this);
        View vi=getLayoutInflater().inflate(R.layout.search_dialog,null);
        build.setView(vi);
        EditText Search=vi.findViewById(R.id.lien);
        EditText port=vi.findViewById(R.id.port);
        build.setTitle("What are you searching for ?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!Search.getText().toString().equals("")) {
                    Afficher_Objects.lien = Search.getText().toString();
                    Afficher_Objects.Port=port.getText().toString();
                }

                Intent iy=new Intent(getBaseContext(),Afficher_Objects.class);
                startActivity(iy);
            }
        }).setNegativeButton("Cancel",null);
        Dialg=build.create();
    }
}