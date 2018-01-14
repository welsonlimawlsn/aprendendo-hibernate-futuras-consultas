package br.com.welson.hibernate.veiculos.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tab_proprietario")
public class Proprietario {

    private Long codigo;
    private String nome;
    private List<Telefone> telefone = new ArrayList<>();
    private String email;
    private List<Veiculo> veiculos;

    public Proprietario(){

    }

    public Proprietario(String nome, String telefone, String email) {
        this.nome = nome;
        this.email = email;
    }

    @Id
    @GeneratedValue
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @Column(length = 60, nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /*@ElementCollection
    @CollectionTable(name = "proprietario_telefone",
        joinColumns = @JoinColumn(name = "cod_proprietario"))
    @Column(name = "numero_telefone", length = 20, nullable = false)
    public List<String> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<String> telefone) {
        this.telefone = telefone;
    }*/

    @ElementCollection
    @CollectionTable(name = "proprietario_telefones", joinColumns = @JoinColumn(name = "cod_proprietario"))
    @AttributeOverrides({@AttributeOverride(name = "numero", column = @Column(name = "num_telefone", length = 20, nullable = false))})
    public List<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<Telefone> telefone) {
        this.telefone = telefone;
    }

    @Column(length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "proprietario")
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Proprietario that = (Proprietario) o;

        return codigo != null ? codigo.equals(that.codigo) : that.codigo == null;
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }
}
