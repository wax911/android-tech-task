package io.wax911.challenge.common.widget.card

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntRange
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import io.wax911.challenge.common.widget.databinding.StatusCardWidgetBinding
import io.wax911.challenge.core.extensions.layoutInflater

class StatusCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val statusCardWidgetBinding by lazy(LazyThreadSafetyMode.NONE) {
        StatusCardWidgetBinding.inflate(
            context.layoutInflater(),
            this,
            true
        )
    }

    /**
     * Could have been a model if we had more time
     */
    fun setUpUsing(
        @DrawableRes icon: Int,
        title: String, subTitle:
        String,
        @IntRange(from=0, to=100)
        progress: Int
    ) {
        statusCardWidgetBinding.statusIcon.setImageDrawable(
            ContextCompat.getDrawable(context, icon)
        )
        statusCardWidgetBinding.statusTitle.text = title
        statusCardWidgetBinding.statusSubTitle.text = subTitle
        statusCardWidgetBinding.statusProgress.setProgressCompat(progress, true)

    }
}