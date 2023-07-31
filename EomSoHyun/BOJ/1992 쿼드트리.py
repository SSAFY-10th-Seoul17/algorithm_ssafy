from collections import deque

n = int(input())
grid = [list(input()) for _ in range(n)]
queue = deque()
queue.append((0, 0, n-1, n-1, n))
result = []

while queue:
  start_x, start_y, end_x, end_y, size = queue.popleft()
  grid_piece = grid[start_x:end_x+1][start_y:end_y+1]
  if '0' in grid_piece and '1' in grid_piece:
    if size > 1:
      size //= 2
      queue.appendleft((start_x + size, start_y + size, end_x, end_y, size))  # 제4사분면
      queue.appendleft((start_x + size, start_y, end_x + size, end_y + size - 1, size))  # 제3사분면
      queue.appendleft((start_x, start_y + size, end_x + size - 1, end_y + size, size))  # 제2사분면
      queue.appendleft((start_x, start_y, end_x + size - 1, end_y + size - 1, size))  # 제1사분면
    # else:
  elif '0' in grid_piece:
    result.append("(0)")



# def calc(grid):
#   if '0' in grid and '1' in grid:  # 0과 1 둘 다 있음
#     for _ in range(4):
#       result.append([])
