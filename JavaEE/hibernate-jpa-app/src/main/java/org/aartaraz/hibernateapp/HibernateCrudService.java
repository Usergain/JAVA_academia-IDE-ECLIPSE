package org.aartaraz.hibernateapp;

import jakarta.persistence.EntityManager;
import org.aartaraz.hibernateapp.entity.Cliente;
import org.aartaraz.hibernateapp.services.ClienteService;
import org.aartaraz.hibernateapp.services.ClienteServiceImpl;
import org.aartaraz.hibernateapp.util.JpaUtil;

import java.util.List;
import java.util.Optional;

public class HibernateCrudService {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        ClienteService service = new ClienteServiceImpl(em);

        System.out.println("========== listar ==========");
        List<Cliente> clientes = service.listar();
        clientes.forEach(System.out::println);

        System.out.println("========== obtener por id ==========");
        Optional<Cliente> optionalCliente = service.porId(1L);
        optionalCliente.ifPresent(System.out::println);

        System.out.println("========== insertar nuevo cliente ==========");
        Cliente cliente = new Cliente();
        cliente.setNombre("Luci");
        cliente.setApellido("Mena");
        cliente.setFormaPago("paypal");

        service.guardar(cliente);
        System.out.println("cliente guardado con exito ");
        service.listar().forEach(System.out::println);

        System.out.println("========== editar cliente ==========");
        Long id = cliente.getId();
        optionalCliente = service.porId(id);
        optionalCliente.ifPresent(c -> {
            c.setFormaPago("mercado pago");
            service.guardar(c);
            System.out.println("cliente editado con exito!");
            service.listar().forEach(System.out::println);
        });

        System.out.println("========== eliminar cliente ==========");
        id = cliente.getId();
        optionalCliente = service.porId(id);
        optionalCliente.ifPresent(c -> {
            service.eliminar(c.getId());
            /*if (optionalCliente.isPresent()) {
            service.eliminar(id);
            }*/
            System.out.println("cliente eliminado con exito!");
            service.listar().forEach(System.out::println);
        });


        em.close();
    }
}

