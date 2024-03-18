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

    public static void main (String[] arg) {


               try {
                   EntityManagerUtil.getEntityManagerFactory();
                   System.out.println("Pressione: \n" +
                           "(1) para cadastrar Usuarios\n" +
                           "(2) para editar Usuarios\n" +
                           "(3) para deletar Usuarios\n" +
                           "(4) para buscar todos os Usuarios\n" +
                           "(5) para salvar um endereco\n" +
                           "(6) para buscar os enderecos salvos\n" +
                           "Digite apenas números válidos!: ");
                   Scanner scanner = new Scanner(System.in);
                   int x = scanner.nextInt();

                   switch (x) {
                       case 1:
                           salvarUsuario();
                       case 2:
                           editarUsuario();
                       case 3:
                           deletarUsuario();
                       case 4:
                           buscarTodosUsuarios();
                       case 5:
                           salvarEndereco();
                       case 6:
                           mostrarTodosEnderecos();
                   }

                   EntityManagerUtil.closeEntityManagerFactory();

               } catch (Exception e) {
                   System.out.println(e);
               }

    }

    private static void salvarUsuario(){
        UsuarioDAO usuarioDao = new UsuarioDaoimpl(EntityManagerUtil.getManager());
        Usuario usuario = new Usuario();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome: ");
        String nome = scanner.next();
        System.out.println("Login: ");
        String login = scanner.next();
        System.out.println("Senha: ");
        String senha = scanner.next();

        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);

        usuarioDao.save(usuario);
    }

    private static void editarUsuario() {
        UsuarioDAO usuarioDao = new UsuarioDaoimpl(EntityManagerUtil.getManager());
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID para deletar: ");
        Long id = scanner.nextLong();

        Usuario usuario = usuarioDao.FindById(id);

        System.out.println("Nome: ");
        String nome = scanner.next();
        System.out.println("Login: ");
        String login = scanner.next();
        System.out.println("Senha: ");
        String senha = scanner.next();

        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);

        usuarioDao.update(usuario);
    }

    private static void deletarUsuario(){
        UsuarioDAO usuarioDAO = new UsuarioDaoimpl(EntityManagerUtil.getManager());
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID para deletar: ");
        Long id = scanner.nextLong();

        Usuario usuario = usuarioDAO.FindById(id);
        usuarioDAO.delete(usuario);

    }

    private static void buscarTodosUsuarios(){
        UsuarioDAO usuarioDAO = new UsuarioDaoimpl(EntityManagerUtil.getManager());

        List<Usuario> usuarios = usuarioDAO.findAll();

        for(Usuario usuario : usuarios){
            System.out.println("Usuario " + usuario.getNome() + " encontrado com sucesso!");
        }
    }

    public static Endereco getViaCep(String cep) throws Exception{
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
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o Cep para cadastrar: ");
            String cep = scanner.next();

            enderecoDAO.save(getViaCep(cep));

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void mostrarTodosEnderecos(){
        EnderecoDAO enderecoDAO = new EnderecoDaoimpl(EntityManagerUtil.getManager());

        List<Endereco> enderecos = enderecoDAO.findAll();

        for(Endereco endereco : enderecos){
            System.out.println(endereco.toString());
        }
    }
}
