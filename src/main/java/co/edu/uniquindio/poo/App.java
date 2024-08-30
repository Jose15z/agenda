package co.edu.uniquindio.poo;
import javax.swing.JOptionPane;
/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Contacto contacto = new Contacto("Jose", "jose", "km3...", "314", "uqvirtual");
        JOptionPane.showMessageDialog(null, contacto.toString());
    }
}
