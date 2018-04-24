package yanzhikai.threadsdemo;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * IntentService的Demo
 */
public class IntentServiceActivity extends AppCompatActivity {

    private MyIntentService myIntentService;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            connection = (ServiceConnection) service;
            
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @BindView(R.id.btn_start)
    Button btnStart;

    private UpdateListener updateListener = new UpdateListener() {
        @Override
        public void onUpdate(int process) {
            btnStart.setText(process + "%");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        ButterKnife.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        Intent intent = new Intent(this,MyIntentService.class);
        intent.putExtra("start",1);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }


    interface UpdateListener {
        void onUpdate(int process);
    }


}
