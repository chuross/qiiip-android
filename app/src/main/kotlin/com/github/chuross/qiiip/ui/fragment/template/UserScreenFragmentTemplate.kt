package com.github.chuross.qiiip.ui.fragment.template

import android.content.Context
import android.view.ViewGroup
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.AbstractTemplate
import com.github.chuross.library.mvp.view.template.ApplicableTemplate
import com.github.chuross.qiiip.domain.user.User
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.template_fragment_user_screen.view.*

class UserScreenFragmentTemplate(context: Context, parent: ViewGroup) : AbstractTemplate(context, R.layout.template_fragment_user_screen, parent), ListTemplate, ApplicableTemplate<User> {

    val toolbar = view.toolbar
    val userLayout = view.layout_user
    val userNameText = view.txt_user_name
    val userThumbnailImage = view.img_user_thumbnail
    override val swipeRefreshLayout = view.layout_swipe_refresh
    override val list = view.list
    override val messageView = view.status

    override fun apply(user: User) {
        userNameText.text = user.identity.value
        user.metaInfo?.let { metaInfo ->
            Picasso.with(view.context)
                    .load(metaInfo.profileImageUrl)
                    .fit()
                    .centerCrop()
                    .transform(CropCircleTransformation())
                    .into(userThumbnailImage)
        }
    }

}