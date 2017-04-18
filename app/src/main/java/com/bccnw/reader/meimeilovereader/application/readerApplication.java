package com.bccnw.reader.meimeilovereader.application;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

/**
 * Created by ASUS on 2017/4/12.
 */
public class readerApplication extends Application {
    private static readerApplication cloudReaderApplication;

    public static readerApplication getInstance() {
        return cloudReaderApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        cloudReaderApplication = this;
        initTextSize();
    }

    /**
     * 使其系统更改字体大小无效
     */
    private void initTextSize() {
        Resources res = getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

}
