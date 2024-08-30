package co.edu.uniquindio.poo;

import java.util.Collection;

import java.util.LinkedList;

public class Grupo {
   String nombre;
   Categoria categoria;
   public Collection<Contacto> contactos;
 
   public Grupo(String nombre, Categoria categoria) {
      this.nombre = nombre;
      this.categoria = categoria;
      this.contactos = new LinkedList<>();
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public boolean verificarContacto(String telefono, String nombre) {
      boolean centinela = false;
      for (Contacto contacto : contactos) {
         if (contacto.getTelefono().equals(telefono) && contacto.getNombre().equals(nombre)) {
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
      for(Contacto contacto : contactos){
         if(contacto.getTelefono().equals(telefono)){
            contactos.remove(contacto);
         }
      }
   
   }

   public boolean limiteIntegrantes(Collection<Contacto> contactos){
      boolean superaLimite;
      if(contactos.size() > 5){
         superaLimite = true;
      }else{
         superaLimite = false;
      }
      return superaLimite;
   }

   @Override
   public String toString() {
      return "Grupo [nombre=" + nombre + ", categoria=" + categoria + ", contactos=" + contactos + "]";
   }

}
