package br.com.project.javafxmvc.model.domain;

public class Cliente {

    private String nome;
    private String cpf;
    private String telefone;
    private int codigo;

    public Cliente(String nome, String cpf, String telefone, int codigo) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.codigo = codigo;
    }

    public Cliente() {

    }

     public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
