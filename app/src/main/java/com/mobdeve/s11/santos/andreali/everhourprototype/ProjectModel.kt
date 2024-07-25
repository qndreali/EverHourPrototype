package com.mobdeve.s11.santos.andreali.everhourprototype

data class ProjectModel (
    var projectName: String? = null,
    var projectClient: String? = null,
    var projectHrs: Int? = null,
    var projectCount: Int? = null,
    var projectEntries: ArrayList<TimeEntryModel>? = null
) {

}