package com.olabode.wilson.statussaver.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *   Created by OLABODE WILSON on 2020-01-10.
 */

@Parcelize
data class Status(
    var id: String,
    var path: String
) : Parcelable