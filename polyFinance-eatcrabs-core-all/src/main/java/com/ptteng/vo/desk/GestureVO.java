package com.ptteng.vo.desk;

import java.io.Serializable;

public class GestureVO implements Serializable {

    private String gesture;

    @Override
    public String toString() {
        return "GestureVO{" +
                "gesture='" + gesture + '\'' +
                '}';
    }

    public String getGesture() {
        return gesture;
    }

    public void setGesture(String gesture) {
        this.gesture = gesture;
    }
}
