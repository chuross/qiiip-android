package com.github.chuross.qiiip.ui.widget.template

import android.content.Context
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.AbstractTemplate
import com.github.chuross.library.mvp.view.template.ApplicableTemplate
import com.github.chuross.qiiip.domain.item.Item
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.template_list_row.view.img_thumbnail
import kotlinx.android.synthetic.template_list_row.view.txt_tags
import kotlinx.android.synthetic.template_list_row.view.txt_title

class ItemRowTemplate : AbstractTemplate, ApplicableTemplate<Item> {

    val thumbnailImage = view.img_thumbnail
    val titleText = view.txt_title
    val tagsText = view.txt_tags

    constructor(context: Context) : super(context, R.layout.template_list_row)

    override fun apply(item: Item?) {
        item?.metaInfo?.user?.metaInfo?.profileImageUrl.let { imageUrl ->
            Picasso.with(view.context)
                    .load(imageUrl)
                    .fit()
                    .centerCrop()
                    .transform(CropCircleTransformation())
                    .into(thumbnailImage)
        }

        titleText.text = item?.metaInfo?.title.orEmpty()
        tagsText.text = item?.metaInfo?.tags?.fold("") { current, tag ->
            current.plus(", ${tag.identity.value}")
        }?.substring(2)
    }
}