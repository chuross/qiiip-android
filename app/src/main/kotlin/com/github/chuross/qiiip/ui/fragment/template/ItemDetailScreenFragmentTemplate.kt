package com.github.chuross.qiiip.ui.fragment.template

import android.content.Context
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.AbstractTemplate
import com.github.chuross.library.mvp.view.template.ApplicableTemplate
import com.github.chuross.qiiip.domain.item.Item
import kotlinx.android.synthetic.template_fragment_item_detail_screen.view.tag_group
import kotlinx.android.synthetic.template_fragment_item_detail_screen.view.toolbar
import kotlinx.android.synthetic.template_fragment_item_detail_screen.view.txt_title
import kotlinx.android.synthetic.template_fragment_item_detail_screen.view.webview
import org.jsoup.Jsoup

class ItemDetailScreenFragmentTemplate(context: Context) : AbstractTemplate(context, R.layout.template_fragment_item_detail_screen), ApplicableTemplate<Item> {

    val toolbar = view.toolbar
    val titleText = view.txt_title
    val tagGroup = view.tag_group
    val webView = view.webview

    companion object {
        private val HTML_FILE_PATH = "html/item_detail.html"
    }

    override fun apply(item: Item) {
        titleText.text = item.metaInfo?.title
        tagGroup.setTags(item.metaInfo?.tags?.map { tag -> tag.getIdentity().getValue() })


        webView.loadDataWithBaseURL("file:///android_asset/", view.context.assets.open(HTML_FILE_PATH).reader(Charsets.UTF_8).use { reader ->
            val document = Jsoup.parse(reader.readText())
            document.body().let { element ->
                element?.append(item.metaInfo?.body.orEmpty())
            }
            document.html()
        }, "text/html", Charsets.UTF_8.name(), null)
    }

}