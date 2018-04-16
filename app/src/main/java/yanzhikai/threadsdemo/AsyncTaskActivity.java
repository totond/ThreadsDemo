package yanzhikai.threadsdemo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AsyncTaskActivity extends AppCompatActivity {
    public static final String TAG = "AsyncTaskActivity";

    private AsyncTask<String, Float, String> task;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        task = new AsyncTask<String, Float, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.d(TAG, "onPreExecute: init!");
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d(TAG, "onPostExecute: " + s);
            }

            @Override
            protected void onProgressUpdate(Float... values) {
                super.onProgressUpdate(values);
                Log.d(TAG, "onProgressUpdate: " + values[0]);
            }

            @Override
            protected void onCancelled(String s) {
                super.onCancelled(s);
                Log.d(TAG, "onCancelled: " + s);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                Log.d(TAG, "onCancelled: ");
            }

            protected String doInBackground(String... urls) {
                int count = urls.length;
                if (isCancelled()){
                    return "Cancel";
                }
                for (int i = 0; i < count; i++){
                    if (isCancelled()){
                        return "Cancel";
                    }
                    try {
                        Thread.sleep(3000);
                        publishProgress((float) i / count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return "Finish all!";
            }
        };
        task.execute("www.baidu.com","www.google.com","www.amazon.com");
    }

    @Override
    protected void onStop() {
        super.onStop();
        task.cancel(true);
    }
}
