import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AgendaTest {

    private Agenda agenda;

    @BeforeEach
    public void setUp() {
        agenda = new Agenda();
    }

    @Test
    public void testAgregarContacto() {
        Contacto contacto = new Contacto("Juan", "Juanito", "Calle 123", "123456789", "juan@example.com");
        agenda.ingresarContactos(); 
        assertTrue(agenda.verificarContacto("123456789", "Juan"));
    }

    @Test
    public void testEliminarContacto() {
        Contacto contacto = new Contacto("Juan", "Juanito", "Calle 123", "123456789", "juan@example.com");
        agenda.ingresarContactos(); 
        agenda.eliminarContacto("123456789");
        assertFalse(agenda.verificarContacto("123456789", "Juan"));
    }
    @Test 
    public void testContactoRepetido(){
        Contacto contacto = new Contacto("Juan", "Juanito", "Calle 123", "123456789", "juan@example.com");
        Contacto contacto1 = new Contacto("Juan", "Juanito", "Calle 123", "123456789", "juan@example.com");
        agenda.contactos.add(contacto);
        assertTrue(agenda.verificarContacto(contacto1));
    }

    @Test
    public void testEditarContacto() {
        Contacto contacto = new Contacto("Juan", "Juanito", "Calle 123", "123456789", "juan@example.com");
        agenda.ingresarContactos(); 
        agenda.editarContacto(); 
        assertEquals("NuevoNombre", contacto.getNombre()); 
    }
    @Test
    public void testAgregarGrupo() {
        Grupo grupo = new Grupo("Amigos", Categoria.AMIGOS);
        agenda.ingresarGrupos(); 
        assertNotNull(agenda.grupos);
        assertTrue(agenda.grupos.contains(grupo));
    }
    @Test
    public void testLimiteParticipantesGrupo(){
        Grupo  grupo = new Grupo("Familia", Categoria.FAMILIA);
        Contacto contacto = new Contacto("Juan", "Juanito", "Calle 123", "1234567890", "juan@example.com");
        Contacto contacto1 = new Contacto("Juan1", "Juanito", "Calle 123", "1234567891", "juan@example.com");
        Contacto contacto2 = new Contacto("Juan2", "Juanito", "Calle 123", "1234567892", "juan@example.com");
        Contacto contacto3 = new Contacto("Juan3", "Juanito", "Calle 123", "1234567893", "juan@example.com");
        Contacto contacto4 = new Contacto("Juan4", "Juanito", "Calle 123", "1234567894", "juan@example.com");
    }
    @Test
    public void testEliminarGrupo() {
        Grupo grupo = new Grupo("Amigos", Categoria.AMIGOS);
        agenda.ingresarGrupos();
        agenda.eliminarGrupo("Amigos");
        assertFalse(agenda.grupos.contains(grupo));
    }
    @Test
    public void testAgregarParticipanteAGrupo() {
        Contacto contacto = new Contacto("Juan", "Juanito", "Calle 123", "123456789", "juan@example.com");
        Grupo grupo = new Grupo("Amigos", Categoria.AMIGOS);
        agenda.ingresarContactos(); 
        agenda.ingresarGrupos();
        agenda.agregarParticipanteAGrupo(); 
        assertTrue(grupo.getParticipantes().contains(contacto));
    }
    @Test
    public void testAgregarReunion() {
        Reunion reunion = new Reunion("Reunión de equipo", LocalDate.now(), LocalTime.now());
        agenda.ingresarReuniones(); 
        assertTrue(agenda.reuniones.contains(reunion));
    }
    @Test
    public void testEliminarReunion() {
        Reunion reunion = new Reunion("Reunión de equipo", LocalDate.now(), LocalTime.now());
        agenda.ingresarReuniones(); 
        agenda.eliminarReunion("Reunión de equipo");
        assertFalse(agenda.reuniones.contains(reunion));
    }
    @Test
    public void testAgregarAsistenteAReunion() {
        Contacto contacto = new Contacto("Juan", "Juanito", "Calle 123", "123456789", "juan@example.com");
        Reunion reunion = new Reunion("Reunión de equipo", LocalDate.now(), LocalTime.now());
        agenda.ingresarContactos(); 
        agenda.ingresarReuniones();
        agenda.agregarAsistenteAReunion();
        assertTrue(reunion.getAsistentes().contains(contacto));
    }
    @Test
    public void testEliminarAsistenteDeReunion() {
        Contacto contacto = new Contacto("Juan", "Juanito", "Calle 123", "123456789", "juan@example.com");
        Reunion reunion = new Reunion("Reunión de equipo", LocalDate.now(), LocalTime.now());
        agenda.ingresarContactos(); 
        agenda.ingresarReuniones();
        agenda.agregarAsistenteAReunion(); 
        agenda.eliminarAsistenteDeReunion(); 
        assertFalse(reunion.getAsistentes().contains(contacto));
    }
        

}
