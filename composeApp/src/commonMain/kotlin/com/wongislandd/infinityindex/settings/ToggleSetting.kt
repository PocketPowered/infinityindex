package com.wongislandd.infinityindex.settings

enum class ToggleSetting(
    override val displayName: String,
    override val description: String,
    override val key: String,
    override val defaultValue: Boolean
) : Setting<Boolean> {
    VARIANTS(
        "Display Variants",
        "Variants are copies of a comic, with different cover art.",
        "variants",
        false
    ),
    DIGITALLY_AVAILABLE(
        "Limit Results to Digitally Available",
        "Enabling this will limit comic results to those which are available to read online.",
        "digitallyAvailable",
        false
    )
}