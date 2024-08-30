package co.edu.uniquindio.poo;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Contacto contacto = new Contacto("Jose", "jose", "km3...", "314", "uqvirtual");
        JOptionPane.showMessageDialog(null, contacto.toString());
        Grupo grupo = new Grupo("grupo", Categoria.AMIGOS );
        JOptionPane.showMessageDialog(null, grupo.toString());
        Reunion reunion = new Reunion("Reunion de negocios", LocalDate.of(2024, 07, 30), LocalTime.of(12, 30));
        JOptionPane.showMessageDialog(null, reunion.toString());
    }
}