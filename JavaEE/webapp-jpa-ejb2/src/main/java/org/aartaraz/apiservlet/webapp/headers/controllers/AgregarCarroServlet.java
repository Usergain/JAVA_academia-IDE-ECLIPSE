package org.aartaraz.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aartaraz.apiservlet.webapp.headers.configs.ProductoServicePrincipal;
import org.aartaraz.apiservlet.webapp.headers.models.Carro;
import org.aartaraz.apiservlet.webapp.headers.models.ItemCarro;
import org.aartaraz.apiservlet.webapp.headers.models.entities.Producto;
import org.aartaraz.apiservlet.webapp.headers.services.ProductoService;


import java.io.IOException;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {
    @Inject
    @ProductoServicePrincipal
    private ProductoService service;
    //proxy CDI, cada uno con su hilo, por eso que es Serializable(magic number)
    @Inject
    private Carro carro;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Optional<Producto> producto = service.porId(id);
        if (producto.isPresent()) {
            ItemCarro item = new ItemCarro(1, producto.get());
            //HttpSession session = req.getSession();
            // Carro carro = (Carro) session.getAttribute("carro");
            carro.addItemCarro(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
