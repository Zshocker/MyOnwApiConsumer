package com.example.apiconsumer;

import android.util.JsonReader;
import android.util.Log;
import android.util.MalformedJsonException;

import com.example.apiconsumer.bo.Etudiant;
import com.google.gson.Gson;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class onlineFilesReader
{
    URL uri;
    HttpURLConnection urlConnection=null;
    InputStream Inp=null;

    public List<Etudiant> Read_ListObjects(String ur)
    {
        Gson gson=new Gson();
        Prepare_File(ur);
        List<Etudiant> obl;
        Etudiant[] etudiants=gson.fromJson((Reader) new InputStreamReader(Inp), (Type) Etudiant[].class);
        obl= new LinkedList<Etudiant>(Arrays.asList(etudiants));
        Stop_Reading();
        return obl;
    }
    private void Prepare_File(String ur)
    {

        try {
            uri=new URL(ur);
            if(Inp==null)
            {
                if(urlConnection==null)
                {
                    if(ur.contains("https"))urlConnection =(HttpsURLConnection)uri.openConnection();
                    else urlConnection=(HttpURLConnection) uri.openConnection();
                    urlConnection.setRequestMethod("GET");
                }
                int a=urlConnection.getResponseCode();
            Inp= urlConnection.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void Stop_Reading()
    {
        try
        {
            Inp.close();
            urlConnection.disconnect();
            urlConnection=null;
            Inp=null;
        }catch (Exception e){
        }
    }

}
