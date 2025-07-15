extends Node3D

var server = TCPServer.new()
var client = StreamPeerTCP.new()

func _ready():
	match GameMode.mode:
		GameMode.Mode.SERVER:
			print("Starting in SERVER mode")
			start_game_session()
		GameMode.Mode.CLIENT:
			print("Starting in CLIENT mode")
			connect_to_server("192.168.1.21", 5555)
		GameMode.Mode.AUTO:
			print("AUTO mode: defaulting to CLIENT")
			connect_to_server("192.168.1.21", 5555)

func start_game_session():
	if server.listen(5555) != OK:
		print("Failed to start server")
		return
	print("Game server started on port 5555")
	set_process(true)

func connect_to_server(host: String, port: int):
	var err = client.connect_to_host(host, port)
	if err != OK:
		print("Failed to connect to server: %s" % err)
	else:
		print("Connecting to server at %s:%s..." % [host, port])
	set_process(true)

func _process(delta: float):
	if GameMode.mode == GameMode.Mode.SERVER:
		if server.is_connection_available():
			var peer = server.take_connection()
			print("Client connected from %s" % peer.get_connected_host())
			peer.put_utf8_string("Welcome to Godot server\n")

	elif GameMode.mode == GameMode.Mode.CLIENT:
		if client.get_status() == StreamPeerTCP.STATUS_CONNECTED:
			if client.get_available_bytes() > 0:
				var msg = client.get_utf8_string(client.get_available_bytes())
				print("Server says: %s" % msg)
