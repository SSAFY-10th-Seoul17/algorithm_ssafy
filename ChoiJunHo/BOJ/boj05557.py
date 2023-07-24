import sys
input = sys.stdin.readline

N = int(input())
a = list(map(int, input().split()))

dp = [[0 for i in range(20 + 1)] for j in range(100)]

dp[0][a[0]] = 1
for i in range(1, N - 1):
    for j in range(20 + 1):
        if dp[i - 1][j] != 0:
            sum = j + a[i]
            if sum >= 0 and sum <= 20:
                dp[i][sum] += dp[i - 1][j]
            sum = j - a[i]
            if sum >= 0 and sum <= 20:
                dp[i][sum] += dp[i - 1][j]

print(dp[N - 2][a[-1]])