package com.example.hp.corona;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.Gravity;
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
import com.squareup.okhttp.Address;
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
    int i;
    static ArrayList<AllExistingData> arrayList;

//    public static TextView st1, ct1, at1, rt1, dt1, st2, ct2, at2, rt2, dt2, st3, ct3, at3, rt3, dt3, st4, ct4, at4, rt4, dt4, st5, ct5, at5, rt5, dt5,
    //          st6, ct6, at6, rt6, dt6, st7, ct7, at7, rt7, dt7, st8, ct8, at8, rt8, dt8, st9, ct9, at9, rt9, dt9, st10, ct10, at10, rt10, dt10, st11, ct11, at11, rt11, dt11,
    //          st12, ct12, at12, rt12, dt12, st13, ct13, at13, rt13, dt13, st14, ct14, at14, rt14, dt14, st15, ct15, at15, rt15, dt15, st16, ct16, at16, rt16, dt16,
    //         st17, ct17, at17, rt17, dt17, st18, ct18, at18, rt18, dt18, st19, ct19, at19, rt19, dt19, st20, ct20, at20, rt20, dt20, st21, ct21, at21, rt21, dt21, st22, ct22, at22, rt22, dt22,
    //         st23, ct23, at23, rt23, dt23, st24, ct24, at24, rt24, dt24, st25, ct25, at25, rt25, dt25, st26, ct26, at26, rt26, dt26, st27, ct27, at27, rt27, dt27,
    //         st28, ct28, at28, rt28, dt28, st29, ct29, at29, rt29, dt29, st30, ct30, at30, rt30, dt30;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_tablefragment, container, false);
        getActivity().setTitle("Cases Of Corona");

        t1 = view.findViewById(R.id.table);
//        TableLayout t1 = view.findViewById(R.id.table);


        //    if (Build.VERSION.SDK_INT > 9){
        //        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //        StrictMode.setThreadPolicy(threadPolicy);
        //    }

