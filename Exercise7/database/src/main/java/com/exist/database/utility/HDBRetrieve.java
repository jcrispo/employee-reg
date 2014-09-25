package com.exist.database.utility;

import java.util.List;

public interface HDBRetrieve {

    List<Object[]> retrieveObjectArray(String query, int beginIndex, int maxResult);
    List<Object> retrieveObject(String query, int beginIndex, int maxResult);
}
