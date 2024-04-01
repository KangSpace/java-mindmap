package org.kangspace.common.clazz;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangxuefeng
 * @since 2022/8/23
 */
public class OOMObject {
    private int[] arr = new int[1024];

    public static void main(String[] args) {
        List<OOMObject> oomObjectList = new ArrayList<>();
        while (true) {
            oomObjectList.add(new OOMObject());
        }
    }
}
