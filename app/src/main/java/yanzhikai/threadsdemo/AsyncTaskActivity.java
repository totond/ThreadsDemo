package yanzhikai.threadsdemo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AsyncTaskActivity extends AppCompatActivity {
    public static final String TAG = "AsyncTaskActivity";
    @BindView(R.id.btn_start)
    Button btnStart;

    private AsyncTask<Integer, Integer, String> task;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        ButterKnife.bind(this);

        task = new AsyncTask<Integer, Integer, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.d(TAG, "onPreExecute: init!");
                btnStart.setEnabled(false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d(TAG, "onPostExecute: " + s);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                Log.d(TAG, "onProgressUpdate: " + values[0]);
                btnStart.setText(String.valueOf(values[0]) + "%");
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

            protected String doInBackground(Integer... integers) {
                if (isCancelled()) {
                    return "Cancel";
                }
                for (int i = 0; i <= 100; i += integers[0]) {
                    if (isCancelled()) {
                        return "Cancel";
                    }
                    try {
                        Thread.sleep(300);
                        publishProgress(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return "Finish all!";
            }
        };
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        task.execute(4);
    }

    @Override
    protected void onStop() {
        super.onStop();
        task.cancel(true);
    }
}
