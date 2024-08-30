package co.edu.uniquindio.poo;

public class Grupo {
   String nombre;
   Enum categoria;
   Contacto[] contactos = new Contacto[5];
   

   public Grupo(String nombre, Enum categoria, Contacto[] contactos){
        this.nombre = nombre;
        this.categoria = categoria;
        this.contactos = contactos;
   }


   public String getNombre() {
      return nombre;
   }


   public void setNombre(String nombre) {
      this.nombre = nombre;
   }


   public Enum getCategoria() {
      return categoria;
   }


   public void setCategoria(Enum categoria) {
      this.categoria = categoria;
   }


   public Contacto[] getContactos() {
      return contactos;
   }


   public void setContactos(Contacto[] contactos) {
      this.contactos = contactos;
   }
   
}
