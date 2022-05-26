package com.example.aidld;

/**
 * create time 2022/5/26 4:48 下午
 * create by 胡汉君
 *实现stub
 */
public class MyImplementor extends IMyAidlInterface.Stub {
    @Override
    public String getMessage() {
        return "hello world this good";
    }

}
