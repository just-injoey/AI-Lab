import heapq

# Define the maze as a 2D grid (0 represents empty, 1 represents obstacles)
maze = [
    [0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0],
    [0, 0, 1, 0, 0, 0, 0],
    [0, 0, 1, 0, 0, 0, 0],
    [0, 0, 1, 0, 0, 0, 0],
    [0, 0, 1, 0, 0, 0, 0],
    [0, 0, 1, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0]
]

# Define the dimensions of the maze
rows, cols = len(maze), len(maze[0])

# Define the start and goal positions
start = (3, 0)
goal = (5, 4)

# Define a function to calculate the heuristic (Manhattan distance)
def heuristic(node):
    x1, y1 = node
    x2, y2 = goal
    return abs(x1 - x2) + abs(y1 - y2)

# Define A* algorithm
def astar(maze, start, goal):
    open_list = []
    closed_set = set()
    heapq.heappush(open_list, (0, start))
    parent = {}

    while open_list:
        current_cost, current_node = heapq.heappop(open_list)

        if current_node == goal:
            path = []
            while current_node in parent:
                path.append(current_node)
                current_node = parent[current_node]
            path.append(start)
            path.reverse()
            return path

        closed_set.add(current_node)

        neighbors = [(0, 1), (1, 0), (0, -1), (-1, 0)]

        for dx, dy in neighbors:
            x, y = current_node[0] + dx, current_node[1] + dy

            if 0 <= x < rows and 0 <= y < cols and maze[x][y] == 0 and (x, y) not in closed_set:
                tentative_g_score = current_cost + 1
                if (tentative_g_score, (x, y)) not in open_list:
                    parent[(x, y)] = current_node
                    heapq.heappush(open_list, (tentative_g_score + heuristic((x, y)), (x, y)))

    return None  # No path found

# Find and print the path
path = astar(maze, start, goal)
if path:
    print("Path found:")
    for row in range(rows):
        for col in range(cols):
            if (row, col) == start:
                print("S", end=" ")
            elif (row, col) == goal:
                print("G", end=" ")
            elif (row, col) in path:
                print("X", end=" ")
            else:
                print(".", end=" ")
        print()
else:
    print("No path found")