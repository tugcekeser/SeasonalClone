package com.example.rabia.seasonalclone

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun Context.inflate(@LayoutRes layoutRes: Int,parent:ViewGroup, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this).inflate(layoutRes, parent, attachToRoot)
}