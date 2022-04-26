package entidades;

import lombok.Data;

@Data
public class Produto {
    private Integer id;
    private String nome;
    private Integer quantidade;
    private Double preco;
    private Categoria categoria;
}
