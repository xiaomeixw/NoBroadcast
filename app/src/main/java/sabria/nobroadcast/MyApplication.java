package sabria.nobroadcast;

import android.app.Application;

import sabria.nobroadcast.library.util.BroadcastUtil;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-22  19:06
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BroadcastUtil.init(this);
    }
}
