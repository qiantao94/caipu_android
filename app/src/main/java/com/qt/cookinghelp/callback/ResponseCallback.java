package com.qt.cookinghelp.callback;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by qiantao on 2016/6/24.
 */
public abstract class ResponseCallback implements Callback {

    private static final int CALLBACK_SUCCEED = 000;

    private static final int CALLBACK_FAILED = 001;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CALLBACK_FAILED:
                    onFailed((Exception) msg.obj);
                    break;
                case CALLBACK_SUCCEED:
                    onSucceed((String) msg.obj);
                    break;
            }

        }
    };

    @Override
    public void onFailure(Call call, IOException e) {
        Message msg = Message.obtain();
        msg.what = CALLBACK_FAILED;
        msg.obj = e;
        handler.sendMessage(msg);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        Message msg = Message.obtain();
        if(response.isSuccessful()){
            msg.what = CALLBACK_SUCCEED;
            msg.obj = response.body().string();
        } else {
            msg.what = CALLBACK_FAILED;
        }
        handler.sendMessage(msg);
    }

    public abstract void onFailed(Exception e);

    public abstract void onSucceed(String json);
}
