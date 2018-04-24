package yanzhikai.threadsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 测试线程中断的方法
 */
public class InterruptActivity extends AppCompatActivity {
    public static final String TAG = "InterruptActivity";

    private Thread threadA;

    @BindView(R.id.btn_interrupt)
    Button btnInterrupt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initThread();

    }

    private void initThread(){
        threadA = new Thread(){
            @Override
            public void run() {
                super.run();
                while (!isInterrupted()){
                    try {
                        Log.d(TAG, "run: ");
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.d(TAG, "InterruptedException");
                        break;
                    }
                }
            }
        };
        threadA.start();
    }

    @OnClick(R.id.btn_interrupt)
    public void interrupt(){
        threadA.interrupt();
    }
}
