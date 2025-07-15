extends Skeleton3D

func _ready():
	var spine = add_bone("Spine")
	set_bone_rest(spine, Transform3D.IDENTITY.translated(Vector3(0, 1, 0)))

	var head = add_bone("Head")
	set_bone_rest(head, Transform3D.IDENTITY.translated(Vector3(0, 2, 0)))
	set_bone_parent(head, spine)

	var pb = PhysicalBone3D.new()
	pb.bone_name = "Spine"
	pb.mass = 1.0
	add_child(pb)
