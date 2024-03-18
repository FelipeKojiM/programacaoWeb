package br.unipar.programacaointernet.servicecep.util.service;
import br.unipar.programacaointernet.servicecep.util.Main;
import br.unipar.programacaointernet.servicecep.util.dao.EnderecoDAO;
import br.unipar.programacaointernet.servicecep.util.dao.EnderecoDaoimpl;
import br.unipar.programacaointernet.servicecep.util.model.Endereco;
import br.unipar.programacaointernet.servicecep.util.util.EntityManagerUtil;
import jakarta.jws.WebService;

@WebService(endpointInterface = "br.unipar.programacaointernet.servicecep.util.service.EnderecoSEI")
public class EnderecoSIB implements EnderecoSEI {

    @Override
    public String boasvindas(String nome) {
        return "Bem Vindo(a) " + nome + "!";
    }

    @Override
    public Endereco consultarEndereco(Long idEndereco) {
        EnderecoDAO enderecoDao = new EnderecoDaoimpl(EntityManagerUtil.getManager());
        Endereco endereco = enderecoDao.FindById(idEndereco);
        return endereco;
    }
    @Override
    public Endereco consultarEnderecoPorCep(String cep) {
        EnderecoDAO enderecoDao = new EnderecoDaoimpl(EntityManagerUtil.getManager());
        Endereco endereco = enderecoDao.findByCep(cep);
        if (endereco == null){
            try {
                endereco = Main.getViaCep(cep);

                enderecoDao.save(endereco);

            }catch (Exception e){
                throw  new RuntimeException(e);
            }
        }
        return endereco;
    }
}