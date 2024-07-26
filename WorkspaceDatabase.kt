package com.mobdeve.s11.santos.andreali.everhourprototype

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.util.concurrent.Executors

class WorkspaceDatabase(context: Context) {
    private lateinit var databaseHelper: DatabaseHelper
    private val executorService = Executors.newSingleThreadExecutor()

    init {
        this.databaseHelper = DatabaseHelper(context)
    }

    fun addWorkspace(workspace: Workspace): Int {
        val db = databaseHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(DatabaseHelper.WORKSPACE_NAME, workspace.name)
            put(DatabaseHelper.WORKSPACE_HOURS, workspace.hours)
            put(DatabaseHelper.WORKSPACE_PROJECTS, workspace.projects)
        }
        val id = db.insert(DatabaseHelper.WORKSPACE_TABLE, null, contentValues)
        db.close()
        return id.toInt()
    }

    fun updateWorkspace(workspace: Workspace) {
        val db = databaseHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(DatabaseHelper.WORKSPACE_NAME, workspace.name)
            put(DatabaseHelper.WORKSPACE_HOURS, workspace.hours)
            put(DatabaseHelper.WORKSPACE_PROJECTS, workspace.projects)
        }
        db.update(DatabaseHelper.WORKSPACE_TABLE, contentValues, "${DatabaseHelper.WORKSPACE_ID}=?", arrayOf(workspace.workspaceID.toString()))
        db.close()
    }

    fun deleteWorkspace(workspace: Workspace) {
        val db = databaseHelper.writableDatabase
        db.delete(DatabaseHelper.WORKSPACE_TABLE, "${DatabaseHelper.WORKSPACE_ID}=?", arrayOf(workspace.workspaceID.toString()))
        db.close()
    }

    fun getWorkspace(): ArrayList<Workspace> {
        val result = ArrayList<Workspace>()
        val db = databaseHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.WORKSPACE_TABLE, null, null, null,
            null, null, "${DatabaseHelper.WORKSPACE_ID} ASC, ${DatabaseHelper.WORKSPACE_NAME} ASC"
        )

        while (cursor.moveToNext()) {
            result.add(Workspace(
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.WORKSPACE_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.WORKSPACE_NAME)),
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.WORKSPACE_HOURS)),
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.WORKSPACE_PROJECTS))
            ))
        }
        cursor.close()
        db.close()
        return result
    }

    // Methods for managing members

    fun addMember(member: Member) {
        val db = databaseHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(DatabaseHelper.WORKSPACE_ID, member.workspaceId)
            put(DatabaseHelper.MEMBER_NAME, member.name)
            put(DatabaseHelper.MEMBER_ROLE, member.role)
        }
        db.insert(DatabaseHelper.MEMBERS_TABLE, null, contentValues)
        db.close()
    }

    fun updateMember(member: Member) {
        val db = databaseHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(DatabaseHelper.MEMBER_NAME, member.name)
            put(DatabaseHelper.MEMBER_ROLE, member.role)
        }
        db.update(DatabaseHelper.MEMBERS_TABLE, contentValues, "${DatabaseHelper.MEMBER_ID}=?", arrayOf(member.memberId.toString()))
        db.close()
    }

    fun deleteMember(member: Member) {
        val db = databaseHelper.writableDatabase
        db.delete(DatabaseHelper.MEMBERS_TABLE, "${DatabaseHelper.MEMBER_ID}=?", arrayOf(member.memberId.toString()))
        db.close()
    }

    fun getMembers(workspaceId: Int): List<Member> {
        val result = ArrayList<Member>()
        val db = databaseHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.MEMBERS_TABLE,
            null,
            "${DatabaseHelper.WORKSPACE_ID}=?",
            arrayOf(workspaceId.toString()),
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            result.add(Member(
                workspaceId,
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.MEMBER_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MEMBER_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MEMBER_ROLE))
            ))
        }
        cursor.close()
        db.close()
        return result
    }
}
