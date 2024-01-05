package class096;

import java.util.Arrays;

// 三堆石头拿取斐波那契数博弈
// 有三堆石头，数量分别为a、b、c
// 两个人轮流拿，每次可以选择其中一堆石头，拿取斐波那契数的石头
// 拿到最后一颗石子的人获胜，根据a、b、c返回谁赢
// 来自真实大厂笔试，没有在线测试，对数器验证
public class Code04_FibonacciStones {

	// 如果MAXN变大
	// 相应的要修改f数组
	public static int MAXN = 201;

	// MAXN以内的斐波那契数
	public static int[] f = { 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144 };

	public static String[][][] dp = new String[MAXN][MAXN][MAXN];

	// 动态规划方法彻底尝试
	// 为了验证
	public static String win1(int a, int b, int c) {
		if (a + b + c == 0) {
			return "后手";
		}
		if (dp[a][b][c] != null) {
			return dp[a][b][c];
		}
		String ans = "后手";
		for (int i = 0; i < f.length; i++) {
			if (f[i] <= a) {
				if (win1(a - f[i], b, c).equals("后手")) {
					ans = "先手";
					break;
				}
			}
			if (f[i] <= b) {
				if (win1(a, b - f[i], c).equals("后手")) {
					ans = "先手";
					break;
				}
			}
			if (f[i] <= c) {
				if (win1(a, b, c - f[i]).equals("后手")) {
					ans = "先手";
					break;
				}
			}
		}
		dp[a][b][c] = ans;
		return ans;
	}

	// sg定理
	public static int[] sg = new int[MAXN];

	public static boolean[] appear = new boolean[MAXN];

	public static void build() {
		for (int i = 1; i < MAXN; i++) {
			Arrays.fill(appear, false);
			for (int j = 0; j < f.length && i - f[j] >= 0; j++) {
				appear[sg[i - f[j]]] = true;
			}
			for (int s = 0; s < MAXN; s++) {
				if (!appear[s]) {
					sg[i] = s;
					break;
				}
			}
		}
	}

	public static String win2(int a, int b, int c) {
		return (sg[a] ^ sg[b] ^ sg[c]) != 0 ? "先手" : "后手";
	}

	public static void main(String[] args) {
		build();
		System.out.println("测试开始");
		for (int a = 0; a < MAXN; a++) {
			for (int b = 0; b < MAXN; b++) {
				for (int c = 0; c < MAXN; c++) {
					String ans1 = win1(a, b, c);
					String ans2 = win2(a, b, c);
					if (!ans1.equals(ans2)) {
						System.out.println("出错了！");
					}
				}
			}
		}
		System.out.println("测试结束");
	}

}
