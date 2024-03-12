package br.unipar.programacaointernet.servicecep.util.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity

public class Usuario {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)

    private long id;

    @Column(length = 20, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false)
    private String login;

    @Column(length = 20, nullable = false)
    private String senha;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
