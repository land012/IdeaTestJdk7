package com.umbrella.vo;

/**
 * Created by 大洲 on 15-4-23.
 */
public class Passenger extends User {
    private long ticketNo;

    public long getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(long ticketNo) {
        this.ticketNo = ticketNo;
    }
}
