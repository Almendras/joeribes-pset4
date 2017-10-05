package com.example.joeribes.joeribes_pset4;

import java.util.ArrayList;

/**
 * Created by Joeri Bes on 3-10-2017.
 */

public class TodoList {

    private String group;
    private ArrayList<TodoItem> todoItemList;

    public TodoList(String aGroup) {
        this.group = aGroup;
        this.todoItemList = new ArrayList<>();
    }

    public void setGroup(String aGroup) {
        this.group = aGroup;
    }

    public void setTodoItemList(ArrayList<TodoItem> todoItemList) {
        this.todoItemList = todoItemList;
    }

    public String getGroup() {
        return group;
    }

    public ArrayList<TodoItem> getTodoItemList() {
        return todoItemList;
    }

    public void addtodoItem(TodoItem todoItem) {
        todoItemList.add(todoItem);
    }



}
