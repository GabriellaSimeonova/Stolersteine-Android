package com.example.stolpersteine

import android.app.Application
import android.content.Context
import com.akexorcist.localizationactivity.ui.LocalizationApplication
import com.yariksoffice.lingver.Lingver
import java.util.*

class StolpersteineApp : LocalizationApplication() {
    override fun getDefaultLanguage(context: Context): Locale {
        return Locale.ENGLISH
    }




}