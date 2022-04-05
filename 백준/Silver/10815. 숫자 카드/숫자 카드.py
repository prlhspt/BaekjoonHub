import sys

read = sys.stdin.readline

N = int(read())
A = list(map(int, read().strip().split()))
A = sorted(A)

M = read()
B_ = list(map(int, read().strip().split()))


def b_search(arr, t, left, right):
    if left <= right:
        m = (left + right) // 2
        if arr[m] == t:
            print(1)

        elif arr[m] > t:
            right = m - 1
            return b_search(arr, t, left, right)

        else:
            left = m + 1
            return b_search(arr, t, left, right)
    else:
        print(0)


left = 0
right = N - 1

for B in B_:
    b_search(A, B, left, right)
