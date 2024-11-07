def knapsack_dp(weights, profits, capacity):
    n = len(weights)
    table = [[0 for _ in range(capacity+1)] for _ in range(n+1)]

    for i in range(n):
        for j in range(1, capacity+1):
            if weights[i] > j:
                table[i+1][j] = table[i][j]
            else:
                table[i+1][j] = max( table[i][j], profits[i] + table[i][j-weights[i]] )
    return table[-1][-1]


weight = [23, 31, 29, 44, 53, 38, 63, 85, 89, 82]
profit = [92, 57, 49, 68, 60, 43, 67, 84, 87, 72]
capacity = 165  

print(knapsack_dp(weight, profit, capacity))