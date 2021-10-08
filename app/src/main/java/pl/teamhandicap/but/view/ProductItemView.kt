package pl.teamhandicap.but.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_product_item.view.*
import pl.teamhandicap.but.R

class ProductItemView(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    private var iconDrawable: Drawable?
    private var labelText: CharSequence?

    init {
        inflate(context, R.layout.view_product_item, this)

        context
            .obtainStyledAttributes(attrs, R.styleable.ProductItemView, 0, 0)
            .apply {
                try {
                    iconDrawable = getDrawable(R.styleable.ProductItemView_icon)
                    labelText = getText(R.styleable.ProductItemView_label)
                } finally {
                    recycle()
                }
            }

        icon.setImageDrawable(iconDrawable)
        label.text = labelText
    }
}