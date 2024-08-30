pacakage co.edu.uniquindio.poo;

public class Agenda{
    public Collection<Contacto> contactos;
    public Collection<Grupo> grupos; 
    public Collection<Reunion> reuniones;

    public Agenda(){
        contactos = new LinkedList<>();
        grupos = new LinkedList<>();
        reuniones = new LinkedList<>();
    }


    public boolean verificarContacto(String telefono, String nombre){
        boolean centinela = false;
        for (Contacto contacto : contactos){
            if(contacto.getTelefono().equals(telefono) && contacto.getNombre().equals(nombre)) {
                centinela = true;
            }
        }
        return centinela;
    }


    public void agregarContacto(Contacto contacto){
        if(!verificarContacto(contacto.getTelefono(), contacto.getNombre())){
            contactos.add(contacto);
        }
    }


    public void eliminarContacto(String telefono){
        for (Contacto contacto : contactos){
            if(contacto.getTelefono().equals(telefono)){
                contactos.remove(contacto);
            }
        }
    }

}
