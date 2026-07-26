/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrador.prog2.service;

import integrador.prog2.entities.Usuario;
import integrador.prog2.enums.Rol;
import integrador.prog2.exception.EmailDuplicado;
import integrador.prog2.exception.EntidadNoEncontrada;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author black
 */
public class UsuarioService {

    private final DataService dataService;

    public UsuarioService(DataService dataService) {
        this.dataService = dataService;
    }

    public void crearUsuario(String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol)
            throws EmailDuplicado, IllegalArgumentException {
        if (nombre == null || nombre.trim().isEmpty() || mail == null || mail.trim().isEmpty()) {
            throw new IllegalArgumentException("Los campos no pueden estar vacios.");
        }
        // Regla de negocio critica: Unicidad de Mail recorriendo la coleccion
        for (Usuario user : dataService.getUsuarios()) {
            if (!user.isEliminado() && user.getMail().equalsIgnoreCase(mail.trim())) {
                throw new EmailDuplicado("El correo electronico ya esta registrado por un usuario activo.");
            }
        }

        Usuario nuevo = new Usuario(dataService.generarIdUsuario(), nombre.trim(), apellido.trim(), mail.trim(), celular, contrasenia, rol);
        dataService.getUsuarios().add(nuevo);
    }

    public List<Usuario> listarUsuariosActivos() {
        List<Usuario> activos = new ArrayList<>();
        for (Usuario user : dataService.getUsuarios()) {
            if (!user.isEliminado()) {
                activos.add(user);
            }
        }
        return activos;
    }

    public Usuario buscarPorId(Long id) throws EntidadNoEncontrada {
        for (Usuario user : dataService.getUsuarios()) {
            if (user.getId().equals(id) && !user.isEliminado()) {
                return user;
            }
        }
        throw new EntidadNoEncontrada("Usuario con ID " + id + " no encontrado o dado de baja.");
    }

    public void editarUsuario(Long id, String nuevoNombre, String nuevoApellido, String nuevoMail, String nuevoCelular, String nuevaContrasenia, Rol nuevoRol)
            throws EntidadNoEncontrada, EmailDuplicado, IllegalArgumentException {
        Usuario user = buscarPorId(id);

        if (nuevoNombre == null || nuevoNombre.trim().isEmpty() || nuevoMail == null || nuevoMail.trim().isEmpty()) {
            throw new IllegalArgumentException("Los campos no pueden estar vacios.");
        }

        // Validar unicidad de email excluyendo el propio ID en edicion
        for (Usuario u : dataService.getUsuarios()) {
            if (!u.isEliminado() && !u.getId().equals(id) && u.getMail().equalsIgnoreCase(nuevoMail.trim())) {
                throw new EmailDuplicado("El correo electronico ya esta en uso por otro usuario.");
            }
        }

        user.setNombre(nuevoNombre.trim());
        user.setApellido(nuevoApellido.trim());
        user.setMail(nuevoMail.trim());
        user.setCellular(nuevoCelular);
        user.setContrasenia(nuevaContrasenia);
        user.setRol(nuevoRol);
    }

    public void eliminarUsuario(Long id) throws EntidadNoEncontrada {
        Usuario user = buscarPorId(id);
        user.setEliminado(true);
    }
}
