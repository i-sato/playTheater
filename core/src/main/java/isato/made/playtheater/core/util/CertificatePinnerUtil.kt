/*
 * PlayTheater.core
 * CertificatePinnerUtil.kt
 * Created by Isato on 19/12/2021 9:45:52
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

@file:Suppress("SpellCheckingInspection")

package isato.made.playtheater.core.util

import okhttp3.CertificatePinner

object CertificatePinnerUtil {

    fun createCertificatePinner(): CertificatePinner {
        val hostName = "api.themoviedb.org"
        return CertificatePinner.Builder()
            .add(hostName, "sha256/oD/WAoRPvbez1Y2dfYfuo4yujAcYHXdv1Ivb2v2MOKk=")
            .add(hostName, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostName, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()
    }

}