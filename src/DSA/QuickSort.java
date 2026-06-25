package DSA;

import java.util.ArrayList;
import Service.TrafficData;

public class QuickSort {

    public static void sort(ArrayList<TrafficData> list,
                            int low,
                            int high) {

        if(low < high) {

            int pivot =
                    partition(list,
                              low,
                              high);

            sort(list,
                 low,
                 pivot - 1);

            sort(list,
                 pivot + 1,
                 high);
        }
    }

    private static int partition(
            ArrayList<TrafficData> list,
            int low,
            int high) {

        int pivot =
                (int) list.get(high)
		    .getTrafficVolume();

        int i = low - 1;

        for(int j = low;
            j < high;
            j++) {

            if(list.get(j)
                   .getTrafficVolume()
                   <= pivot) {

                i++;

                TrafficData temp =
                        list.get(i);

                list.set(i,
                         list.get(j));

                list.set(j,
                         temp);
            }
        }

        TrafficData temp =
                list.get(i + 1);

        list.set(i + 1,
                 list.get(high));

        list.set(high,
                 temp);

        return i + 1;
    }
}