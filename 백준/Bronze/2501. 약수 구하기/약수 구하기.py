p, q = map(int, input().split())

result = []

for i in range(1, p+1):
    if p % i == 0:
        result.append(i)

if len(result) >= q:
    print(result[q-1])
else:
    print(0)