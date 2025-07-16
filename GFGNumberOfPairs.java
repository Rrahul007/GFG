//Back-end complete function Template for Java
class Solution {
    // Function to count number of pairs such that x^y is greater than y^x.
    public long countPairs(int arr[], int brr[], int M, int N) {
        // code here
        Arrays.sort(brr);

        // Count frequencies of small values in brr (0-4)
        int[] countY = new int[5];
        for (int y : brr) {
            if (y < 5) countY[y]++;
        }

        long totalPairs = 0;
        for (int x : arr) {
            totalPairs += countValidY(x, brr, countY);
        }

        return totalPairs;
    }

    static int countValidY(int x, int[] brr, int[] countY) {
        if (x == 0) return 0;
        if (x == 1) return countY[0]; // Only y == 0 satisfies 1^0 > 0^1

        // Binary search: count of y > x
        int idx = upperBound(brr, x);
        int count = brr.length - idx;

        // Add special cases
        count += countY[0] + countY[1]; // since x^0 = 1, x^1 = x; both usually > y^x

        // Handle exceptions:
        if (x == 2) {
            // Remove y=3 and y=4, because 2^3 < 3^2 and 2^4 == 16 < 4^2 == 16
            count -= (countY[3] + countY[4]);
        }
        if (x == 3) {
            // Add y=2 manually because 3^2 > 2^3
            count += countY[2];
        }

        return count;
    }

    // Equivalent to C++ upper_bound
    static int upperBound(int[] arr, int x) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
