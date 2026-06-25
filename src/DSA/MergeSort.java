package DSA;

import java.util.ArrayList;
import Service.TrafficData;

public class MergeSort {

    public static void sort(
            ArrayList<TrafficData> list,
            int left,
            int right) {

        if(left < right) {

            int mid =
                    (left + right) / 2;

            sort(list,
                 left,
                 mid);

            sort(list,
                 mid + 1,
                 right);

            merge(list,
                  left,
                  mid,
                  right);
        }
    }

    private static void merge(
            ArrayList<TrafficData> list,
            int left,
            int mid,
            int right) {

        ArrayList<TrafficData> temp =
                new ArrayList<>();

        int i = left;
        int j = mid + 1;

        while(i <= mid &&
              j <= right) {

            if(list.get(i)
                   .getAverageSpeed()
                   <=
               list.get(j)
                   .getAverageSpeed()) {

                temp.add(
                        list.get(i++));

            } else {

                temp.add(
                        list.get(j++));
            }
        }

        while(i <= mid) {

            temp.add(
                    list.get(i++));
        }

        while(j <= right) {

            temp.add(
                    list.get(j++));
        }

        for(int k = 0;
            k < temp.size();
            k++) {

            list.set(left + k,
                     temp.get(k));
        }
    }
}