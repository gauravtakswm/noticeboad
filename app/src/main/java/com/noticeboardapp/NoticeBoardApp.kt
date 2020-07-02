package com.gauravtak.scheduler_assignment

import android.app.Application
import android.content.Context
import com.noticeboardapp.utils_classes.DummyDataClasses


public class NoticeBoardApp: Application(){
    override fun onCreate() {
        super.onCreate()
        mContext = this
        DummyDataClasses.addDummyNotices()

    }


    companion object {
        public  var  mContext:Context? = null;
     }
}