package com.projetobancodedados.Bertoti.animal;

public record AnimalRequestDTO(String nome,String especie,String descricao, Integer idade_meses,String cor,String linkFoto,String cidade) {
}
