extends CharacterBody3D

@export var speed := 5.0
@export var mouse_sensitivity := 0.002
@export var gravity := 20.0
@export var jump_velocity := 6.5
@export var mass := 60.0 # kg
@export var jump_force := 450.0 # Newtons

var yaw := 0.0
var pitch := 0.0
@onready var cam = $Camera3D
@onready var start_position = get_node("../RespawnPoint").global_transform.origin
	
func _ready():
	Input.set_mouse_mode(Input.MOUSE_MODE_CAPTURED)
	
func  _unhandled_input(event):
	if event is InputEventMouseMotion:
		yaw -= event.relative.x * mouse_sensitivity
		pitch -= event.relative.y * mouse_sensitivity
		pitch = clamp(pitch, -1.5, 1.5)
		rotation.y = yaw
		cam.rotation.x = pitch

func _respawn():
	global_transform.origin = start_position
	velocity = Vector3.ZERO
	
func _input(event):
	if event.is_action_pressed("respawn"):
		_respawn()
		
func _physics_process(_delta):
	var dir = Vector3.ZERO
	
	# Sterowanie - poziom
	if Input.is_action_pressed("move_forward"):
		dir += transform.basis.x
	if Input.is_action_pressed("move_back"):
		dir -= transform.basis.x
	if Input.is_action_pressed("move_left"):
		dir -= transform.basis.z
	if Input.is_action_pressed("move_right"):
		dir += transform.basis.z	
	
	# normalizacja kierunku???	
	dir = dir.normalized()
	
	# Ruch w poziomie
	velocity.x = dir.x * speed
	velocity.z = dir.z * speed
	
	# Grawitacja i skok
	if is_on_floor():
		if Input.is_action_just_pressed("jump"):
			velocity.y = jump_force / mass # jump_velocity
	else:
		velocity.y -= gravity * _delta
				
	# Ruch końcowy
	move_and_slide()
