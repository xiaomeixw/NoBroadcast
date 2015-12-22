package sabria.nobroadcast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import sabria.nobroadcast.library.util.BroadcastCx;
import sabria.nobroadcast.library.util.BroadcastUtil;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-22  19:11
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class SecondActivity extends AppCompatActivity {

    public static final String bundle_key="key";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.btSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = BroadcastUtil.getBundle();
                params.putString(bundle_key, "Hello");
                BroadcastUtil.post(BroadcastCx.DEF_EVENT_ID.Cx_0x0001,params);
            }
        });
    }
}
