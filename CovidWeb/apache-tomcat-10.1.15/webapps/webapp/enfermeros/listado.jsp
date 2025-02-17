<%@page import="org.aartaraz.webapp.consultas.RolesDAO" %>
<%@page import="org.aartaraz.webapp.consultas.PersonasDAO" %>
<%@page import="org.aartaraz.webapp.entidades.Persona" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Listado de enfermeros</title>
    <%@include file="../comunes/css.jsp" %>

</head>
<body>

<%@include file="../comunes/menu.jsp" %>

<%
    ArrayList<Persona> personas = PersonasDAO.listar_enfemeros();
%>
<div class="container">
    <div class="row">
        <div class="col-lg-12 mt-3">
            <h1 class="text-center">Listado de enfermeros</h1>
        </div>
    </div>

    <%
        if ((String) session.getAttribute("usuarioInsertado") != "") {
    %>
    <div class="row">
        <div class="col-lg-12 mt-3">
            <div class="alert alert-info">
                <%=(String) session.getAttribute("usuarioInsertado")%>
            </div>
        </div>
    </div>
    <%
        }
    %>

    <div class="row">
        <div class="col-lg-12 mt-3">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>DNI</th>
                    <th>Tel�fono</th>
                    <th>Email</th>
                    <th>Opciones</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (int i = 0; i < personas.size(); i++) {
                %>
                <tr>
                    <td><%=personas.get(i).getId()%>
                    </td>
                    <td><%=personas.get(i).getNombre()%>
                    </td>
                    <td><%=personas.get(i).getDni()%>
                    </td>
                    <td><%=personas.get(i).getTelefono()%>
                    </td>
                    <td><%=personas.get(i).getEmail()%>
                    </td>

                    <td>
                        <a href="asignar_paciente?id=<%=personas.get(i).getId() %>"
                           class="btn btn-primary text-white"><i
                                class="fa fa-users"></i> Pacientes
                        </a>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>

</div>

<div class="modal fade" id="modalEliminar" tabindex="-1"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Eliminar
                    persona</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <h6>�Est�s seguro que deseas eliminar esta persona?</h6>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Cerrar
                </button>
                <button type="button" class="btn btn-primary">Confirmar</button>
            </div>
        </div>
    </div>
</div>


<%@include file="../comunes/js.jsp" %>
</body>
</html>
