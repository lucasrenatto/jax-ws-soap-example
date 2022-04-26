package entidades;

import lombok.Data;

@Data
public class Veiculo {
    private Integer id;
    private String marca;
    private String modelo;
    private Integer anoFabric;
    private Integer anoModel;
    private Double preco;
    private TipoDeVeiculo tipoDeVeiculo;

}
