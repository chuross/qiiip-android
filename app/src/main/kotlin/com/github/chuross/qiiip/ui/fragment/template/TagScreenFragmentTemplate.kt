package com.github.chuross.qiiip.ui.fragment.template

import android.content.Context
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.AbstractTemplate
import com.github.chuross.library.mvp.view.template.ApplicableTemplate
import com.github.chuross.qiiip.domain.tag.Tag
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.template_fragment_tag_screen.view.img_tag_thumbnail
import kotlinx.android.synthetic.template_fragment_tag_screen.view.toolbar
import kotlinx.android.synthetic.template_fragment_tag_screen.view.txt_tag_name

class TagScreenFragmentTemplate(context: Context) : AbstractTemplate(context, R.layout.template_fragment_tag_screen), ApplicableTemplate<Tag> {

    val toolbar = view.toolbar
    val tagNameText = view.txt_tag_name
    val tagThumbnailImage = view.img_tag_thumbnail

    override fun apply(tag: Tag) {
        tag.metaInfo?.let { metaInfo ->
            Picasso.with(view.context)
                    .load(metaInfo.iconUrl)
                    .fit()
                    .centerCrop()
                    .transform(CropCircleTransformation())
                    .into(tagThumbnailImage)
        }
        tagNameText.text = tag.identity.value
    }
}