package sabria.nobroadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import sabria.nobroadcast.library.BroadcastEx;
import sabria.nobroadcast.library.util.BroadcastUtil;

public class MainActivity extends AppCompatActivity implements BroadcastEx.EventsListener {

    int BroadcastId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BroadcastId = BroadcastUtil.register(this);
        findViewById(R.id.gotoSecond).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        BroadcastUtil.unregister(BroadcastId);
    }

    @Override
    public void onEvent(int eventId, Bundle params, boolean isBroadcasted) {
        switch (eventId) {
            case 0x0001A:
                String msg = params.getString(SecondActivity.bundle_key);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
