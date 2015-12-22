package sabria.nobroadcast.library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import java.util.Random;

import sabria.nobroadcast.library.util.BroadcastCx;

/**
 * Created by xiongwei & lele,An Android project Engineer.
 * Date:2015-08-07  15:11
 * Base on Meilimei.com (PHP Service)
 * Describe: 辅助类
 * Version:1.0
 * Open source
 */
public class BroadcastEx {



    public static final String EXTRA_EVENT_ID = BroadcastCx.EVENT_ID + getRandomNum();
    public static final String EXTRA_RECEIVER_ID = BroadcastCx.RECEIVER_ID + getRandomNum();
    public static final String BASE_MIME_TYPE = BroadcastCx.MIME_TYPE + getRandomNum();
    private static final String EVENT_MIME_TYPE_ = BASE_MIME_TYPE + BroadcastCx.MIME_EVENT;
    private static String PACKAGE_NAME = BroadcastCx.DEFALUT_PACKAGE;

    public interface EventsListener {
        /**
         * @param eventId
         * @param params
         * @param isBroadcasted Whether this event was send directly to this listener or broadcasted for all.
         */
        void onEvent(int eventId, Bundle params, boolean isBroadcasted);

    }


    /**
     * 广播接受
     */
    public static class EventsReceiver extends BroadcastReceiver {

        public String mReceiverId;
        public EventsListener mListener;

        @Override
        public final void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(EXTRA_EVENT_ID)) {
                Bundle params = intent.getExtras();
                String targetReceiverId = params.getString(EXTRA_RECEIVER_ID);

                if (targetReceiverId == null || targetReceiverId.equals(mReceiverId)) {
                    mListener.onEvent(params.getInt(EXTRA_EVENT_ID), params, targetReceiverId == null);
                }
            }
        }

    }

    public static void judgeApplication(Context mContext) {
        if (mContext == null)
            throw new RuntimeException("BroadcastEvent was not initialized with init(Context) method");
    }

    public static void judgeBroad(Context mContext, EventsListener listener) {
        if (mContext == null)
            throw new RuntimeException("BroadcastEvent was not initialized with init(Context) method");
        if (listener == null) throw new NullPointerException("Listener cannot be null");
    }

    public static String buildMimeType(int eventId, String receiverId) {
        return EVENT_MIME_TYPE_ + eventId + (receiverId == null ? "" : BroadcastCx.UNDERSCODE + receiverId);
    }


    public static String getRandomNum(Context context) {
        PACKAGE_NAME = context.getPackageName();
        return getPrefix() + context.getPackageName();
    }

    public static String getRandomNum() {
        return getPrefix() + PACKAGE_NAME;
    }

    private static String getPrefix() {
        return BroadcastCx.PREFIX + getRan() + BroadcastCx.UNDERSCODE;
    }

    private static String getRan() {
        return String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
    }
}
