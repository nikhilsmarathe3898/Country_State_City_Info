package com.example.country_state_city_info.AsyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

public class MyTask extends AsyncTask<Void, Integer, String> {

    Context context;
    TextView textView;
    Button button;
    ProgressDialog progressDialog;

    public MyTask(Context context, TextView textView, Button button) {
        this.context = context;
        this.textView = textView;
        this.button = button;
    }

    public MyTask() {
    }

    @Override
    protected String doInBackground(Void... voids) {
        int i = 0;
        synchronized (this) {
            while (i < 10) {
                try {
                    wait(10);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Download Complete....";
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Download in Progress....");
        progressDialog.setMax(0);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDialog.setProgress(progress);
        textView.setText("Download in Progress....");
    }

    @Override
    protected void onPostExecute(String result) {
        textView.setText(result);
        button.setEnabled(true);
        progressDialog.hide();
    }
}