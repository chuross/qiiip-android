package com.github.chuross.qiiip.ui.fragment.template

import android.content.Context
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.AbstractTemplate
import com.github.chuross.library.mvp.view.template.SourceTemplate
import com.github.chuross.qiiip.domain.item.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.template_item_row.view.img_thumbnail
import kotlinx.android.synthetic.template_item_row.view.txt_title

class ItemRowTemplate : AbstractTemplate, SourceTemplate<Item> {

    val thumbnailImage = view.img_thumbnail
    val titleText = view.txt_title

    constructor(context: Context) : super(context, R.layout.template_list_row)

    override fun apply() {
    }

    override fun apply(item: Item?) {
        item?.metaInfo?.user?.metaInfo?.profileImageUrl.let { imageUrl ->
            Picasso.with(view.context)
                    .load(imageUrl)
                    .fit()
                    .centerCrop()
                    .into(thumbnailImage)
        }

        titleText.text = item?.metaInfo?.title.orEmpty()
    }
}