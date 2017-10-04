package com.example.joeribes.joeribes_pset4;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;

public class TodoManager extends SQLiteOpenHelper {

    // Initializing values for the database
    private static TodoManager sInstance;

    // Initializing values for the todoItem table
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "activityDB.db";
    private static final String TABLE_TODO = "activityTable";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TODO = "todoName";
    private static final String COLUMN_FINISHED = "finished";
    private static final String COLUMN_DESCRIPTION = "todoDescription";
    private static final String COLUMN_GROUPID = "groupid";

    // Initializing values for the group table
    private static final String TABLE_GROUP = "groupTable";
    private static final String COLUMN_GROUPNAME = "groupName";

    // Initializing values for the todoLists
    private static ArrayList<TodoList> todoListsContainer;

    public void setTodoListsContainer(ArrayList<TodoList> todoListsContainer) {
        TodoManager.todoListsContainer = todoListsContainer;
    }

    public static ArrayList<TodoList> getTodoListsContainer() {
        return todoListsContainer;
    }

    public static void addTodoList(TodoList todoList) {
        todoListsContainer.add(todoList);
    }


    public TodoManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized TodoManager getsInstance(Context context) {
        if(sInstance == null)
            sInstance = new TodoManager(context.getApplicationContext());

        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_TODO + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TODO + " TEXT NOT NULL, " +
                COLUMN_FINISHED + " BOOL NOT NULL, " +
                COLUMN_GROUPID + " INTEGER NOT NULL, " +
                COLUMN_DESCRIPTION + " TEXT NOT NULL)";
        String query2 = "CREATE TABLE " + TABLE_GROUP + "(" +
                COLUMN_GROUPID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_GROUPNAME + " TEXT NOT NULL)";

        db.execSQL(query);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }

    //Add a new row to the database
    public void create(TodoItem todoItem) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TODO, todoItem.get_todoName());
        values.put(COLUMN_DESCRIPTION, todoItem.get_description());
        values.put(COLUMN_FINISHED, todoItem.get_finished());
        values.put(COLUMN_GROUPID, todoItem.get_groupID());
        db.insert(TABLE_TODO, null, values);
        db.close();
    }

    //Delete a todoItem from the database
    public void delete(int todoID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TODO + " WHERE " + COLUMN_ID + "=\"" + todoID + "\";");
    }

    // Read the values from the database and story them in an ArrayList
    public ArrayList<TodoList> read(){
        SQLiteDatabase db = getReadableDatabase();

        todoListsContainer = new ArrayList<TodoList>();

        //TodoList todoList = new TodoList();



        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_TODO + ", " + COLUMN_DESCRIPTION + ", " + COLUMN_FINISHED + ", " + COLUMN_GROUPID + " FROM " + TABLE_TODO;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                String todoName = cursor.getString(cursor.getColumnIndex(COLUMN_TODO));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                int finished = cursor.getInt(cursor.getColumnIndex(COLUMN_FINISHED));
                // int groupID = cursor.getInt(cursor.getColumnIndex(COLUMN_GROUPID));
                int groupID = cursor.getInt(cursor.getColumnIndex(COLUMN_GROUPID));
                TodoItem todoItem = new TodoItem(todoName, description, id, finished, groupID);

                TodoList todoList = new TodoList(groupID);
                todoList.addtodoItem(todoItem);

                boolean check = false;
                for(TodoList todoList1 : todoListsContainer) {
                    int test = todoList1.getGroup();
                    if (test == groupID) {
                        check = true;
                    }
                }

                if (todoListsContainer.isEmpty()) {
                    //TodoList todoList = new TodoList(groupID);
                    //todoList.addtodoItem(todoItem);
                    todoListsContainer.add(todoList);
                } else {
                    if (check) {
                        todoListsContainer.get(groupID).addtodoItem(todoItem);
                    } else {
                        //TodoList todoList = new TodoList(groupID);
                        //todoList.addtodoItem(todoItem);
                        todoListsContainer.add(todoList);
                    }
                }


            }

            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return todoListsContainer;

    }

    // Delete values from the database which match the id
    public TodoItem readDescription(int activity_id){
        SQLiteDatabase db = getReadableDatabase();

        TodoItem todoItem = null;

        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_TODO + ", " + COLUMN_DESCRIPTION + ", " + COLUMN_FINISHED + ", " + COLUMN_GROUPID + " FROM " + TABLE_TODO + " WHERE " + COLUMN_ID + " = " + activity_id;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                String todoName = cursor.getString(cursor.getColumnIndex(COLUMN_TODO));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                int finished = cursor.getInt(cursor.getColumnIndex(COLUMN_FINISHED));
                int groupID = cursor.getInt(cursor.getColumnIndex(COLUMN_GROUPID));
                todoItem = new TodoItem(todoName, description, id, finished, groupID);
            }

            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return todoItem;
    }

    // Update the database with new values
    public int update(TodoItem todoItem) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TODO, todoItem.get_todoName());
        values.put(COLUMN_DESCRIPTION, todoItem.get_description());
        values.put(COLUMN_FINISHED, todoItem.get_finished());
        values.put(COLUMN_GROUPID, todoItem.get_groupID());
        return db.update(TABLE_TODO, values, COLUMN_ID + " = ? ", new String[] { String.valueOf(todoItem.get_id()) });

    }



}