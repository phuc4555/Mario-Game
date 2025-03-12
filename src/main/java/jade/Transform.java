package jade;

import org.joml.Vector2f;


public class Transform {

    public Vector2f position;
    public Vector2f scale;


    public Transform() {
        init(new Vector2f(), new Vector2f());
    }

    public Transform(Vector2f position) {
        init(position, new Vector2f());
    }


    public Transform(Vector2f position, Vector2f scale) {
        init(position, scale);
    }


    public void init(Vector2f position, Vector2f scale) {
        this.position = position;
        this.scale = scale;
    }

    public Transform copy() {
        return new Transform(new Vector2f(this.position), new Vector2f(this.scale));
    }

    public void copy(Transform transform) {
        this.position = transform.position;
        this.scale = transform.scale;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Transform)) return false;
        Transform t = (Transform) obj;
        return this.position.equals(t.position) && this.scale.equals(t.scale);
    }
}
