/*
 * Copyright 2017-2019 Bak Jaeon (finalchild) and Ranol
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.finalchild.kotlinbukkit.updater

import com.google.gson.JsonParser
import java.net.URL
import java.util.*
import javax.net.ssl.HttpsURLConnection

private val LATEST_RELEASE_URL = URL("https://api.github.com/repos/finalchild/kotlinbukkit/releases/latest")

fun getLatestRelease(): ReleaseInfo? {
    val connection = LATEST_RELEASE_URL.openConnection() as HttpsURLConnection
    connection.requestMethod = "GET"
    connection.setRequestProperty("Accept", "application/vnd.github.v3+json")
    connection.setRequestProperty("User-Agent", "kotlinbukkit")
    connection.setRequestProperty("Time-Zone", TimeZone.getDefault().id)
    connection.instanceFollowRedirects = true

    return when (connection.responseCode) {
        HttpsURLConnection.HTTP_OK -> {
            val reader = connection.inputStream.reader().buffered()
            val o = JsonParser().parse(reader).asJsonObject
            ReleaseInfo(o.get("html_url").asString, o.getAsJsonPrimitive("tag_name").asString)
        }
        else -> null
    }
}
