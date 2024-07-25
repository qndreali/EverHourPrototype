package com.mobdeve.s11.santos.andreali.everhourprototype

data class TimeEntryModel(
    var entryName: String? = null,
    var entryBillable: Boolean? = null,
    var entryRate: Double? = null,
    var entryHrs: Int? = null
)
