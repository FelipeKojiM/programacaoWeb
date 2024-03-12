package br.unipar.programacaointernet.servicecep.util.service;
import br.unipar.programacaointernet.servicecep.util.model.Usuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface UsuarioSEI {

    @WebMethod
    String boasvindas(@WebParam(name = "nome")String nome);


    @WebMethod
    Usuario consultarUsuario(@WebParam(name = "idUsuario") Long idUsuario);

}
