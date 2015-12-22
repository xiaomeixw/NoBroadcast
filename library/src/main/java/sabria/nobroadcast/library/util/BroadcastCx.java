package sabria.nobroadcast.library.util;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-22  18:58
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class BroadcastCx {

    public enum DEF_EVENT_ID {
        Cx_Defalut("Cx_Defalut",Integer.MAX_VALUE), Cx_0x0001("Cx_0x0001", 0x0001A), Cx_0x0002("Cx_0x0002", 0x0002A), Cx_0x0003("Cx_0x0003", 0x0003A);

        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private DEF_EVENT_ID(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }
    }



    public static final class  EVENT_OTHER_ID{

        public static final int Cx_Defalut = Integer.MAX_VALUE;

        public static final int Cx_0x0001 = 0x0001A;

        public static final int Cx_0x0002 = 0x0002A;

        public static final int Cx_0x0003 = 0x0003A;

        public static final int Cx_0x0004 = 0x0004A;

        public static final int Cx_0x0005 = 0x0005A;

        public static final int Cx_0x0006 = 0x0006A;

        public static final int Cx_0x0007 = 0x0007A;

        public static final int Cx_0x0008 = 0x0008A;

        public static final int Cx_0x0009 = 0x0009A;

        public static final int Cx_0x0010 = 0x0010A;

        public static final int Cx_0x0011 = 0x0011A;

        public static final int Cx_0x0012 = 0x0012A;

        public static final int Cx_0x0013 = 0x0013A;

        public static final int Cx_0x0014 = 0x0014A;
    }








    public static final String UNDERSCODE="_";

    public static final String PREFIX="com.lib.beauty_";

    public static final  String EVENT_ID ="extra_event_id_";

    public static final  String RECEIVER_ID ="extra_receiver_id_";

    public static final  String MIME_TYPE ="com.lib.beauty.type.";

    public static final  String MIME_EVENT ="/event_";

    public static final  String DEFALUT_PACKAGE ="default.package.name";
}
