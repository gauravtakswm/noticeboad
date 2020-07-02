package com.gauravtak.scheduler_assignment.viewmodels

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel


public class ToolbarViewModel : ViewModel() {
    var centerTitleValue = ObservableField<String>()

    var mActivity: AppCompatActivity? = null

    fun init(mActivity: AppCompatActivity, centerTitle: String) {
        centerTitleValue.set(centerTitle)

        this.mActivity = mActivity;
    }

    public fun getTodaysNoticesApiCall(dateValue: String) {

    }



}