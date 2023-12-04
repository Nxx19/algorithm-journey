package class082;

// 测试链接 : https://www.nowcoder.com/practice/3473e545d6924077a4f7cbc850408ade

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code06 {

	public static int MAXN = 100001;

	public static int[] nums = new int[MAXN];

	public static int[] minSums = new int[MAXN];

	public static int[] minSumEnds = new int[MAXN];

	public static int n, k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			in.nextToken();
			k = (int) in.nval;
			for (int i = 0; i < n; i++) {
				in.nextToken();
				nums[i] = (int) in.nval;
			}
			out.println(compute());
		}
		out.flush();
		out.close();
		br.close();
	}

	public static int compute() {
		minSums[n - 1] = nums[n - 1];
		minSumEnds[n - 1] = n - 1;
		for (int i = n - 2; i >= 0; i--) {
			if (minSums[i + 1] < 0) {
				minSums[i] = nums[i] + minSums[i + 1];
				minSumEnds[i] = minSumEnds[i + 1];
			} else {
				minSums[i] = nums[i];
				minSumEnds[i] = i;
			}
		}
		int ans = 0;
		for (int i = 0, sum = 0, end = 0; i < n; i++) {
			while (end < n && sum + minSums[end] <= k) {
				sum += minSums[end];
				end = minSumEnds[end] + 1;
			}
			ans = Math.max(ans, end - i);
			if (end > i) {
				sum -= nums[i];
			} else {
				end = i + 1;
			}
		}
		return ans;
	}

}
