package com.github.chuross.qiiip.ui.fragment.template

import android.content.Context
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.AbstractTemplate
import com.github.chuross.library.mvp.view.template.ApplicableTemplate
import com.github.chuross.qiiip.domain.tag.Tag
import kotlinx.android.synthetic.template_fragment_tag_screen.view.toolbar
import kotlinx.android.synthetic.template_fragment_tag_screen.view.txt_tag_name

class TagScreenFragmentTemplate(context: Context) : AbstractTemplate(context, R.layout.template_fragment_tag_screen), ApplicableTemplate<Tag> {

    val toolbar = view.toolbar
    val tagNameText = view.txt_tag_name

    override fun apply(tag: Tag) {
        tagNameText.text = tag.identity.value
    }
}