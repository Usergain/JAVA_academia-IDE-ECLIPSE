package org.aartaraz.webapp.jsf3.services;

import jakarta.annotation.Resource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.aartaraz.webapp.jsf3.entities.Categoria;
import org.aartaraz.webapp.jsf3.entities.Producto;
import org.aartaraz.webapp.jsf3.repositories.CrudRepository;
import org.aartaraz.webapp.jsf3.repositories.ProductoRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"USER", "ADMIN"})
public class ProductoServiceImpl implements ProductoService {

    @Inject
    private ProductoRepository repository;

    @Inject
    private CrudRepository<Categoria> categoriaRepository;

    @Resource
    private SessionContext ctx;

    @Override
    @PermitAll
    public List<Producto> listar() {
        Principal usuario=ctx.getCallerPrincipal(); //usuario logeado
        String username=usuario.getName();
        System.out.println("username: " + username);
        if(ctx.isCallerInRole("ADMIN")){
            System.out.println("Hola soy un administrador!");
        } else if (ctx.isCallerInRole("USER")) {
            System.out.println("Hola soy un usuario normal!");
        }else{
            System.out.println("Hola soy un usuario anonimo!");
//            throw new SecurityException("Lo sentimos no tienes permisos para acceder a esta pagina");
        }
        return repository.listar();
    }

    @RolesAllowed({"USER", "ADMIN"})
    @Override
    public Optional<Producto> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public void guardar(Producto producto) {
        repository.guardar(producto);
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @RolesAllowed({"USER", "ADMIN"})
    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.listar();
    }

    @RolesAllowed({"USER", "ADMIN"})
    @Override
    public Optional<Categoria> porIdCategorias(Long id) {
        return Optional.ofNullable(categoriaRepository.porId(id));
    }

    @RolesAllowed({"USER", "ADMIN"})
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        return repository.buscarPorNombre(nombre);
    }
}
