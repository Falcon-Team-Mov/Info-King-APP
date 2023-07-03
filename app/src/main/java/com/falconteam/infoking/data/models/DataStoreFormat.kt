package com.falconteam.infoking.data.models

import androidx.datastore.preferences.core.Preferences

data class DataStoreFormat(
    val key: Preferences.Key<String>,
    val value: String
)
