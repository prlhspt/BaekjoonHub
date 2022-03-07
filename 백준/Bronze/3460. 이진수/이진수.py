T = int(input())
for i in range(T):
    p = int(input())

    result = []

    while p > 0:
        p, n = divmod(p, 2)
        result.append(n)

    for j in range(len(result)):
        if result[j] == 1:
            print(j)