package com.github.chuross.qiiip.ui.widget.template

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.AbstractTemplate
import com.github.chuross.library.mvp.view.template.ApplicableTemplate
import com.github.chuross.qiiip.domain.user.User
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class NavigationHeaderTemplate(view: View) : AbstractTemplate(view), ApplicableTemplate<User> {

    val profileImage: ImageView by lazy { view.findViewById(R.id.image_profile) as ImageView }

    val profileText by lazy { view.findViewById(R.id.txt_profile) as TextView }

    val loginButton by lazy { view.findViewById(R.id.btn_login) as Button }

    override fun apply(user: User?) {
        user?.metaInfo?.let {
            Picasso.with(view.context)
                    .load(it.profileImageUrl)
                    .fit()
                    .centerCrop()
                    .transform(CropCircleTransformation())
                    .into(profileImage)
        }
        profileText.text = user?.identity?.value ?: "guest"
        loginButton.visibility = if (user == null) View.VISIBLE else View.INVISIBLE
    }
}