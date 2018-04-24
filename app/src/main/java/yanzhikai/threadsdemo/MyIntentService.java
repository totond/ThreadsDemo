package yanzhikai.threadsdemo;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/16
 * desc   :
 */

public class MyIntentService extends IntentService {
    public static final String TAG = "MyIntentService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService() {
        super(null);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int i = 0;
        if (intent != null) {
            i = intent.getIntExtra("start", 0);
        }
        while (i < 100) {
            i += 4;
            Log.d(TAG, "onHandleIntent: ");
        }

    }

}
