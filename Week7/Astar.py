import heapq

# Define the map
map_data = [
    [0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 1, 0, 0, 0, 0, 0],
    [0, 0, 1, 0, 0, 0, 0, 0],
    [0, 0, 1, 0, 0, 0, 0, 0],
    [0, 0, 1, 0, 0, 0, 0, 0],
    [0, 0, 1, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0]
]

# Define the source and destination points
start = (0, 3)
end = (4, 5)

# Define possible movements (up, down, left, right)
movements = [(1, 0), (-1, 0), (0, 1), (0, -1)]

def heuristic(a, b):
    return abs(a[0] - b[0]) + abs(a[1] - b[1])

def astar(map_data, start, end):
    open_list = []
    closed_set = set()

    heapq.heappush(open_list, (0, start))
    while open_list:
        current_cost, current_node = heapq.heappop(open_list)

        if current_node == end:
            return current_cost

        if current_node in closed_set:
            continue

        closed_set.add(current_node)

        for move in movements:
            new_node = (current_node[0] + move[0], current_node[1] + move[1])

            if (
                0 <= new_node[0] < len(map_data) and
                0 <= new_node[1] < len(map_data[0]) and
                map_data[new_node[0]][new_node[1]] == 0
            ):
                new_cost = current_cost + 1
                heapq.heappush(open_list, (new_cost + heuristic(new_node, end), new_node))

    return None

result = astar(map_data, start, end)

if result is not None:
    print(f"Shortest path from {start} to {end} is {result} units long.")
else:
    print("No path found.")
