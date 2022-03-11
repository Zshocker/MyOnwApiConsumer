package com.example.apiconsumer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apiconsumer.bo.Etudiant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Afficher_Objects extends AppCompatActivity {
    LinearLayout Lis;

    public static String lien="192.168.1.100",Port="3000";
    public  String line ="http://"+lien+":"+Port+"/api/Student";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        Lis=findViewById(R.id.Rect);
        new Async().execute(line);
    }
    class Async extends AsyncTask<String,Void, List<Etudiant>>
    {

        @Override
        protected List<Etudiant> doInBackground(String... strings) {
            return new onlineFilesReader().Read_ListObjects(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Etudiant> etudiants) {
            AfficherList(etudiants);
        }
    }
    private void AfficherList(List<Etudiant> objectList)
    {
        for (Etudiant Data : objectList) {
            add_object(Data);
        }
    }
    private void add_object(Etudiant data)
    {
        Lis.addView(Prepare_View_Etudiant(data));
    }

    private View Prepare_View_Etudiant(Etudiant data)
    {
        ViewGroup view=(ViewGroup) getLayoutInflater().inflate(R.layout.weather_card,null);
        TextView Text=view.findViewById(R.id.day);
        TextView Text2=view.findViewById(R.id.Temper);
        TextView Text3=view.findViewById(R.id.Wind);
        ImageView img=view.findViewById(R.id.image);
        Text.setText(data.getId()+"");
        Text2.setText(data.getCne());
        Text3.setText(data.getName());
        String lientoImage="http://"+lien+":"+Port+data.getImageUrl().substring(1);
        Picasso.get().load(lientoImage).resize(150,150).into(img);
        View Ce=view.findViewById(R.id.Card_ma);
        view.removeView(Ce);
        return Ce;
    }

}