subway = 0
answer = 0

for i in range(10):
    sub_out, sub_in = map(int, input().split())
    subway += sub_in
    subway -= sub_out

    answer = max(answer, subway)

print(answer)
