package br.unipar.programacaointernet.servicecep.util;

import br.unipar.programacaointernet.servicecep.util.dao.EnderecoDAO;
import br.unipar.programacaointernet.servicecep.util.dao.EnderecoDaoimpl;
import br.unipar.programacaointernet.servicecep.util.dao.UsuarioDAO;
import br.unipar.programacaointernet.servicecep.util.dao.UsuarioDaoimpl;
import br.unipar.programacaointernet.servicecep.util.model.Endereco;
import br.unipar.programacaointernet.servicecep.util.model.Usuario;
import br.unipar.programacaointernet.servicecep.util.util.EntityManagerUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main (String[] arg){

        try{
            EntityManagerUtil.getEntityManagerFactory();
            System.out.println("Pressione \n" +
                    "(1) para cadastrar Usuarios\n" +
                    "(2) para editar Usuarios\n" +
                    "(3) para deletar Usuarios\n" +
                    "(4) para buscar todos os Usuarios\n" +
                    "(5) para salvar um endereco\n");
            Scanner scanner = new Scanner(System.in);
            int x = scanner.nextInt();

            switch (x){
                case 1: salvarUsuario();
                    return;
                case 2: editarUsuario();
                    return;
                case 3:deletarUsuario();
                    return;
                case 4:buscarTodosUsuarios();
                    return;
                case 5:salvarEndereco();
                    return;
            }

            EntityManagerUtil.closeEntityManagerFactory();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static void salvarUsuario(){
        UsuarioDAO usuarioDao = new UsuarioDaoimpl(EntityManagerUtil.getManager());
        Usuario usuario = new Usuario();

        usuario.setNome("Zézinho");
        usuario.setLogin("zezinho123");
        usuario.setSenha("123");

        usuarioDao.save(usuario);
    }

    private static void editarUsuario() {
        UsuarioDAO usuarioDao = new UsuarioDaoimpl(EntityManagerUtil.getManager());
        Usuario usuario = usuarioDao.FindById(1L);

        usuario.setNome("Bruninho");
        usuario.setLogin("bruninho123");
        usuario.setSenha("321");

        usuarioDao.update(usuario);
    }

    private static void deletarUsuario(){
        UsuarioDAO usuarioDAO = new UsuarioDaoimpl(EntityManagerUtil.getManager());

         Usuario usuario = usuarioDAO.FindById(1L);
         usuarioDAO.delete(usuario);

    }

    private static void buscarTodosUsuarios(){
        UsuarioDAO usuarioDAO = new UsuarioDaoimpl(EntityManagerUtil.getManager());

        List<Usuario> usuarios = usuarioDAO.findAll();

        for(Usuario usuario : usuarios){
            System.out.println("Usuario " + usuario.getNome() + " encontrado com sucesso!");
        }
    }

    private static void buscarUsuariosPorId(){
        UsuarioDAO usuarioDAO = new UsuarioDaoimpl(EntityManagerUtil.getManager());
        //List<Usuario> usuarios = (List<Usuario> UsuarioDAO.FindById(1l));

            System.out.println("Usuarios encontrados com sucesso!");
    }

    private static Endereco getViaCep(String cep) throws Exception{
        URL url = new URL("Http://viacep.com.br/ws/"+cep.replace("-", "").replace("-", "")+"/xml/");

        BufferedReader in = new BufferedReader (new InputStreamReader(url.openStream()));

        String inputLine;
        String result = "";
        while ((inputLine = in.readLine()) != null) result += inputLine;

        in.close();
        return Endereco.unmarshalFromString(result);
    }

    private static void salvarEndereco(){
        try{
            EnderecoDAO enderecoDAO = new EnderecoDaoimpl( EntityManagerUtil.getManager());

            enderecoDAO.save(getViaCep("85813090"));

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
