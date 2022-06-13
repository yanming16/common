package com.xiaoming.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author xiaoming
 * @date 2020-01-01
 */
public class MinMeetingRooms {
    public int minMeetingRooms(List<Interval> intervals) {
        //Sorting the interval list based on meeting start time.
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval x, Interval y) {
                return x.start - y.start;
            }
        });

        List<Integer> rooms = new ArrayList<Integer>();

        boolean flag = false;
        for (Interval x : intervals) {
            //for each meeting check all the rooms. if any room is avaiable then update the meeting end time in that room.
            for (int i = 0; i < rooms.size(); i++) {
                if (x.start > rooms.get(i)) {
                    rooms.set(i, x.end);
                    flag = true;
                    break;
                }
            }

            // rooms were not available then add a new room.
            if (!flag) {
                rooms.add(x.end);
            }
            flag = false;
        }

        return rooms.size();
    }


    public class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
