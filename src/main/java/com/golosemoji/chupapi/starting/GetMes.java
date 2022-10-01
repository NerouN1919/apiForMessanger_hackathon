package com.golosemoji.chupapi.starting;

public class GetMes {
    private int from;
    private int to;

    public GetMes(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public GetMes() {
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
