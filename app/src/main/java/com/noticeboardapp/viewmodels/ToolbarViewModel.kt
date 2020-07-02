package com.gauravtak.scheduler_assignment.viewmodels

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gauravtak.scheduler_assignment.NoticeBoardApp
import com.noticeboardapp.db_storage.DatabaseHandler
import com.noticeboardapp.model_classes.NoticeDataBean
import com.noticeboardapp.utils_classes.SingleLiveEvent


public class ToolbarViewModel : ViewModel() {
    var centerTitleValue = ObservableField<String>()
    var getNoticesList2:LiveData<ArrayList<NoticeDataBean>> = MutableLiveData<ArrayList<NoticeDataBean>>();
    var getNoticesList:SingleLiveEvent<ArrayList<NoticeDataBean>> = SingleLiveEvent<ArrayList<NoticeDataBean>>();
    var mActivity: AppCompatActivity? = null

    fun init(mActivity: AppCompatActivity, centerTitle: String) {
        centerTitleValue.set(centerTitle)

        this.mActivity = mActivity;
    }

    public fun getTodaysNoticesApiCall(dateValue: String) {
      val dataBeanArrayList = DatabaseHandler(NoticeBoardApp.mContext).allNotices;
        getNoticesList?.value = dataBeanArrayList;

    }



}