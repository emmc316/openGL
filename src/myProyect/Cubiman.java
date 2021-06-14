/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myProyect;

import com.jogamp.opengl.GL;
import static com.jogamp.opengl.GL.GL_DEPTH_TEST;
import static com.jogamp.opengl.GL.GL_LEQUAL;
import static com.jogamp.opengl.GL.GL_LINES;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author halo_
 */
public class Cubiman  extends GLCanvas implements GLEventListener,  KeyListener {
     private GLU glu;  // para las herramientas GL (GL Utilities)
     private GLUT glut;
     private static String TITLE = "Cubiman";  // window's title
   private static final int CANVAS_WIDTH = 800;  // width of the drawable
   private static final int CANVAS_HEIGHT = 680; // height of the drawable
   private static final int FPS = 40; // animator's target frames per second
   private static final float factInc = 5.0f; // animator's target frames per second
   private float fovy = 45.0f;   
   
  float rotacion= 0;
  int rotacion3 =0;
  float rotacion2=0;
  float lightX=1f;
  float lightY=1f;
  float lightZ=1f;
  float dLight=0.05f;
  float dLight2=0.05f;
  boolean luz= false;
  
   float liX = -2f;
    float liY = 1f;
    float liZ = 2f;
  boolean bandera = false;
  boolean bandera2 = false;
  boolean bandera3 = false;
    final float ambiente2[] = {0.0f, 0.0f, 0.0f, 1.0f};
  final float ambient[] = { 0.2f, 0.2f, 0.2f, 1.0f };
  final float position[] = { lightX, lightY, lightZ, 1.0f };
  final float mat_diffuse[] = { 0.6f, 0.6f, 0.6f, 1.0f };
  final float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
  final float mat_shininess[] = { 50.0f };

  final float[] colorBlack  = {0.0f,0.0f,0.0f,1.0f};
  final float[] colorWhite  = {1.0f,1.0f,1.0f,1.0f};
  final float[] colorGray   = {0.4f,0.4f,0.4f,1.0f};
  final float[] colorDarkGray = {0.2f,0.2f,0.2f,1.0f};
 // final float[] colorRed    = {1.0f,0.0f,0.0f,1.0f};
  final float[] colorGreen  = {0.0f,1.0f,0.0f,1.0f};
  final float[] colorBlue   = {0.0f,0.0f,0.6f,1.0f};
  final float[] colorYellow = {1.0f,1.0f,0.0f,1.0f};
  final float[] colorLightYellow = {.5f,.5f,0.0f,1.0f};
   final float[] colorRed    = {1.0f,0.0f,0.0f,1.0f};

   
   
   
   public Cubiman() {
    this.addGLEventListener(this);

  }
   
    @Override
    public void init(GLAutoDrawable drawable) {
       
       GL2 gl = drawable.getGL().getGL2();
      // Establece un material por default.
      gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // set background (clear) color
      gl.glClearDepth(1.0f);      // set clear depth value to farthest
      gl.glEnable(GL_DEPTH_TEST); // enables depth testing
      gl.glDepthFunc(GL_LEQUAL);  // the type of depth test to do 
      gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting  
    
      gl.glEnable( GL2.GL_LIGHTING );
      gl.glEnable( GL2.GL_LIGHT0 );
      
  
      
      

     glu = new GLU();                      
      glut = new GLUT();
    }
    
    
//SON DIRECTAMENTE LOS COLORES DEL OBJETO POR CAPAS
//-----------------------------------------------------------------
  public void setSomeGreenMaterial( GL2 gl, int face )
    {
    gl.glMaterialfv( face, GL2.GL_AMBIENT, colorDarkGray , 0 );
     gl.glMaterialfv( face, GL2.GL_DIFFUSE, colorGreen , 0 );
      gl.glMaterialfv( face, GL2.GL_SPECULAR, colorGreen , 0 );
      gl.glMateriali( face, GL2.GL_SHININESS, 4 );
      gl.glMaterialfv( face, GL2.GL_EMISSION, colorDarkGray , 0 );
    }
//-----------------------------------------------------------------  
  
 
//---------------------------------------------------------------------     
     
