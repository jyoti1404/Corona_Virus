package com.example.hp.corona;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.utilities.Base64;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.collection.LLRBNode;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PrivateKey;
import java.util.ArrayList;

import cz.msebera.android.httpclient.client.HttpClient;

import static android.widget.Toast.*;

public class TableFragment extends Fragment {

    String searchAPI = "https://api.covid19india.org/data.json";
    ProgressDialog progressDialog;
    TableLayout t1;
    static ArrayList<AllExistingData> arrayList;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
//    public static TextView st1, ct1, at1, rt1, dt1, st2, ct2, at2, rt2, dt2, st3, ct3, at3, rt3, dt3, st4, ct4, at4, rt4, dt4, st5, ct5, at5, rt5, dt5,
  //          st6, ct6, at6, rt6, dt6, st7, ct7, at7, rt7, dt7, st8, ct8, at8, rt8, dt8, st9, ct9, at9, rt9, dt9, st10, ct10, at10, rt10, dt10, st11, ct11, at11, rt11, dt11,
  //          st12, ct12, at12, rt12, dt12, st13, ct13, at13, rt13, dt13, st14, ct14, at14, rt14, dt14, st15, ct15, at15, rt15, dt15, st16, ct16, at16, rt16, dt16,
   //         st17, ct17, at17, rt17, dt17, st18, ct18, at18, rt18, dt18, st19, ct19, at19, rt19, dt19, st20, ct20, at20, rt20, dt20, st21, ct21, at21, rt21, dt21, st22, ct22, at22, rt22, dt22,
   //         st23, ct23, at23, rt23, dt23, st24, ct24, at24, rt24, dt24, st25, ct25, at25, rt25, dt25, st26, ct26, at26, rt26, dt26, st27, ct27, at27, rt27, dt27,
   //         st28, ct28, at28, rt28, dt28, st29, ct29, at29, rt29, dt29, st30, ct30, at30, rt30, dt30;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_tablefragment, container, false);
     /*   st1 = view.findViewById(R.id.stext1);
        st2 = view.findViewById(R.id.stext2);
        st3 = view.findViewById(R.id.stext3);
        st4 = view.findViewById(R.id.stext4);
        st5 = view.findViewById(R.id.stext5);
        st6 = view.findViewById(R.id.stext6);
        st7 = view.findViewById(R.id.stext7);
        st8 = view.findViewById(R.id.stext8);
        st9 = view.findViewById(R.id.stext9);
        st10 = view.findViewById(R.id.stext10);
        st11 = view.findViewById(R.id.stext11);
        st12 = view.findViewById(R.id.stext12);
        st13 = view.findViewById(R.id.stext13);
        st14 = view.findViewById(R.id.stext14);
        st15 = view.findViewById(R.id.stext15);
        st16 = view.findViewById(R.id.stext16);
        st17 = view.findViewById(R.id.stext17);
        st18 = view.findViewById(R.id.stext18);
        st19 = view.findViewById(R.id.stext19);
        st20 = view.findViewById(R.id.stext20);
        st21 = view.findViewById(R.id.stext21);
        st22 = view.findViewById(R.id.stext22);
        st23 = view.findViewById(R.id.stext23);
        st24 = view.findViewById(R.id.stext24);
        st25 = view.findViewById(R.id.stext25);
        st26 = view.findViewById(R.id.stext26);
        st27 = view.findViewById(R.id.stext27);
        st28 = view.findViewById(R.id.stext28);
        st29 = view.findViewById(R.id.stext29);
        st30 = view.findViewById(R.id.states);
        ct1 = view.findViewById(R.id.ctext1);
        ct2 = view.findViewById(R.id.ctext2);
        ct3 = view.findViewById(R.id.ctext3);
        ct4 = view.findViewById(R.id.ctext4);
        ct5 = view.findViewById(R.id.ctext5);
        ct6 = view.findViewById(R.id.ctext6);
        ct7 = view.findViewById(R.id.ctext7);
        ct8 = view.findViewById(R.id.ctext8);
        ct9 = view.findViewById(R.id.ctext9);
        ct10 = view.findViewById(R.id.ctext10);
        ct11 = view.findViewById(R.id.ctext11);
        ct12 = view.findViewById(R.id.ctext12);
        ct13 = view.findViewById(R.id.ctext13);
        ct14 = view.findViewById(R.id.ctext14);
        ct15 = view.findViewById(R.id.ctext15);
        ct16 = view.findViewById(R.id.ctext16);
        ct17 = view.findViewById(R.id.ctext17);
        ct18 = view.findViewById(R.id.ctext18);
        ct19 = view.findViewById(R.id.ctext19);
        ct20 = view.findViewById(R.id.ctext20);
        ct21 = view.findViewById(R.id.ctext21);
        ct22 = view.findViewById(R.id.ctext22);
        ct23 = view.findViewById(R.id.ctext23);
        ct24 = view.findViewById(R.id.ctext24);
        ct25 = view.findViewById(R.id.ctext25);
        ct26 = view.findViewById(R.id.ctext26);
        ct27 = view.findViewById(R.id.ctext27);
        ct28 = view.findViewById(R.id.ctext28);
        ct29 = view.findViewById(R.id.ctext29);
        ct30 = view.findViewById(R.id.confirmed);
        at1 = view.findViewById(R.id.atext1);
        at2 = view.findViewById(R.id.atext2);
        at3 = view.findViewById(R.id.atext3);
        at4 = view.findViewById(R.id.atext4);
        at5 = view.findViewById(R.id.atext5);
        at6 = view.findViewById(R.id.atext6);
        at7 = view.findViewById(R.id.atext7);
        at8 = view.findViewById(R.id.atext8);
        at9 = view.findViewById(R.id.atext9);
        at10 = view.findViewById(R.id.atext10);
        at11 = view.findViewById(R.id.atext11);
        at12 = view.findViewById(R.id.atext12);
        at13 = view.findViewById(R.id.atext13);
        at14 = view.findViewById(R.id.atext14);
        at15 = view.findViewById(R.id.atext15);
        at16 = view.findViewById(R.id.atext16);
        at17 = view.findViewById(R.id.atext17);
        at18 = view.findViewById(R.id.atext18);
        at19 = view.findViewById(R.id.atext19);
        at20 = view.findViewById(R.id.atext20);
        at21 = view.findViewById(R.id.atext21);
        at22 = view.findViewById(R.id.atext22);
        at23 = view.findViewById(R.id.atext23);
        at24 = view.findViewById(R.id.atext24);
        at25 = view.findViewById(R.id.atext25);
        at26 = view.findViewById(R.id.atext26);
        at27 = view.findViewById(R.id.atext27);
        at28 = view.findViewById(R.id.atext28);
        at29 = view.findViewById(R.id.atext29);
        at30 = view.findViewById(R.id.active);
        rt1 = view.findViewById(R.id.rtext1);
        rt2 = view.findViewById(R.id.rtext2);
        rt3 = view.findViewById(R.id.rtext3);
        rt4 = view.findViewById(R.id.rtext4);
        rt5 = view.findViewById(R.id.rtext5);
        rt6 = view.findViewById(R.id.rtext6);
        rt7 = view.findViewById(R.id.rtext7);
        rt8 = view.findViewById(R.id.rtext8);
        rt9 = view.findViewById(R.id.rtext9);
        rt10 = view.findViewById(R.id.rtext10);
        rt11 = view.findViewById(R.id.rtext11);
        rt12 = view.findViewById(R.id.rtext12);
        rt13 = view.findViewById(R.id.rtext13);
        rt14 = view.findViewById(R.id.rtext14);
        rt15 = view.findViewById(R.id.rtext15);
        rt16 = view.findViewById(R.id.rtext16);
        rt17 = view.findViewById(R.id.rtext17);
        rt18 = view.findViewById(R.id.rtext18);
        rt19 = view.findViewById(R.id.rtext19);
        rt20 = view.findViewById(R.id.rtext20);
        rt21 = view.findViewById(R.id.rtext21);
        rt22 = view.findViewById(R.id.rtext22);
        rt23 = view.findViewById(R.id.rtext23);
        rt24 = view.findViewById(R.id.rtext24);
        rt25 = view.findViewById(R.id.rtext25);
        rt26 = view.findViewById(R.id.rtext26);
        rt27 = view.findViewById(R.id.rtext27);
        rt28 = view.findViewById(R.id.rtext28);
        rt29 = view.findViewById(R.id.rtext29);
        rt30 = view.findViewById(R.id.recovered);
        dt1 = view.findViewById(R.id.dtext1);
        dt2 = view.findViewById(R.id.dtext2);
        dt3 = view.findViewById(R.id.dtext3);
        dt4 = view.findViewById(R.id.dtext4);
        dt5 = view.findViewById(R.id.dtext5);
        dt6 = view.findViewById(R.id.dtext6);
        dt7 = view.findViewById(R.id.dtext7);
        dt8 = view.findViewById(R.id.dtext8);
        dt9 = view.findViewById(R.id.dtext9);
        dt10 = view.findViewById(R.id.dtext10);
        dt11 = view.findViewById(R.id.dtext11);
        dt12 = view.findViewById(R.id.dtext12);
        dt13 = view.findViewById(R.id.dtext13);
        dt14 = view.findViewById(R.id.dtext14);
        dt15 = view.findViewById(R.id.dtext15);
        dt16 = view.findViewById(R.id.dtext16);
        dt17 = view.findViewById(R.id.dtext17);
        dt18 = view.findViewById(R.id.dtext18);
        dt19 = view.findViewById(R.id.dtext19);
        dt20 = view.findViewById(R.id.dtext20);
        dt21 = view.findViewById(R.id.dtext21);
        dt22 = view.findViewById(R.id.dtext22);
        dt23 = view.findViewById(R.id.dtext23);
        dt24 = view.findViewById(R.id.dtext24);
        dt25 = view.findViewById(R.id.dtext25);
        dt26 = view.findViewById(R.id.dtext26);
        dt27 = view.findViewById(R.id.dtext27);
        dt28 = view.findViewById(R.id.dtext28);
        dt29 = view.findViewById(R.id.dtext29);
        dt30 = view.findViewById(R.id.deaths); */
        t1 = view.findViewById(R.id.table);
//        TableLayout t1 = view.findViewById(R.id.table);


