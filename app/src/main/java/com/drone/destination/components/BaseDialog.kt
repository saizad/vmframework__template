package com.drone.destination.components

import android.content.Context
import androidx.annotation.StyleRes
import com.vm.framework.BaseDialog
import kotlinx.coroutines.Job

abstract class BaseDialog<M, RV>(
    context: Context, layout: Int, @StyleRes styleRes: Int = 0
) : BaseDialog<M, RV>(context, layout, styleRes) {

    private var job: Job? = null

    override fun onDetachedFromWindow() {
        job?.cancel()
        super.onDetachedFromWindow()
    }

}