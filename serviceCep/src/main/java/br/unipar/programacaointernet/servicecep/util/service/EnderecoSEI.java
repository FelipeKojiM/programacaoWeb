package br.unipar.programacaointernet.servicecep.util.service;

import br.unipar.programacaointernet.servicecep.util.model.Endereco;
import br.unipar.programacaointernet.servicecep.util.model.Usuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface EnderecoSEI {

    @WebMethod
    String boasvindas(@WebParam(name = "nome")String nome);


    @WebMethod
    Endereco consultarEndereco(@WebParam(name = "idEndereco") Long idEndereco);

    Endereco consultarEnderecoPorCep(String cep);
}
