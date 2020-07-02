package com.gauravtak.scheduler_assignment.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.gauravtak.scheduler_assignment.holders.NoticesAdapter
import com.gauravtak.scheduler_assignment.utils_classes.TimeUtilsHelper.Companion.getTodayDate
import com.gauravtak.scheduler_assignment.viewmodels.ToolbarViewModel
import com.noticeboardapp.R
import com.noticeboardapp.databinding.ActivityNoticesListingBinding
import com.noticeboardapp.db_storage.DatabaseHandler
import com.noticeboardapp.model_classes.NoticeDataBean
import com.noticeboardapp.utils_classes.CustomProgressDialog
import java.util.*
import kotlin.collections.ArrayList

class NoticesListingActivity : AppCompatActivity() {
    var activityNoticesListingBinding:ActivityNoticesListingBinding? = null
    private val mActivity: AppCompatActivity = this
    var dataBeanArrayList = ArrayList<NoticeDataBean>()
    private var meetingListAdapter: NoticesAdapter? = null
    private var toolbarViewModel:ToolbarViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNoticesListingBinding = DataBindingUtil.setContentView(this, R.layout.activity_notices_listing)
        //   activityMeetingsListingBinding.setViewModel(new LoginViewModel());
        activityNoticesListingBinding?.executePendingBindings()
        setToolbarInit()

        setUpRecyclerView()
        getDataFromDbStorage();


     }

    private fun setUpRecyclerView() {
        meetingListAdapter = NoticesAdapter(mActivity, dataBeanArrayList)
        activityNoticesListingBinding!!.recyclerViewMeetings.adapter = meetingListAdapter

    }

    private fun getDataFromDbStorage()
    {
        //we are fetching the data in ViewModel( in dependent of Activity Life Cycle) which is not main thread so it will increase the performance of app
        CustomProgressDialog.showProgress(this);

            toolbarViewModel?.getTodaysNoticesApiCall(getTodayDate());
        //because of this, We can reduce the memory leak issues;
           
    }

    private fun setToolbarInit() {
         toolbarViewModel = ViewModelProviders.of(this).get(ToolbarViewModel::class.java)
        activityNoticesListingBinding!!.toolbarViewModel = toolbarViewModel
        toolbarViewModel?.init(mActivity, "Notice:- "+getTodayDate());
        toolbarViewModel?.getTodaysNoticesApiCall(getTodayDate())
       toolbarViewModel?.getNoticesList!!.observe(this, androidx.lifecycle.Observer<Any?> {
            obj ->
           meetingListAdapter!!.setData(obj as ArrayList<NoticeDataBean>)
           CustomProgressDialog.hideprogressbar()
        })


    }

}
