package com.example.srk.openmoviedatabase.utils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.ListUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Raghu on 10/31/2016.
 */
public class ListUtilsTest {

    @Test
    public void testNullList() {
        assertTrue(ListUtils.isEmpty(null));
    }

    @Test
    public void testEmptyList() {
        assertTrue(ListUtils.isEmpty(new ArrayList()));
    }

    @Test
    public void testNonEmptyList() {
        List list = new ArrayList();
        list.add("String");
        assertFalse(ListUtils.isEmpty(list));
    }

}