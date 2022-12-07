package com.eduauraa.teacher.components

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.CallSuper
import androidx.annotation.StyleRes
import com.vm.framework.BaseBottomSheetDialog
import kotlinx.coroutines.Job

abstract class EduauraaTeacherBottomSheetDialog<M, RV>(
    context: Context, layout: Int, @StyleRes styleRes: Int = 0
) : BaseBottomSheetDialog<M, RV>(context, layout, styleRes) {

    private var job: Job? = null

    @SuppressLint("RestrictedApi")
    @CallSuper
    override fun onShow() {
        super.onShow()
        behavior.disableShapeAnimations()
    }

    override fun onDetachedFromWindow() {
        job?.cancel()
        super.onDetachedFromWindow()
    }

}