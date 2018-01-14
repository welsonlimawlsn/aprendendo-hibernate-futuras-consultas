package br.com.welson.hibernate.veiculos.dominio;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tab_veiculo")
public class Veiculo {

    private Long codigo;
    private Proprietario proprietario;
    private String fabricante;
    private String modelo;
    private Integer anoFabricacao;
    private Integer anoModelo;
    private BigDecimal valor;
    private TipoCombustivel tipoCombustivel;
    private Date dataCadastro;
    private String especificacoes;
    private Set<Acessorio> acessorios = new HashSet<>();
    private boolean editando = false;

    public Veiculo() {

    }

    public Veiculo(Proprietario proprietario, String fabricante, String modelo, Integer anoFabricacao, Integer anoModelo, BigDecimal valor, TipoCombustivel tipoCombustivel, Date dataCadastro, String especificacoes) {
        this.proprietario = proprietario;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.valor = valor;
        this.tipoCombustivel = tipoCombustivel;
        this.dataCadastro = dataCadastro;
        this.especificacoes = especificacoes;
    }

    @Id//            Não obrigatório no caso AUTO
    @GeneratedValue(strategy = GenerationType.AUTO)//(generator = "inc")// SELECT MAX + 1
    //@GenericGenerator(name = "inc", strategy = "increment")SELECT MAX + 1
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }


    @ManyToOne
    @JoinColumn(name = "cod_proprietario")
    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    @Column(length = 60, nullable = false)
    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @Column(length = 60, nullable = false)
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Column(name = "ano_fabricacao", nullable = false)
    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    @Column(name = "ano_modelo", nullable = false)
    public Integer getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
        this.anoModelo = anoModelo;
    }

    @Column(precision = 10, scale = 2, nullable = true)
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Column(name = "tipo_combustivel", nullable = false)
    @Enumerated(EnumType.STRING)
    public TipoCombustivel getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    @Transient
    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_cadastro", nullable = false)
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Transient
    public String getDescricao() {
        return fabricante + " " + modelo + " " + anoFabricacao + "/" + anoModelo + " por apenas " + valor;
    }

    @Lob
    public String getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(String especificacoes) {
        this.especificacoes = especificacoes;
    }

    @ManyToMany
    @JoinTable(name = "veiculo_acessorios",
        joinColumns = @JoinColumn(name = "cod_veiculo"),
        inverseJoinColumns = @JoinColumn(name = "cod_acessorio"))
    public Set<Acessorio> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(Set<Acessorio> acessorios) {
        this.acessorios = acessorios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Veiculo veiculo = (Veiculo) o;

        return codigo != null ? codigo.equals(veiculo.codigo) : veiculo.codigo == null;
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }
}
