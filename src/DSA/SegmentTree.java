package DSA;

public class SegmentTree {

    private int[] tree;
    private int n;

    public SegmentTree(int[] arr) {

        n = arr.length;

        tree = new int[4 * n];

        build(arr, 0, n - 1, 1);
    }

    private void build(int[] arr,
                       int start,
                       int end,
                       int node) {

        if(start == end) {

            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;

        build(arr,
              start,
              mid,
              2 * node);

        build(arr,
              mid + 1,
              end,
              2 * node + 1);

        tree[node] =
                tree[2 * node]
                +
                tree[2 * node + 1];
    }

    public int query(int left,
                     int right) {

        return queryHelper(
                0,
                n - 1,
                left,
                right,
                1);
    }

    private int queryHelper(
            int start,
            int end,
            int left,
            int right,
            int node) {

        if(right < start ||
           left > end) {

            return 0;
        }

        if(left <= start &&
           end <= right) {

            return tree[node];
        }

        int mid =
                (start + end) / 2;

        int leftSum =
                queryHelper(
                        start,
                        mid,
                        left,
                        right,
                        2 * node);

        int rightSum =
                queryHelper(
                        mid + 1,
                        end,
                        left,
                        right,
                        2 * node + 1);

        return leftSum + rightSum;
    }
}