package jarvis;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {

    private static MouseListener instance;

    private double scrollX, scrollY;
    private double xpos, ypos, lastX, lastY;
    private boolean[] mousebuttonPressed = new boolean[3];
    private boolean isDragging;

    private MouseListener(){

        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xpos = 0.0;
        this.ypos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseListener get(){

        if(instance == null){
            instance = new MouseListener();
        }
        return instance;
    }

    public static void mousePositionCallback(long window, double xPos, double yPos)
    {
        get().lastX = get().xpos;
        get().lastY = get().xpos;
        get().xpos = xPos;
        get().ypos = yPos;

        get().isDragging = get().mousebuttonPressed[0] || get().mousebuttonPressed[1] || get().mousebuttonPressed[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods){

        if(action == GLFW_PRESS && button < get().mousebuttonPressed.length){
            get().mousebuttonPressed[button] = true;
        } else if(action == GLFW_RELEASE && button < get().mousebuttonPressed.length){
            get().mousebuttonPressed[button] = false;
            get().isDragging = false;
        }
    }

    public static void scroll_callback(long window, double xOffset, double yOffset)
    {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame(){

        get().scrollY =0.0;
        get().scrollX = 0.0;
        get().lastX = get().xpos;
        get().lastY = get().ypos;
    }

    public static float getX(){
       return (float) get().xpos;
    }
    public static float getY(){
        return (float) get().ypos;
    }

    public static float getDx(){

        return (float) (get().lastX - get().xpos);
    }

    public static float getDy(){

        return (float) (get().lastY - get().ypos);
    }

    public static float getScrollX(){
        return (float) (get().scrollX);
    }

    public static float getScrollY() {

        return (float) (get().scrollY);
    }

    public static boolean isMouseDragging(){
        return get().isDragging;
    }

    public static boolean isMouseButtonPressed(int button){

        if(button < get().mousebuttonPressed.length){
            return  get().mousebuttonPressed[button];
        }
        return  false;
    }
}
