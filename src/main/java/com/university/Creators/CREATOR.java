package com.university.Creators;


import java.util.List;

public interface CREATOR <K> {

    void create();
    List<K> getData();
}