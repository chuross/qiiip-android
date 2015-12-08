package com.github.chuross.qiiip.ui.fragment.template

import android.content.Context
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.AbstractTemplate
import com.github.chuross.library.mvp.view.template.SourceTemplate
import com.github.chuross.qiiip.domain.item.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.template_item_row.view.img_tag
import kotlinx.android.synthetic.template_item_row.view.txt_title

class ItemRowTemplate : AbstractTemplate, SourceTemplate<Item> {

    val tagImage = view.img_tag
    val titleText = view.txt_title

    constructor(context: Context) : super(context, R.layout.template_item_row)

    override fun apply() {
    }

    override fun apply(item: Item?) {
        item?.metaInfo?.tags?.elementAtOrNull(0).let { tag ->
            Picasso.with(view.context)
                    .load(tag?.metaInfo?.iconUrl)
                    .fit()
                    .centerCrop()
                    .into(tagImage)
        };

        titleText.text = item?.metaInfo?.title.orEmpty()
    }
}