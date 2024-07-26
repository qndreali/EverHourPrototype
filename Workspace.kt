package com.mobdeve.s11.santos.andreali.everhourprototype

import java.io.Serializable

class Workspace(var workspaceID: Int, var name: String, var hours: Int, var projects: Int) : Serializable {
    companion object {
        private const val DEFAULT_ID = -1
    }

    constructor(name: String, hours: Int, projects: Int) : this(DEFAULT_ID, name, hours, projects)

    // Default constructor
    constructor() : this(DEFAULT_ID, "Blank", 0, 0)
}
