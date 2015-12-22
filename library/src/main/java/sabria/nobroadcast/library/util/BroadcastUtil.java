package sabria.nobroadcast.library.util;


import android.content.Context;
import android.os.Bundle;

import sabria.nobroadcast.library.BroadcastEvent;
import sabria.nobroadcast.library.BroadcastEx;


/**
 * Created by xiongwei & lele,An Android project Engineer.
 * Date: 2015-08-07  10:50
 * Base on Meilimei.com (PHP)
 * Describe:
 * Version:1.0
 * Open source
 */
public class BroadcastUtil {

    /**在QuickUtils中初始化*/
    public static void init(Context context){
        BroadcastEvent.init(context);
    }

    /**在onCreate中初始化*/
    public static int register(BroadcastEx.EventsListener listener){
        return BroadcastEvent.register(listener);
    }

    /**在onDestory中初始化*/
    public static void unregister(int regId){
        BroadcastEvent.unregister(regId);
    }

    /**
     * 发送EVENT_ID
     * @param ID
     * @param bundle
     */
    public static void post(BroadcastCx.DEF_EVENT_ID ID,Bundle bundle){
        if(ID==null){
            ID=BroadcastCx.DEF_EVENT_ID.Cx_Defalut;
        }
        BroadcastEvent.send(ID.getIndex(), bundle);
    }
    
    
    public static Bundle getBundle(){
        return new Bundle();
    }
    
    
}