        //    if (Build.VERSION.SDK_INT > 9){
        //        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //        StrictMode.setThreadPolicy(threadPolicy);
        //    }

//        fetch_data process = new fetch_data();
//        process.execute();

        arrayList = new ArrayList<AllExistingData>();
        addHeaders();
        GetAllDataExistingTask getAllDataExistingTask = new GetAllDataExistingTask();
        getAllDataExistingTask.execute();


        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return inflater.inflate(R.layout.activity_tablefragment, container, false);


// Write a message to the database


/*
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public TableFragment() {
        // Required empty public constructor
    }

    public static TableFragment newInstance(String param1, String param2) {
        TableFragment fragment = new TableFragment();
      //  FragmentTransaction fragmentTransaction = new TableFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
      //  fragmentTransaction.add(new TableFragment(), "FragmentA_Tag");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fragment selectedFragment = null;


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_tablefragment, container, false);
    }
}*/
    }


    public class GetAllDataExistingTask extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //ProgressDialog progressDialog = new ProgressDialog(TableFragment.this);
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
            Log.d("123", "onPreExecute: No Error"+ 123);

        }


        @Override
        protected String doInBackground(String... strings) {


            String responseBodyText = null;
            OkHttpClient client = new OkHttpClient();
            try {
                Request request = new Request.Builder().url(searchAPI).build();
                Response response = null;
                response = client.newCall(request).execute();

                if (!response.isSuccessful()) {
                    //  Object runOnUiThread;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Activity activity = getActivity();
                            Toast.makeText(activity, "Data Not Found..", Toast.LENGTH_SHORT).show();
                        }
                    });
                    throw new IOException("OKHttp Error: " + response);

                } else {
                    responseBodyText = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseBodyText);
                    JSONArray jsonArray = jsonObject.getJSONArray("statewise");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jobject = jsonArray.getJSONObject(i);
                        String states = jobject.getString("state");
                        String confirmed = jobject.getString("confirmed");
                        String active = jobject.getString("active");
                        String recovered = jobject.getString("recovered");
                        String deaths = jobject.getString("deaths");
                        arrayList.add(new AllExistingData(states,confirmed,active,recovered,deaths));
                    }
                }
                Log.d("123", "onPreExecute: No Error");
                int s = arrayList.size();
                System.out.println(s);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });
            return responseBodyText;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            int allInputData = arrayList.size();


            for (int i = 0; i < allInputData; i++) {
                TableRow tr = new TableRow(getActivity());
                tr.setLayoutParams(getLayoutParams());

               // String arr = arrayList.get(i).getStates();
              //  t1.addView(tr,getLayoutParams());
                tr.addView(getRowsTextView(i+1 , arrayList.get(i).getStates(), ContextCompat.getColor(getActivity(),R.color.cell_backgroung_color)));
                tr.addView(getRowsTextView(i + allInputData , arrayList.get(i).getConfirmed(),  ContextCompat.getColor(getActivity(),R.color.cell_backgroung_color)));
                tr.addView(getRowsTextView(i+allInputData , arrayList.get(i).getActive(), ContextCompat.getColor(getActivity(),R.color.cell_backgroung_color)));
                tr.addView(getRowsTextView(i+allInputData , arrayList.get(i).getRecovered(),  ContextCompat.getColor(getActivity(),R.color.cell_backgroung_color)));
                tr.addView(getRowsTextView(i+allInputData , arrayList.get(i).getDeaths(), ContextCompat.getColor(getActivity(),R.color.cell_backgroung_color)));
                t1.addView(tr, gettb1LayoutParams());

            }

        }

    }
    private TextView getTextView(int id,String title, int bgColor){
        TextView tv = new TextView(getActivity());
        tv.setId(id);
        tv.setText(title.toUpperCase());
        //   tv.setTextColor(color);
        tv.setPadding(40,40,40,40);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(getLayoutParams());
        return tv;

    }
    private TextView getRowsTextView (int id, String title, int bgColor){
        TextView tv = new TextView(getActivity());
        tv.setId(id);
        tv.setText(title);
        // tv.setTextColor(color);
        tv.setPadding(40,40,40,40);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(getLayoutParams());
        return tv;
    }

    private TableRow.LayoutParams getLayoutParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(2,0,0,2);
        return params;
    }

    private TableLayout.LayoutParams gettb1LayoutParams(){
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
        return params;
    }



    @SuppressLint("ResourceType")
    public void addHeaders(){
        TableRow tableRow = new TableRow(getActivity());
        t1.setLayoutParams(getLayoutParams());

        tableRow.addView(getTextView(0, "States",R.color.colorAccent));
        tableRow.addView(getTextView(0, "Confirmed",R.color.colorAccent));
        tableRow.addView(getTextView(0, "Active",R.color.colorAccent));
        tableRow.addView(getTextView(0, "Recovered",R.color.colorAccent));
        tableRow.addView(getTextView(0, "Deaths",R.color.colorAccent));

        t1.addView(tableRow,gettb1LayoutParams());
    }

}


/*
          String current = "";
            try {
                URL url;
               // HttpURLConnection urlConnection = "";
                try {
                    url = new URL(searchAPI);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);
                    int data = isw.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                        System.out.println(current);
                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                }
                catch (IOException e) {
                    e.printStackTrace();
                    return "Exception: " + e.getMessage();
                }
                return current;

        }
    } */ /*
    public ArrayList<User> parseJSON(String result) {
        try {
            ArrayList<User> users = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i=0;i<jsonArray.length();i++){

                JSONObject oneObject = jsonArray.getJSONObject(i);
                User user = new User();
                user.setStates(oneObject.getString("states"));
                user.setConfirmed(oneObject.getString("confirmed"));
                user.setActive(oneObject.getString("active"));
                user.setRecovered(oneObject.getString("recovered"));
                user.setDeaths(oneObject.getString("deaths"));
                users.add(user);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

} */

