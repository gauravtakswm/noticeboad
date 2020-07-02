package com.gauravtak.scheduler_assignment.viewmodels


import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.noticeboardapp.model_classes.NoticeDataBean

public class RowNoticesListingVM(val noticeDataBean: NoticeDataBean) : ViewModel() {
    var issuedBy: ObservableField<String>? = ObservableField<String>()

    var desValue: ObservableField<String>? = ObservableField<String>()
    var titleValue: ObservableField<String>? = ObservableField<String>()
    var category: ObservableField<String>? = ObservableField<String>()


    init {
         desValue?.set(noticeDataBean.description)
        titleValue?.set(noticeDataBean.title)
        issuedBy?.set("Issued By:- "+noticeDataBean.issuedBy);
        category?.set("Category:- "+noticeDataBean.category);

    }


}