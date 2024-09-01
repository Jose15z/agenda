package co.edu.uniquindio.poo;

import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Agenda {
    private Collection<Contacto> contactos;
    private Collection<Grupo> grupos;
    private Collection<Reunion> reuniones;

    /**
     * Método constructor de la clase Agenda
     */
    public Agenda() {
        contactos = new LinkedList<>();
        grupos = new LinkedList<>();
        reuniones = new LinkedList<>();
    }

    /**
     * MÉTODOS PARA EL MANEJO DE SELECCIÓN DEL USUARIO
     */

    // Método para manejar la selección del usuario en el menú principal
    public void manejarSeleccion(String seleccion, Agenda agenda) {
        switch (seleccion) {
            case "Contactos":
                manejarContactos(agenda);
                break;

            case "Reuniones":
                manejarReuniones(agenda);
                break;

            case "Grupos":
                manejarGrupos(agenda);
                break;
        }
    }

    // Método de manejo de contactos
    public static void manejarContactos(Agenda agenda) {
        String[] opciones = { "", "Ver Contactos", "Agregar Contacto", "Eliminar Contacto", "Editar contacto",
                "Volver" };
        String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción", "Gestión de Contactos",
                JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

        switch (seleccion) {
            case "Ver Contactos":
                StringBuilder contactosInfo = new StringBuilder();
                for (Contacto contacto : agenda.contactos) {
                    contactosInfo.append(contacto).append("\n");
                }
                JOptionPane.showMessageDialog(null, contactosInfo.toString(), "Contactos",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "Agregar Contacto":
                agenda.ingresarContactos();
                break;

            case "Eliminar Contacto":
                agenda.eliminarContactoAgenda();
                break;

            case "Editar contacto":
                agenda.editarContacto();
                break;
        }
    }

    // Método de manejo de reuniones
    public static void manejarReuniones(Agenda agenda) {
        String[] opciones = { "", "Ver Reuniones", "Agregar Reunión", "Eliminar Reunión", "Agregar Asistente",
                "Eliminar Asistente", "Volver" };
        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una opción",
                "Gestión de Reuniones",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]);

        switch (seleccion) {
            case "Ver Reuniones":
                StringBuilder reunionesInfo = new StringBuilder();
                for (Reunion reunion : agenda.reuniones) {
                    reunionesInfo.append(reunion).append("\n");
                }
                JOptionPane.showMessageDialog(null, reunionesInfo.toString(), "Reuniones",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "Agregar Reunión":
                agenda.ingresarReuniones();
                break;

            case "Eliminar Reunión":
                String descripcion = ingresarStringMensaje("Ingrese la descripción de la reunión:");
                agenda.eliminarReunion(descripcion);
                break;

            case "Agregar Asistente":
                agenda.agregarAsistenteAReunion();
                break;

            case "Eliminar Asistente":
                agenda.eliminarAsistenteDeReunion();
                break;
        }
    }

    // Método de manejo de grupos
    public static void manejarGrupos(Agenda agenda) {
        String[] opciones = { "", "Ver Grupos", "Agregar Grupo", "Eliminar Grupo", "Agregar participante", "Eliminar participante", "Volver" };
        String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción", "Gestión de Grupos", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        switch (seleccion) {
            case "Ver Grupos":
                StringBuilder gruposInfo = new StringBuilder();
                for (Grupo grupo : agenda.grupos) {
                    gruposInfo.append(grupo).append("\n");
                    }
                JOptionPane.showMessageDialog(null, gruposInfo.toString(), "Grupos", JOptionPane.INFORMATION_MESSAGE);
                break;

            case "Agregar Grupo":
                agenda.ingresarGrupos();
                break;

            case "Eliminar Grupo":
                String nombreGrupo = JOptionPane.showInputDialog("Ingrese el nombre del grupo :");
                agenda.eliminarGrupo(nombreGrupo);
                break;

            case "Agregar participante":
                agenda.agregarParticipanteAGrupo();
                break;
                
            case "Eliminar participante":
                agenda.eliminarParticipanteGrupo();
                break;
        }
    }

    /**
     * MÉTODOS PROPIOS DE LOS CONTACTOS
     */

    // Método que impide la creación de contactos duplicados
    public boolean verificarContacto(String telefono, String nombre) {
        for (Contacto contacto : contactos) {
            if (contacto.getTelefono().equals(telefono) && contacto.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    // Método que permite ingresar un contacto a la agenda
    private Contacto ingresarContacto() {
        String nombre = ingresarStringMensaje("Ingrese el nombre: ");
        String alias = ingresarStringMensaje("Ingrese el alias: ");
        String direccion = ingresarStringMensaje("Ingrese la dirección: ");
        String telefono = ingresarStringMensaje("Ingrese el teléfono: ");
        String email = ingresarStringMensaje("Ingrese el e-mail: ");
        return new Contacto(nombre, alias, direccion, telefono, email);
    }

    // Método que permite ingresar varios contactos a la agenda
    public void ingresarContactos() {
        boolean centinela = true;
        while (centinela) {
            contactos.add(ingresarContacto());
            int respuesta = JOptionPane.showConfirmDialog(
                    null,
                    "¿Desea Ingresar Otro Contacto?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            centinela = (respuesta == JOptionPane.YES_OPTION);
        }
    }
        // Método para eliminar el contacto de la agenda
    public void eliminarContactoAgenda() {
        String telefonoContacto = ingresarStringMensaje("Ingrese el teléfono del contacto que desea eliminar:");

        Contacto contactoSeleccionado = null;
        for (Contacto contacto : contactos) {
            if (contacto.getTelefono().equals(telefonoContacto)) {
                contactoSeleccionado = contacto;
                break;
            }
        }
        if (contactoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Contacto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        eliminarContacto(contactoSeleccionado.getTelefono());
    }

    // Método para editar un contacto
    public void editarContacto() {
        String telefonoContacto = ingresarStringMensaje("Ingrese el teléfono del contacto que desea editar:");
        Contacto contactoSeleccionado = null;
        for (Contacto contacto : contactos) {
            if (contacto.getTelefono().equals(telefonoContacto)) {
                contactoSeleccionado = contacto;
                break;
            }
        }
        if (contactoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Contacto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] edicion = { "editar", "nombre", "alias", "direccion", "telefono", "email" };
        String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción", "Editar contacto",
                JOptionPane.PLAIN_MESSAGE, null, edicion, edicion[0]);
        switch (seleccion) {
            case "nombre":
                contactoSeleccionado.setNombre(ingresarStringMensaje("Ingrese el nuevo nombre "));
                break;
                
            case "alias":
                contactoSeleccionado.setAlias(ingresarStringMensaje("Ingrese el nuevo alias"));
                break;
                
            case "direccion":
                contactoSeleccionado.setDireccion(ingresarStringMensaje("Ingrese la nueva direccion"));
                break;
                
            case "telefono":
                contactoSeleccionado.setTelefono(ingresarStringMensaje("Ingrese el nuevo telefono"));
                break;
                
            case "email":
                contactoSeleccionado.setEmail(ingresarStringMensaje("Ingrese el nuevo Email"));
                break;

        }
    }

    // Método para eliminar un contacto
    public void eliminarContacto(String telefono) {
        for (Contacto contacto : contactos) {
            if (contacto.getTelefono().equals(telefono)) {
                contactos.remove(contacto);
            }
        }
    }

    /**
     * MÉTODOS PROPIOS DE LAS REUNIONES
     */

    // Método que permite crear una reunión
    private Reunion crearReunion() {
        String descripcion = ingresarStringMensaje("Ingrese la descripción de la reunión: ");
        LocalDate fecha = LocalDate.of(ingresarEntero("año"), ingresarEntero("mes"), ingresarEntero("dia"));
        LocalTime hora = LocalTime.of(ingresarEntero("hora"), ingresarEntero("minuto"));
        return new Reunion(descripcion, fecha, hora);
    }

    // Método para ingresar varias reuniones
    public void ingresarReuniones() {
        boolean bandera = true;
        while (bandera) {
            reuniones.add(crearReunion());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea Ingresar Otra Reunion?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            bandera = (respuesta == JOptionPane.YES_OPTION);
        }
    }

    // Método para agregar un asistente a una reunión
    public void agregarAsistenteAReunion() {
        String descripcionReunion = ingresarStringMensaje("Ingrese la descripción de la reunión:");
        Reunion reunionSeleccionada = null;
        for (Reunion reunion : reuniones) {
            if (reunion.getDescripcion().equalsIgnoreCase(descripcionReunion)) {
                reunionSeleccionada = reunion;
                break;
            }
        }

        if (reunionSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Reunión no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String telefonoContacto = ingresarStringMensaje("Ingrese el teléfono del asistente:");

        Contacto contactoSeleccionado = null;
        for (Contacto contacto : contactos) {
            if (contacto.getTelefono().equals(telefonoContacto)) {
                contactoSeleccionado = contacto;
                break;
            }
        }

        if (contactoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Asistente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        reunionSeleccionada.agregarContactoAsistente(contactoSeleccionado);
        JOptionPane.showMessageDialog(null, "Asistente añadido a la reunión", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para eliminar un asistente de una reunión
    public void eliminarAsistenteDeReunion() {
        String descripcionReunion = ingresarStringMensaje("Ingrese la descripción de la reunión:");
        Reunion reunionSeleccionada = null;
        for (Reunion reunion : reuniones) {
            if (reunion.getDescripcion().equalsIgnoreCase(descripcionReunion)) {
                reunionSeleccionada = reunion;
                break;
            }
        }

        if (reunionSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Reunión no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String telefonoContacto = ingresarStringMensaje("Ingrese el teléfono del asistente:");
        reunionSeleccionada.eliminarContactoAsistente(telefonoContacto);
        JOptionPane.showMessageDialog(null, "Asistente eliminado de la reunión", "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para eliminar una reunion
    public void eliminarReunion(String descripcion) {
        for (Reunion reunion : reuniones) {
            if (reunion.getDescripcion().equals(descripcion)) {
                reuniones.remove(reunion);
            }
        }
    }

    /**
     * MÉTODOS PROPIOS DE LOS GRUPOS
     */

    // Método para crear un grupo
    private Grupo crearGrupo() {
        Categoria[] categorias = { Categoria.AMIGOS, Categoria.FAMILIA, Categoria.FIESTA, Categoria.OFICINA };
        String nombre = ingresarStringMensaje("Ingrese el nombre del grupo: ");
        Categoria categoria = (Categoria) JOptionPane.showInputDialog(
                null,
                "Seleccione una opción",
                "Categoria",
                JOptionPane.PLAIN_MESSAGE,
                null,
                categorias,
                categorias[0]);
        Grupo grupo = new Grupo(nombre, categoria);
        return grupo;
    }

    // Método que permite ingresar varios grupos a la agenda
    public void ingresarGrupos() {
        boolean bandera = true;
        while (bandera) {
            grupos.add(crearGrupo());
            int respuesta = JOptionPane.showConfirmDialog(
                    null,
                    "¿Desea Ingresar Otro Grupo?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            bandera = (respuesta == JOptionPane.YES_OPTION);
        }
    }

    // Método para agregar un contacto específico a un grupo específico
    public void agregarParticipanteAGrupo() {
        String nombreGrupo = ingresarStringMensaje("Ingrese el nombre del grupo al que desea añadir el contacto:");
        Grupo grupoSeleccionado = null;
        for (Grupo grupo : grupos) {
            if (grupo.getNombre().equalsIgnoreCase(nombreGrupo)) {grupoSeleccionado = grupo; break;}
        }

        if (grupoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Grupo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String telefonoContacto = ingresarStringMensaje("Ingrese el teléfono del contacto que desea añadir al grupo:");

        Contacto contactoSeleccionado = null;
        for (Contacto contacto : contactos) {
            if (contacto.getTelefono().equals(telefonoContacto)) {contactoSeleccionado = contacto; break;}
        }

        if (contactoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Contacto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        grupoSeleccionado.getParticipantes().add(contactoSeleccionado);
        JOptionPane.showMessageDialog(null, "Contacto añadido al grupo", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    //Método para eliminar un participante de un grupo
    public void eliminarParticipanteGrupo() {
        String nombreGrupo = ingresarStringMensaje("Ingrese el nombre del grupo al que desea eliminar del grupo:");
        Grupo grupoSeleccionado = null;
        for (Grupo grupo : grupos) {
            if (grupo.getNombre().equalsIgnoreCase(nombreGrupo)) {
                grupoSeleccionado = grupo;
                break;
            }
        }

        if (grupoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Grupo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String telefonoContacto = ingresarStringMensaje(
                "Ingrese el teléfono del contacto que desea eliminar del grupo:");

        Contacto contactoSeleccionado = null;
        for (Contacto contacto : contactos) {
            if (contacto.getTelefono().equals(telefonoContacto)) {
                contactoSeleccionado = contacto;
                break;
            }
        }

        if (contactoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Contacto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        grupoSeleccionado.getParticipantes().remove(contactoSeleccionado);
        JOptionPane.showMessageDialog(null, "Contacto eliminado del grupo", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para eliminar un grupo
    public void eliminarGrupo(String nombre) {
        for (Grupo grupo : grupos) {
            if (grupo.getNombre().equals(nombre)) {
                grupos.remove(grupo);
            }
        }
    }

    /**
     * MÉTODOS DE TIPO INPUT
     */
    
    // Método para recibir input de tipo String
    public static String ingresarStringMensaje(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }

    // Método para ingresar un entero
    public static int ingresarEntero(String mensaje) {
        int entero = 0;
        boolean repetir = true;
        while (repetir) {
            try {
                String stringentero = ingresarStringMensaje(mensaje);
                entero = Integer.parseInt(stringentero);
                repetir = false;
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null, "Error, no ingreso real");
                repetir = true;
            }
        }
        return entero;
    }
    

    //Método que permite ingresar un número entero dentro de un rango
    public static int ingresarEnteroRango(String mensaje, int valorMinimo, int valorMaximo) {
        boolean repetir = true;
        int entero = 0;
        while (repetir) {
            entero = ingresarEntero(mensaje);
            if (entero <= valorMinimo || entero >= valorMaximo) {
                JOptionPane.showMessageDialog(null, "El valor no esta entre " + valorMinimo + "y " + valorMaximo);
            } else {
                repetir = false;
            }
        }
        return entero;
    }
}