//        fetch_data process = new fetch_data();
//        process.execute();

        arrayList = new ArrayList<AllExistingData>(0);

        //addHeaders();
        GetAllDataExistingTask getAllDataExistingTask = new GetAllDataExistingTask();
        getAllDataExistingTask.execute();

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return view;


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
            Log.d("123", "onPreExecute: No Error" + 123);

        }


        @Override
        protected String doInBackground(String... strings) {


            String responseBodyText = null;
            OkHttpClient client = new OkHttpClient();
            try {
                Request request = new Request.Builder().url(searchAPI).build();
                Response response = client.newCall(request).execute();

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
                        arrayList.add(new AllExistingData(states, confirmed, active, recovered, deaths));
                        //Toast.makeText(getActivity().getApplicationContext(),""+arrayList.get(Integer.parseInt(states)),Toast.LENGTH_SHORT).show();
                    }
                }

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

        @SuppressLint("NewApi")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            TableLayout stk = getView().findViewById(R.id.table);
            TableRow tbrow0 = new TableRow(getActivity().getApplicationContext());
            TextView tv0 = new TextView(getActivity().getApplicationContext());
            tv0.setText(" States ");
            tv0.setAllCaps(true);
            tv0.setGravity(Gravity.END);
            tv0.setWidth(2000);
            tv0.setTextColor(getResources().getColor(R.color.headings));
            tv0.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            // tv0.setTextColor(Integer.parseInt("#FFFFFF"));
            tbrow0.addView(tv0);
            TextView tv1 = new TextView(getActivity().getApplicationContext());
            tv1.setText("    Confirmed ");
            tv1.setTextColor(getResources().getColor(R.color.headings));
            //  tv1.setTextColor(Integer.parseInt("#FFFFFF"));
            tv1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tv1.setAllCaps(true);
            tv1.setWidth(380);
            tv1.setGravity(Gravity.CENTER);
            tbrow0.addView(tv1);
            TextView tv2 = new TextView(getActivity().getApplicationContext());
            tv2.setText(" Active ");
            tv2.setAllCaps(true);
            tv2.setTextColor(getResources().getColor(R.color.headings));
            tv2.setWidth(300);
            //tv2.setTextColor(Integer.parseInt("FFFFFF"));
            tv2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tv2.setGravity(Gravity.CENTER);
            tbrow0.addView(tv2);
            TextView tv3 = new TextView(getActivity().getApplicationContext());
            tv3.setText(" Recovered ");
            tv3.setWidth(380);
            tv3.setAllCaps(true);
            tv3.setTextColor(getResources().getColor(R.color.headings));
            tv3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tv3.setGravity(Gravity.CENTER);
            tv3.setAllCaps(true);
            tbrow0.addView(tv3);
            TextView tv4 = new TextView(getActivity().getApplicationContext());
            tv4.setText(" Deaths ");
            tv4.setGravity(Gravity.CENTER);
            tv4.setAllCaps(true);
            tv4.setWidth(300);
            tv4.setTextColor(getResources().getColor(R.color.headings));
            tv4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tbrow0.addView(tv4);

            stk.addView(tbrow0);

            //int size = arrayList.size();


            try{


                int rows = arrayList.size();

                for (i = 0; i < rows; i++) {
                    TableRow tbrow = new TableRow(getActivity().getApplicationContext());
                    TextView t0v = new TextView(getActivity().getApplicationContext());
                    t0v.setText(" " + arrayList.get(i).getStates());
                    t0v.setTextColor(getResources().getColor(R.color.set_backgroung_color));
                    //t1v.setTextColor(Color.WHITE);
                    t0v.setGravity(Gravity.END);
                    t0v.setWidth(2000);
                    tbrow.addView(t0v);
                    TextView t2v = new TextView(getActivity().getApplicationContext());
                    t2v.setText("    " + arrayList.get(i).getConfirmed());
                    //t2v.setText(" " + arrayList.get(i).getConfirmed());
                    t2v.setTextColor(getResources().getColor(R.color.set_backgroung_color));
                    // t2v.setTextColor(Color.WHITE);
                    t2v.setGravity(Gravity.CENTER);
                    t2v.setWidth(380);
                    tbrow.addView(t2v);
                    TextView t3v = new TextView(getActivity().getApplicationContext());
                    t3v.setText(" " + arrayList.get(i).getActive());
                    t3v.setTextColor(getResources().getColor(R.color.set_backgroung_color));
                    //t3v.setTextColor(Color.WHITE);
                    t3v.setWidth(300);
                    t3v.setGravity(Gravity.CENTER);
                    tbrow.addView(t3v);
                    TextView t4v = new TextView(getActivity().getApplicationContext());
                    t4v.setText(" " + arrayList.get(i).getRecovered());

                    //t4v.setText(" " + arrayList.get(i).getRecovered());
                    //t4v.setTextColor(Color.WHITE);
                    t4v.setTextColor(getResources().getColor(R.color.set_backgroung_color));
                    t4v.setGravity(Gravity.CENTER);
                    t4v.setWidth(380);
                    tbrow.addView(t4v);
                    TextView t5v = new TextView(getActivity().getApplicationContext());
                    t5v.setText(" " + arrayList.get(i).getDeaths());
                    //t5v.setText(" " + arrayList.get(i).getDeaths());
                    //t4v.setTextColor(Color.WHITE);
                    t5v.setTextColor(getResources().getColor(R.color.set_backgroung_color));
                    t5v.setWidth(300);
                    t5v.setGravity(Gravity.CENTER);
                    tbrow.addView(t5v);

                    stk.addView(tbrow);
                }

            }catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(),"Eror: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }



     /*     int allInputData = arrayList.size();

              for (int i = 0; i < allInputData; i++) {
                  TableRow tr = new TableRow(getActivity());
                  tr.setLayoutParams(getLayoutParams());

                  // String arr = arrayList.get(i).getStates();
                  //  t1.addView(tr,getLayoutParams());
                  tr.addView(getRowsTextView(0 , arrayList.get(i).getStates(), ContextCompat.getColor(getActivity(),R.color.set_backgroung_color)));
                  tr.addView(getRowsTextView(0 , arrayList.get(i).getConfirmed(),  ContextCompat.getColor(getActivity(),R.color.set_backgroung_color)));
                  tr.addView(getRowsTextView(0 , arrayList.get(i).getActive(), ContextCompat.getColor(getActivity(),R.color.set_backgroung_color)));
                  tr.addView(getRowsTextView(0 , arrayList.get(i).getRecovered(),  ContextCompat.getColor(getActivity(),R.color.set_backgroung_color)));
                  tr.addView(getRowsTextView(0 , arrayList.get(i).getDeaths(), ContextCompat.getColor(getActivity(),R.color.set_backgroung_color)));
                  t1.addView(tr, gettb1LayoutParams());
              } */

        }

        }

        private TextView getTextView(int id, String title, int bgColor) {
            TextView tv = new TextView(getActivity());
            tv.setId(id);
            tv.setText(title.toUpperCase());
            //   tv.setTextColor(color);
            tv.setPadding(40, 40, 40, 40);
            tv.setBackgroundColor(bgColor);
            tv.setLayoutParams(getLayoutParams());
            return tv;

        }

        private TextView getRowsTextView(int id, String title, int bgColor) {
            TextView tv = new TextView(getActivity());
            tv.setId(id);
            tv.setText(title);
            // tv.setTextColor(color);
            tv.setPadding(40, 40, 40, 40);
            tv.setBackgroundColor(bgColor);
            tv.setLayoutParams(getLayoutParams());
            return tv;
        }

        private TableRow.LayoutParams getLayoutParams() {
            TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT);
            params.setMargins(2, 0, 0, 2);
            return params;
        }

        private TableLayout.LayoutParams gettb1LayoutParams() {
            TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            return params;
        }


        @SuppressLint("ResourceType")
        public void addHeaders() {
            TableRow tableRow = new TableRow(getActivity());
            t1.setLayoutParams(getLayoutParams());

            tableRow.addView(getTextView(0, "States", R.color.colorAccent));
            tableRow.addView(getTextView(0, "Confirmed", R.color.colorAccent));
            tableRow.addView(getTextView(0, "Active", R.color.colorAccent));
            tableRow.addView(getTextView(0, "Recovered", R.color.colorAccent));
            tableRow.addView(getTextView(0, "Deaths", R.color.colorAccent));

            t1.addView(tableRow, gettb1LayoutParams());
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

