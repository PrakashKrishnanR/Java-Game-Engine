package jarvis;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {

    private static KeyListener instance;
    private boolean[] keyBindings = new boolean[350];

    private KeyListener(){

    }

    public static KeyListener get(){

        if(instance == null){
            instance = new KeyListener();
        }
        return instance;
    }

    public static void keyCallback(long window, int key, int scanCode, int action, int mods){

        if(action == GLFW_PRESS){
            get().keyBindings[key] = true;
        } else  if(action == GLFW_RELEASE){
            get().keyBindings[key] = false;
        }
    }

    public static boolean isKeyPressed(int key){
            return get().keyBindings[key];
    }
}