     public void setSomeRedMaterial( GL2 gl, int face )
    {
      gl.glMaterialfv( face, GL2.GL_AMBIENT, colorRed , 0 );
      gl.glMaterialfv( face, GL2.GL_DIFFUSE, colorRed , 0 );
      gl.glMaterialfv( face, GL2.GL_SPECULAR, colorRed , 0 );
      gl.glMateriali( face, GL2.GL_SHININESS, 4 );
      gl.glMaterialfv( face, GL2.GL_EMISSION, colorBlack , 0 );
    }
     
      
     public void setSomeBlackMaterial( GL2 gl, int face )
    {
      gl.glMaterialfv( face, GL2.GL_AMBIENT, colorBlack , 0 );
      gl.glMaterialfv( face, GL2.GL_DIFFUSE, colorBlack , 0 );
      gl.glMaterialfv( face, GL2.GL_SPECULAR, colorBlack , 0 );
      gl.glMateriali( face, GL2.GL_SHININESS, 4 );
      gl.glMaterialfv( face, GL2.GL_EMISSION, colorBlack , 0 );
    }
    
    public void setSomeYellowMaterial( GL2 gl, int face )
    {
      gl.glMaterialfv( face, GL2.GL_AMBIENT, colorYellow , 0 );
      gl.glMaterialfv( face, GL2.GL_DIFFUSE, colorYellow , 0 );
      gl.glMaterialfv( face, GL2.GL_SPECULAR, colorYellow , 0 );
      gl.glMateriali( face, GL2.GL_SHININESS, 4 );
      gl.glMaterialfv( face, GL2.GL_EMISSION, colorBlack , 0 );
    }
     
  //ACTUA EN LA FORMA DEL OBJETO
//-----------------------------------------------------
  public void cuerpo( GL2 gl, GLUT glut ) 
    {
    this.setSomeRedMaterial(gl,GL.GL_FRONT_AND_BACK);
    //glut.glutSolidCylinder(0.9, 1.8, 30, 30);
     gl.glTranslatef(0.0f, 0, 0.2f);
      glut.glutSolidCube((float) 1.5);
    }
//-----------------------------------------------------
  
   public void cuello( GL2 gl, GLUT glut ) 
    {
    this.setSomeRedMaterial(gl,GL.GL_FRONT_AND_BACK );
    //glut.glutSolidCylinder(0.9, 0.1, 30, 30);
     gl.glTranslatef(0.0f, 0, 0.7f);
    glut.glutSolidCube((float) 1);
    }
 
    public void cabeza (GL2 gl,GLUT glut){
  this.setSomeBlueMaterial(gl,GL.GL_FRONT_AND_BACK );
  glut.glutSolidCube((float) 1);
  //glut.glutSolidSphere(0.9, 30, 30);
 
  }
  
    public void piernaIzquierda (GL2 gl,GLUT glut){
         gl.glPushMatrix();
        this.setSomeBlackMaterial(gl,GL.GL_FRONT_AND_BACK );
       //this.setSomeGreenMaterial(gl, GL.GL_FRONT_AND_BACK);
        gl.glTranslatef(-0.5f, 0, 0.9f);
        gl.glRotatef(rotacion, 1.0f, 0.0f, 0.0f);
        glut.glutSolidCylinder(0.2, 0.9, 30, 30); 
        gl.glTranslatef(0f, 0, 0.9f);
        glut.glutSolidSphere(0.2, 30, 30);
        
        
        gl.glPopMatrix();
     
    // glut.glutSolidSphere(0.9, 30, 30);
  }
     public void piernaDerecha (GL2 gl,GLUT glut){
         gl.glPushMatrix();
         
         
        this.setSomeBlackMaterial(gl, GL.GL_FRONT_AND_BACK);
        //this.setSomeGreenMaterial(gl, GL.GL_FRONT_AND_BACK);
        gl.glTranslatef(0.5f, 0, 0.9f);
        gl.glRotatef(rotacion2, 1.0f, 0.0f, -10.0f);
        glut.glutSolidCylinder(0.2, 0.9, 30, 30); 
        //glut.glutSolidCube((float) 0.7);
        gl.glTranslatef(0f, 0, 0.9f);
        glut.glutSolidSphere(0.2, 30, 30);
        
        
        gl.glPopMatrix();
         
   
     
    // glut.glutSolidSphere(0.9, 30, 30);
  }
     
