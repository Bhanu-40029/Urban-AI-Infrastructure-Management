package DSA;

import java.util.ArrayList;
import Service.TrafficData;

public class HeapSort {

    public static void sort(
            ArrayList<TrafficData> list) {

        int n = list.size();

        for(int i = n/2 - 1; i >= 0; i--) {
            heapify(list,n, i);
        }

        for(int i = n-1; i > 0; i--) {

            TrafficData temp = list.get(0);

            list.set(0,list.get(i));

            list.set(i,temp);

            heapify(list,i,0);
        }
    }

    private static void heapify(
            ArrayList<TrafficData> list,
            int n,
            int i) {

        int largest = i;

        int left = 2*i + 1;

        int right = 2*i + 2;

        if(left < n &&
           list.get(left).getIncidentReports()>
           list.get(largest).getIncidentReports()) {

            largest = left;
        }

        if(right < n &&
           list.get(right).getIncidentReports()>
           list.get(largest).getIncidentReports()) {

            largest = right;
        }

        if(largest != i) {

            TrafficData temp =list.get(i);

            list.set(i,list.get(largest));

            list.set(largest,temp);

            heapify(list,n,largest);
        }
    }
}