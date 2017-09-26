package com.zy.model.custom;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/20.
 */
public class Message {
    private int flag;

    private String message;

    private Map<Object,Object> map;


    public Message(){
        this.flag = 1;
        this.message = "操作成功";
    }



    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    public Message(int flag, String message){
        this.flag = flag;
        this.message = message;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