       public void manoIzquierda (GL2 gl,GLUT glut){
         gl.glPushMatrix();
         gl.glPushMatrix();
         gl.glRotatef(rotacion2, 1.0f, 0, 1);
         this.setSomeBlackMaterial(gl, GL.GL_FRONT_AND_BACK);
         //this.setSomeGreenMaterial(gl, GL.GL_FRONT_AND_BACK);
        gl.glTranslatef(-1.1f, 0, 0.3f);   
        glut.glutSolidSphere(0.2, 30, 30);
        glut.glutSolidCylinder(0.2, 0.9, 30, 30); 
        gl.glTranslatef(0f, 0, 0.9f);
        
        
        glut.glutSolidSphere(0.2, 30, 30);
     
        gl.glPopMatrix();

    // glut.glutSolidSphere(0.9, 30, 30);
  }
         
       public void manoDerecha (GL2 gl,GLUT glut){
           
         gl.glPushMatrix();
         
         gl.glRotatef(rotacion, 1.0f, 0, 0);
        this.setSomeBlackMaterial(gl, GL.GL_FRONT_AND_BACK);
        //this.setSomeGreenMaterial(gl, GL.GL_FRONT_AND_BACK);
        gl.glTranslatef(1.1f, 0, 0.3f);   
        glut.glutSolidSphere(0.2, 30, 30);
        glut.glutSolidCylinder(0.2, 0.9, 30, 30); 
        gl.glTranslatef(0f, 0, 0.9f);
        glut.glutSolidSphere(0.2, 30, 30);
     
        gl.glPopMatrix();

    // glut.glutSolidSphere(0.9, 30, 30);
  }
       
          public void ojoIzquierda (GL2 gl,GLUT glut){
           
         gl.glPushMatrix();
         
        this.setSomeRedMaterial(gl, GL.GL_FRONT_AND_BACK);
       // this.setSomeBlackMaterial(gl, GL.GL_FRONT_AND_BACK);
        gl.glTranslatef(-0.25f, 0.7f, -0.3f);   
        glut.glutSolidSphere(0.15, 30, 30);
     
     
        gl.glPopMatrix();

    // glut.glutSolidSphere(0.9, 30, 30);
  }
           public void moveLightX(boolean positivDirection) {
        lightX += positivDirection ? dLight : -dLight;
    }
              public void moveLiX(boolean positivDirection) {
        liX += positivDirection ? dLight2 : -dLight2;
    }


    public void moveLightY(boolean positivDirection) {
        lightY += positivDirection ? dLight : -dLight;
    }
    
    public void moveLiY(boolean positivDirection) {
        liY += positivDirection ? dLight2 : -dLight2;
    }


    public void moveLightZ(boolean positivDirection) {
        lightZ += positivDirection ? dLight : -dLight;
    }
      public void moveLiZ(boolean positivDirection) {
        liZ += positivDirection ? dLight2 : -dLight2;
    }


          
    public void ojoDerecho (GL2 gl,GLUT glut){
           
         gl.glPushMatrix();
         
        
        this.setSomeRedMaterial(gl, GL.GL_FRONT_AND_BACK);
        gl.glTranslatef(0.25f, 0.7f, -0.3f);   
        glut.glutSolidSphere(0.15, 30, 30);
     
     
        gl.glPopMatrix();

    // glut.glutSolidSphere(0.9, 30, 30);
  }
      public void setLightSphereMaterial(GL2 gl, int face) {
        gl.glMaterialfv(face, GL2.GL_AMBIENT, colorYellow, 0);
        gl.glMaterialfv(face, GL2.GL_DIFFUSE, colorYellow, 0);
        gl.glMaterialfv(face, GL2.GL_SPECULAR, colorYellow, 0);
        gl.glMateriali(face, GL2.GL_SHININESS, 4);
        gl.glMaterialfv(face, GL2.GL_EMISSION, colorYellow, 0);
        //gl.glMaterialfv( face, GL.GL_EMISSION, colorLightYellow , 0 );
        //gl.glMaterialfv( face, GL.GL_EMISSION, colorBlack , 0 );
    }
       

