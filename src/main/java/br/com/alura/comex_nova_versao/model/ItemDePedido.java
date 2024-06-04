package br.com.alura.comex_nova_versao.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Optional;

@Entity
@Table(name = "item_de_pedido")
public class ItemDePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    private Pedido pedido;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "desconto", scale = 2, nullable = true)
    private BigDecimal desconto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_desconto", nullable = true)
    private TipoDescontoItemPedido tipoDesconto;

    @Transient
    private BigDecimal total = BigDecimal.ZERO;

    public ItemDePedido() {
    }

    public ItemDePedido(Integer quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.precoUnitario = produto.getPrecoUnitario();

        if (quantidade >= 10) {
            this.tipoDesconto = TipoDescontoItemPedido.QUANTIDADE;
            this.desconto = new BigDecimal("0.10"); // 10%
        } else {
            this.tipoDesconto = TipoDescontoItemPedido.NENHUM;
            this.desconto = BigDecimal.ZERO;
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Optional<Produto> produto) {
        if (produto.isPresent()) {
        this.produto = produto.get();}
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public TipoDescontoItemPedido getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(TipoDescontoItemPedido tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public BigDecimal getTotal() {
        return this.produto.getPrecoUnitario().multiply(new BigDecimal(this.quantidade));
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getProdutoId() {
        return this.produto.getId();
    }
}