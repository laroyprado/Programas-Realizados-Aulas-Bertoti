package com.projetobancodedados.Bertoti.animal;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "animal")
@Entity(name = "animal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")


public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome")
    private String nome;


    @Column(name = "especie")
    private String especie;

    @Column(name = "descricao")
    private String descricao;

    @Column(name="idade_meses")
    private Integer idade_meses;

    @Column(name="cor")
    private String cor;

    @Column(name="linkfoto")
    private String linkFoto;

    @Column(name="cidade")
    private String cidade;


    public Animal(AnimalRequestDTO data){
        this.nome = data.nome();
        this.especie = data.especie();
        this.descricao = data.descricao();
        this.idade_meses = data.idade_meses();
        this.cor=data.cor();
        this.linkFoto=data.linkFoto();
        this.cidade=data.cidade();
    }

}
