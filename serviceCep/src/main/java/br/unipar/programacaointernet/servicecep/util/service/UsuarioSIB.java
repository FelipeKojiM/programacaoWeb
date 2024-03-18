package br.unipar.programacaointernet.servicecep.util.service;
import br.unipar.programacaointernet.servicecep.util.dao.UsuarioDAO;
import br.unipar.programacaointernet.servicecep.util.dao.UsuarioDaoimpl;
import br.unipar.programacaointernet.servicecep.util.model.Usuario;
import br.unipar.programacaointernet.servicecep.util.util.EntityManagerUtil;
import jakarta.jws.WebService;

@WebService(endpointInterface = "br.unipar.programacaointernet.servicecep.util.service.UsuarioSEI")
public class UsuarioSIB implements UsuarioSEI {

    @Override
    public String boasvindas(String nome) {
        return "Bem Vindo(a) " +  nome + "!";
    }

    @Override
    public Usuario consultarUsuario(Long idUsuario) {
        UsuarioDAO usuarioDAO = new UsuarioDaoimpl(EntityManagerUtil.getManager());
        System.out.println(usuarioDAO.FindById(idUsuario));
        Usuario usuario = usuarioDAO.FindById(idUsuario);
        return usuario;
    }
}
