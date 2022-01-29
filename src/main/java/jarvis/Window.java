package jarvis;

import jdk.nashorn.internal.runtime.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private  int height, width;
    private String title;
    private static Window window = null;
    private long glfwWindow;

    private Window(){

        this.height = 1080;
        this.width = 1920;
        this.title = "Mario";
    }

    public static Window get(){

        if (Window.window == null) {
            Window.window = new Window();
        }
        return  Window.window;
    }

    public void run(){
        System.out.println("Hello Lwgl : "+ Version.version() + "!");

        init();
        loop();

        //free Memory

        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        //Terminate GLFW and free the error callbacks
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public  void init(){
        //Setting up Error callback
        GLFWErrorCallback.createPrint(System.err).set();

        //initialize GLFW
        if(!glfwInit()){
            throw new IllegalStateException("GLFW is not initialized");
        }
        //configure GLFW
        glfwDefaultWindowHints();

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        if (glfwWindow == NULL){
            throw new IllegalStateException("Failed to create GLFW window !!");
        }

        //mouseCallbacks
        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePositionCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::scroll_callback);
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);

        glfwMakeContextCurrent(glfwWindow);
        //Enable V-sync
        glfwSwapInterval(1);

        glfwShowWindow(glfwWindow);

        //crucial line for GLFW context
        GL.createCapabilities();
    }

    public void loop(){

        while (! glfwWindowShouldClose(glfwWindow)) {

            //poll events
            glfwPollEvents();

            glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

            if(MouseListener.isMouseButtonPressed(GLFW_MOUSE_BUTTON_1)){
                System.out.println("MOUSE BUTTON  is pressed");
            }
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);
        }
    }
}
