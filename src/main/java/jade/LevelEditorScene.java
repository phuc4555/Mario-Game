package jade;


import components.Sprite;
import components.SpriteRenderer;
import components.Spritesheet;
import imgui.ImGui;
import org.joml.Vector2f;
import renderer.Texture;
import util.AssetPool;

public class LevelEditorScene extends Scene {
    private GameObject go1;
    private Spritesheet spritesheet;

    @Override
    public void init() {
        loadResources();

        spritesheet = AssetPool.getSpritesheet("C:\\Users\\PC\\Mario\\mario\\assets\\images\\spritesheet.png");


        this.camera = new Camera(new Vector2f());

        go1 = new GameObject("Obj1", new Transform(new Vector2f(100, 100), new Vector2f(384, 216)), -1);
        go1.addComponent(new SpriteRenderer(spritesheet.getSprite(0)));
        this.addGameObjectToScene(go1);
        this.activeGameObject = go1;

        GameObject go2 = new GameObject("Obj2", new Transform(new Vector2f(700, 100), new Vector2f(255, 255)), 1);
        go2.addComponent(new SpriteRenderer(new Sprite(
                AssetPool.getTexture("C:\\Users\\PC\\Mario\\mario\\assets\\images\\blendImage1.png")
        )));
        this.addGameObjectToScene(go2);

        GameObject go3 = new GameObject("Obj3", new Transform(new Vector2f(900, 100), new Vector2f(255, 255)), 2);
        go3.addComponent(new SpriteRenderer(new Sprite(
               AssetPool.getTexture( "C:\\Users\\PC\\Mario\\mario\\assets\\images\\blendImage2.png")
        )));
        this.addGameObjectToScene(go3);
    }


    private void loadResources() {
        AssetPool.getShader("C:\\Users\\PC\\Mario\\mario\\assets\\shaders\\default.glsl");
        AssetPool.addSpritesheet("C:\\Users\\PC\\Mario\\mario\\assets\\images\\spritesheet.png",
                new Spritesheet(new Texture("C:\\Users\\PC\\Mario\\mario\\assets\\images\\spritesheet.png"),
                        16, 16, 26, 0));
    }

    private int spriteIndex = 0;
    private float spriteFlipTime = 0.2f;
    private float spriteFlipTimeLeft = 0.0f;

    @Override
    public void update(float dt) {
        System.out.println("FPS: " + (1.0f / dt));
        spriteFlipTimeLeft -= dt;

        if (spriteFlipTimeLeft <= 0) {
            spriteFlipTimeLeft = spriteFlipTime;
            spriteIndex++;
            if (spriteIndex > 4) {
                spriteIndex = 0;
            }

            go1.getComponent(SpriteRenderer.class).setSprite(spritesheet.getSprite(spriteIndex));
        }
        go1.transform.position.x += 10 * dt;

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }

    @Override
    public void imgui() {
        ImGui.begin("Test window");
        ImGui.text("Some random text");
        ImGui.end();
    }
}