    public void drawLight(GL2 gl, GLU glu, GLUT glut) {
        gl.glPushMatrix();
        this.setSomeYellowMaterial(gl, GL.GL_FRONT_AND_BACK);
        {
          gl.glTranslatef(lightX, lightY, lightZ);
            glut.glutSolidSphere(0.1f, 20, 20);
        }
      //  float posLight[] = {liX, liY, liZ, 0.0f};
       // gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, posLight, 0);
        gl.glPopMatrix();
    }
     public void setSomeBlueMaterial(GL2 gl, int face) {
        // gl.glMaterialfv(face, GL2.GL_AMBIENT, ambiente2, 0);
        gl.glMaterialfv(face, GL2.GL_DIFFUSE, colorBlue, 0);
        gl.glMaterialfv(face, GL2.GL_SPECULAR, colorBlue, 0);
        gl.glMateriali(face, GL2.GL_SHININESS, 4);
        gl.glMaterialfv(face, GL2.GL_EMISSION, colorBlue, 0);
    }
    
       public void drawLight2(GL2 gl, GLU glu, GLUT glut) {
           
           
           {
      setLightSphereMaterial( gl, GL.GL_FRONT_AND_BACK );
      gl.glPushMatrix(); {
	gl.glTranslatef( liX, liY, liZ );
	glut.glutSolidSphere( 0.1f, 20, 20 );
      } gl.glPopMatrix();
    }
       }
   

