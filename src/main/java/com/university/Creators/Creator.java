package com.university.Creators;


import java.util.List;

public interface Creator<K> {

    void create();
    List<K> getData();
}