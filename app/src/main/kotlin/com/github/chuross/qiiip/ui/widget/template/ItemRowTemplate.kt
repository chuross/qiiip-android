package com.github.chuross.qiiip.ui.widget.template

import android.content.Context
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.AbstractTemplate
import com.github.chuross.library.mvp.view.template.ApplicableTemplate
import com.github.chuross.qiiip.domain.item.Item
import kotlinx.android.synthetic.template_list_row.view.txt_tags
import kotlinx.android.synthetic.template_list_row.view.txt_title
import kotlinx.android.synthetic.template_list_row.view.txt_user_name

class ItemRowTemplate : AbstractTemplate, ApplicableTemplate<Item> {

    val titleText = view.txt_title
    val tagsText = view.txt_tags
    val userNameText = view.txt_user_name

    constructor(context: Context) : super(context, R.layout.template_list_row)

    override fun apply(item: Item?) {
        titleText.text = item?.metaInfo?.title.orEmpty()
        tagsText.text = item?.metaInfo?.tags?.fold("") { current, tag ->
            current.plus(", ${tag.identity.value}")
        }?.substring(2)
        userNameText.text = item?.metaInfo?.user?.identity?.value
    }
}