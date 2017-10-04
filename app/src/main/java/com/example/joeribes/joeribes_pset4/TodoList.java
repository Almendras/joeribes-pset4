package com.example.joeribes.joeribes_pset4;

import java.util.ArrayList;

/**
 * Created by Joeri Bes on 3-10-2017.
 */

public class TodoList {

    private int group;
    private ArrayList<TodoItem> todoItemList;

    public TodoList(int aGroup) {
        this.group = aGroup;
        this.todoItemList = new ArrayList<>();
    }

    public void setGroup(int aGroup) {
        this.group = aGroup;
    }

    public void setTodoItemList(ArrayList<TodoItem> todoItemList) {
        this.todoItemList = todoItemList;
    }

    public int getGroup() {
        return group;
    }

    public ArrayList<TodoItem> getTodoItemList() {
        return todoItemList;
    }

    public void addtodoItem(TodoItem todoItem) {
        todoItemList.add(todoItem);
    }



}
