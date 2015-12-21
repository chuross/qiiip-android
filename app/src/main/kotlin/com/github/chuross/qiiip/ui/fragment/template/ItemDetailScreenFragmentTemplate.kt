package com.github.chuross.qiiip.ui.fragment.template

import android.content.Context
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.NonApplyTemplate
import com.github.chuross.library.mvp.view.template.SourceTemplate
import com.github.chuross.qiiip.domain.item.Item
import kotlinx.android.synthetic.template_fragment_item_detail_screen.view.tag_group
import kotlinx.android.synthetic.template_fragment_item_detail_screen.view.toolbar
import kotlinx.android.synthetic.template_fragment_item_detail_screen.view.txt_title

class ItemDetailScreenFragmentTemplate(context: Context) : NonApplyTemplate(context, R.layout.template_fragment_item_detail_screen), SourceTemplate<Item> {

    val toolbar = view.toolbar
    val titleText = view.txt_title
    val tagGroup = view.tag_group

    override fun apply(item: Item) {
        titleText.text = item.metaInfo?.title

        item.metaInfo?.tags?.isNotEmpty().run {
            tagGroup.setTags(item.metaInfo?.tags?.map { tag -> tag.getIdentity().getValue() })
        }
    }

}