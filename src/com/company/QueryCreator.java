package com.company;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khubaib CH on 8/5/2017.
 */
public class QueryCreator {
    private List<String> queryList = new ArrayList<String>();

    public void Create(HomeTable row) {
        queryList.add("");
    }

    public void execute() {
        //PreparedStatement
    }
}