   public void animate(GL2 gl, GLU glu, GLUT glut) {
        float posLight0[] = {lightX, lightY, lightZ, 1.f};
      if(luz == true){ gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, posLight0, 0);
      }else{
      }
        drawLight(gl, glu, glut);
        
        
        //lightX += 0.003f;
        //lightY += 0.003f;
    }
    public void animate2(GL2 gl, GLU glu, GLUT glut) {
        float posLight1[] = {liX, liY, liZ, 1.f};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, posLight1, 0);
        
        
       drawLight2(gl, glu, glut);
        
        
        //lightX += 0.003f;
        //lightY += 0.003f;
    }



    @Override
    public void dispose(GLAutoDrawable glad) {
   
    }

    @Override
    public void display(GLAutoDrawable glad) {
             
        GL2 gl = glad.getGL().getGL2();  // get the OpenGL 2 graphics context
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();  // reset the model-view matrix
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        glu.gluLookAt(0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
        this.setSomeGreenMaterial( gl, GL.GL_FRONT_AND_BACK );   
        
           gl.glRotatef(rotacion3,1, 0, 0);
           gl.glTranslatef(0,0.0f,-1.0f);
           this.cabeza ( gl, glut ) ;
           this.ojoIzquierda(gl, glut);
           this.ojoDerecho(gl, glut);
           this.setSomeRedMaterial(gl,GL.GL_FRONT_AND_BACK );
           gl.glTranslatef(0,0.0f,-0.1f);
           this.cuello(gl, glut);
           this.setSomeGreenMaterial( gl, GL.GL_FRONT_AND_BACK );   
           gl.glTranslatef(0,0.0f,0.1f);
          this.cuerpo(gl, glut);
      
           this.piernaIzquierda(gl, glut);
           this.piernaDerecha(gl, glut);
           this.manoIzquierda(gl, glut);
           this.manoDerecha(gl, glut);
      
       
          
          
            if ( bandera3 == true ){
            if(rotacion3 < 20 ){
                    bandera3 = false;      
            }else{
            rotacion3 --;
            }
        }else{
            if(rotacion3 > 140){
                bandera3= true;
              this.rotacion3++;
            }else{
             this.rotacion3++;
            }   
        }
        
           
        if ( bandera == true ){
            if(rotacion < -60 ){
                    bandera = false;      
            }else{
            rotacion -=10;
            }
        }else{
            if(rotacion > 60){
                bandera= true;
              this.rotacion++;
            }else{
             this.rotacion+=10;
            }   
        }
        
        
        if ( bandera2 == true ){
            if(rotacion2 > 60){
                bandera2= false;
              this.rotacion2++;
            }else{
             this.rotacion2+=10;
            }  
            
        }else{
            
            if(rotacion2 < -60 ){
                    bandera2 = true;      
            }else{
            rotacion2 -=10;
            }
            
             
        }
        
       System.out.printf("Rotacion %f \n", this.rotacion);
        
     //  this.drawLight2(gl, glut);
        //this.drawLight(gl, glu, glut);
        this.animate2(gl, glu, glut);
        this.animate(gl, glu, glut);
        
      
        gl.glFlush(); 
        
        
        
        
    }
    

    @Override
    public void reshape(GLAutoDrawable glad,int x, int y, int width, int height) {
    GL2 gl = glad.getGL().getGL2();  // get the OpenGL 2 graphics context

        if (height == 0) height = 1;   // prevent divide by zero
        float aspect = (float)width / height;

        // Set the view port (display area) to cover the entire window
        gl.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL_PROJECTION);  // choose projection matrix
        gl.glLoadIdentity();             // reset projection matrix
        glu.gluPerspective(fovy, aspect, 0.1, 50.0); // fovy, aspect, zNear, zFar
        
        // Enable the model-view transform
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity(); // reset
    }
    
    
     public static void main(String[] args) {
      
            SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            // Create the OpenGL rendering canvas
            GLCanvas canvas = new Cubiman();
            canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
            // Create a animator that drives canvas' display() at the specified FPS.
           final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);
 
            // Create the top-level container
            final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            
            FlowLayout fl = new FlowLayout();
            frame.setLayout(fl);
            frame.setDefaultCloseOperation(3);
            panel1.add(canvas);
            frame.getContentPane().add(panel1);
            frame.getContentPane().add(panel2);
            frame.addKeyListener((KeyListener) canvas);
            frame.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosing(WindowEvent e) {
                  // Use a dedicate thread to run the stop() to ensure that the
                  // animator stops before program exits.
                  new Thread() {
                     @Override
                     public void run() {
                        if (animator.isStarted()) animator.stop();
                       
                     }
                  }.start();
               }
            });
                        
            frame.setTitle(TITLE);
            frame.pack();
            frame.setVisible(true);
           animator.start(); // start the animation loop
        
         }
      });
         
         }

    @Override
    public void keyTyped(KeyEvent e) {
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int codigo = e.getKeyCode();
        //  lightX, lightY, lightZ
        System.out.println("codigo presionado = " + codigo);

        switch (codigo) {
            case KeyEvent.VK_DOWN:
                this.moveLightZ(true);
                break;
            case KeyEvent.VK_UP:
                this.moveLightZ(false);
                break;
            case KeyEvent.VK_RIGHT:
                this.moveLightX(true);
                break;
            case KeyEvent.VK_LEFT:
                this.moveLightX(false);
                break;
            case KeyEvent.VK_PAGE_UP:
                this.moveLightX(false);
                break;
            case KeyEvent.VK_PAGE_DOWN:
                this.moveLightX(true);
                break;
          case KeyEvent.VK_S:
                this.moveLiZ(true);
                break;
            case KeyEvent.VK_W:
                this.moveLiZ(false);
                break;
            case KeyEvent.VK_D:
                this.moveLiX(true);
                break;
            case KeyEvent.VK_A:
                this.moveLiX(false);
                break;
            case KeyEvent.VK_ENTER:
                if(luz == false){
                    luz =true;
                }else{
                    luz = false;
                }
                break;
        }
    
    }

    @Override
    public void keyReleased(KeyEvent e) {
     
    }
     

}
