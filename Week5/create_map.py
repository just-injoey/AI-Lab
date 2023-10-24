import json

# Define the graph representing the road map for five cities with sensory inputs
city_graph = {
    'Gandhinagar': {
        'Ahmedabad': {'time': 45, 'traffic': 2, 'weather': 3, 'road_condition': 4, 'safety': 5},
        'Baroda': {'time': 90, 'traffic': 3, 'weather': 2, 'road_condition': 3, 'safety': 4},
        'Bharuch': {'time': 120, 'traffic': 4, 'weather': 1, 'road_condition': 2, 'safety': 3},
        'Surat': {'time': 150, 'traffic': 5, 'weather': 4, 'road_condition': 1, 'safety': 2}
    },
    'Ahmedabad': {
        'Gandhinagar': {'time': 45, 'traffic': 2, 'weather': 3, 'road_condition': 4, 'safety': 5},
        'Baroda': {'time': 75, 'traffic': 3, 'weather': 2, 'road_condition': 3, 'safety': 4},
        'Bharuch': {'time': 105, 'traffic': 4, 'weather': 1, 'road_condition': 2, 'safety': 3},
        'Surat': {'time': 135, 'traffic': 5, 'weather': 4, 'road_condition': 1, 'safety': 2}
    },
    'Baroda': {
        'Gandhinagar': {'time': 90, 'traffic': 3, 'weather': 2, 'road_condition': 3, 'safety': 4},
        'Ahmedabad': {'time': 75, 'traffic': 3, 'weather': 2, 'road_condition': 3, 'safety': 4},
        'Bharuch': {'time': 45, 'traffic': 2, 'weather': 3, 'road_condition': 4, 'safety': 5},
        'Surat': {'time': 90, 'traffic': 3, 'weather': 2, 'road_condition': 3, 'safety': 4}
    },
    'Bharuch': {
        'Gandhinagar': {'time': 120, 'traffic': 4, 'weather': 1, 'road_condition': 2, 'safety': 3},
        'Ahmedabad': {'time': 105, 'traffic': 4, 'weather': 1, 'road_condition': 2, 'safety': 3},
        'Baroda': {'time': 45, 'traffic': 2, 'weather': 3, 'road_condition': 4, 'safety': 5},
        'Surat': {'time': 45, 'traffic': 2, 'weather': 3, 'road_condition': 4, 'safety': 5}
    },
    'Surat': {
        'Gandhinagar': {'time': 150, 'traffic': 5, 'weather': 4, 'road_condition': 1, 'safety': 2},
        'Ahmedabad': {'time': 135, 'traffic': 5, 'weather': 4, 'road_condition': 1, 'safety': 2},
        'Baroda': {'time': 90, 'traffic': 3, 'weather': 2, 'road_condition': 3, 'safety': 4},
        'Bharuch': {'time': 45, 'traffic': 2, 'weather': 3, 'road_condition': 4, 'safety': 5}
    }
}

# Save the graph to a JSON file
with open('city_graph.json', 'w') as json_file:
    json.dump(city_graph, json_file, indent=4)

print("Graph data has been saved to 'city_graph.json'.")
