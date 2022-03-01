import sys
input = sys.stdin.readline

def paint_chess(x:int, y:int) -> int:
    
    od_count = 0
    ev_count = 0
    
    for i in range(x, x+8):
        for j in range(y, y+8):
            # 백색
            if (i % 2 == 0 and j % 2 == 0) or (i % 2 == 1 and j % 2 == 1):
                if board[i][j] != 'B':
                    od_count += 1
            
            elif (i % 2 == 0 and j % 2 == 1) or (i % 2 == 1 and j % 2 == 0):
                if board[i][j] != 'W':
                    od_count += 1
    
    for i in range(x, x+8):
        for j in range(y, y+8):
            # 백색
            if (i % 2 == 0 and j % 2 == 0) or (i % 2 == 1 and j % 2 == 1):
                if board[i][j] != 'W':
                    ev_count += 1
            
            elif (i % 2 == 0 and j % 2 == 1) or (i % 2 == 1 and j % 2 == 0):
                if board[i][j] != 'B':
                    ev_count += 1
            
    return min(od_count, ev_count)


#입력 받기
n, m = map(int, input().split())
board = [[] for _ in range(n)]
result = sys.maxsize

# board 2차원 배열로 받기
for i in range(n):

    a = input().split()
    
    for j in list(a[0]):
        board[i].append(j)


for i in range(n - 7):
    for j in range(m - 7):
        count = paint_chess(i, j)
        result = min(result, count)

print(result)