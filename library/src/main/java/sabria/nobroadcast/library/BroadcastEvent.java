package sabria.nobroadcast.library;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-08-07  15:09
 * Base on Meilimei.com (PHP Service)
 * Describe: 广播Event对象
 * Version:1.0
 * Open source
 */
public class BroadcastEvent {

    private static Context mContext;
    private static String mIntentAction;
    private static final SparseArray<BroadcastEx.EventsReceiver> mReceiversMap = new SparseArray<>();
    private static int mRegistrationId;
    private static final Map<String, Intent> mStickyEventsMap = new HashMap<String, Intent>();

    /**
     * 在QuickUtils中初始化
     *
     * @param context
     */
    public static void init(Context context) {
        if (mContext == null) {
            mContext = context.getApplicationContext();
            mIntentAction = BroadcastEx.getRandomNum(context);
        }
    }

    public static synchronized int register(BroadcastEx.EventsListener listener) {
        return register(null, listener);
    }

    /**
     * 在OnCreate（或者onCreateView）中注册
     *
     * @param receiverId
     * @param listener
     * @return
     */
    private static int register(String receiverId, BroadcastEx.EventsListener listener) {
        BroadcastEx.judgeBroad(mContext, listener);

        // 初始化一个EventsReceiver
        BroadcastEx.EventsReceiver receiver = new BroadcastEx.EventsReceiver();
        receiver.mReceiverId = receiverId;
        receiver.mListener = listener;

        // 注册广播
        try {
            mContext.registerReceiver(receiver, new IntentFilter(mIntentAction, BroadcastEx.BASE_MIME_TYPE + "/*"));
        } catch (IntentFilter.MalformedMimeTypeException e) {
            e.printStackTrace();
        }

        // 不断的在map中加入receiver，key是一个数字不断+1的数字
        mReceiversMap.put(++mRegistrationId, receiver);

        // 不断的执行onReceive
        for (Intent sticky : mStickyEventsMap.values()) {
            receiver.onReceive(mContext, sticky);
        }

        // 返回注册的id值,这个id值对应要取消的那个key
        return mRegistrationId;
    }

    /**
     * 注册广播 在onDestory中执行
     *
     * @param regId
     */
    public static synchronized void unregister(int regId) {
        BroadcastEx.judgeApplication(mContext);
        // map中取出对应key-id的EventsReceiver
        BroadcastEx.EventsReceiver receiver = mReceiversMap.get(regId);
        mReceiversMap.delete(regId);
        // 注册广播
        if (receiver != null) {
            mContext.unregisterReceiver(receiver);
        }
    }

    /**
     * 发送广播
     *
     * @param eventId
     */
    public static void send(int eventId) {
        send(eventId, null, false, null);
    }

    public static void send(int eventId, Bundle params) {
        send(eventId, params, false, null);
    }

    public static void send(int eventId, String receiverId) {
        send(eventId, null, false, receiverId);
    }

    public static void send(int eventId, String receiverId, Bundle params) {
        send(eventId, params, false, receiverId);
    }

    public static void sendSticky(int eventId) {
        send(eventId, null, true, null);
    }

    public static void sendSticky(int eventId, Bundle params) {
        send(eventId, params, true, null);
    }

    public static void sendSticky(int eventId, String receiverId) {
        send(eventId, null, true, receiverId);
    }

    public static void sendSticky(int eventId, String receiverId, Bundle params) {
        send(eventId, params, true, receiverId);
    }

    private synchronized static void send(int eventId, Bundle params, boolean sticky, String receiverId) {
        BroadcastEx.judgeApplication(mContext);
        String mimeType = BroadcastEx.buildMimeType(eventId, receiverId);

        // 创建Intent并传递值
        Intent intent = new Intent(mIntentAction).setType(mimeType);
        if (params != null) {
            intent.putExtras(params);
        }
        intent.putExtra(BroadcastEx.EXTRA_EVENT_ID, eventId);
        if (receiverId != null)
            intent.putExtra(BroadcastEx.EXTRA_RECEIVER_ID, receiverId);

        // 对stick参数控制
        if (sticky)
            mStickyEventsMap.put(mimeType, intent);

        // 发送广播
        mContext.sendBroadcast(intent);

    }

    /**
     * 取消Sticky
     * @param eventId
     */
    public static void removeSticky(int eventId) {
        removeSticky(eventId, null);
    }

    public synchronized static void removeSticky(int eventId, String receiverId) {
        BroadcastEx.judgeApplication(mContext);
        String mimeType = BroadcastEx.buildMimeType(eventId, receiverId);
        Intent sticky = mStickyEventsMap.remove(mimeType);
        if (sticky != null)
            send(-eventId, receiverId, sticky.getExtras());
    }
}
