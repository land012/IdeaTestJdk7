package cf.umbrella.netty.timepojo;

import java.util.Date;

/**
 * Created by xudazhou on 2016/10/17.
 */
public class UnixTime {
    private long value;

    public UnixTime() {
        this(System.currentTimeMillis()/1000 + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return this.value;
    }

    @Override
    public String toString() {
        return new Date((this.value - 2208988800L) * 1000).toString();
    }
}
