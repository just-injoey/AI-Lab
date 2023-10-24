import json
from collections import deque

def load_graph_from_json(json_file):
    with open(json_file, 'r') as f:
        city_graph = json.load(f)
    return city_graph

def bfs(graph, start, goal):
    queue = deque([(start, [])])
    visited = set()

    while queue:
        node, path = queue.popleft()
        if node == goal:
            return path + [node]
        if node not in visited:
            visited.add(node)
            for neighbor in graph.get(node, {}):
                queue.append((neighbor, path + [node]))

    return None

def dfs(graph, node, goal, visited=None):
    if visited is None:
        visited = set()

    if node == goal:
        return [node]

    if node not in visited:
        visited.add(node)
        for neighbor in graph.get(node, {}):
            path = dfs(graph, neighbor, goal, visited)
            if path:
                return [node] + path

    return None

if __name__ == "__main__":
    city_graph = load_graph_from_json("GJ_Graph.json")

    start_node = 'Gandhinagar'
    goal_node = 'Surat'

    bfs_path = bfs(city_graph, start_node, goal_node)
    dfs_path = dfs(city_graph, start_node, goal_node)

    if bfs_path:
        print("BFS Path:", bfs_path)
    else:
        print("No BFS path found.")

    if dfs_path:
        print("DFS Path:", dfs_path)
    else:
        print("No DFS path found.")
