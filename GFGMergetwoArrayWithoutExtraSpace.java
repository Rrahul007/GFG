class Solution {
    public void mergeArrays(int a[], int b[]) {
        // code here
        int n = a.length;
        int m = b.length;
        int idx = kthSmallest(a, b, n);

        // Move all smaller elements in a[]
        for (int i = idx; i < n; i++) {
            int temp = a[i];
            a[i] = b[i - idx];
            b[i - idx] = temp;
        }

        // Sort both a[] and b[]
        Arrays.sort(a);
        Arrays.sort(b);
    }
    
    static int kthSmallest(int[] a, int[] b, int k) {
        int n = a.length, m = b.length;
        int lo = 0, hi = n, idx = 0;
        while (lo <= hi) {
            int mid1 = (lo + hi) / 2;
            int mid2 = k - mid1;

            // We don't have mid2 elements in b[], so pick more
            // elements from a[]
            if (mid2 > m) {
                lo = mid1 + 1;
                continue;
            }

            // Find elements to the left and right of partition in a[]
            int l1 = (mid1 == 0 ? Integer.MIN_VALUE : a[mid1 - 1]);
            int r1 = (mid1 == n ? Integer.MAX_VALUE : a[mid1]);

            // Find elements to the left and right of partition in b[]
            int l2 = (mid2 == 0 ? Integer.MIN_VALUE : b[mid2 - 1]);
            int r2 = (mid2 == m ? Integer.MAX_VALUE : b[mid2]);

            // If it is a valid partition
            if (l1 <= r2 && l2 <= r1) {
                idx = mid1;
                break;
            }

            // Check if we need to take lesser elements from a[]
            if (l1 > r2)
                hi = mid1 - 1;

            // Check if we need to take more elements from a[]
            else
                lo = mid1 + 1;
        }
        return idx;
    }
}
