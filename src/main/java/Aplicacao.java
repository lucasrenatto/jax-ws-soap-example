import jakarta.xml.ws.Endpoint;
import servicos.*;

public class Aplicacao {
    public static void main(String[] args) {
        Endpoint.publish(
                "http://localhost:8080/calculadora-servidor",
                new Calculadora()
        );

        Endpoint.publish(
                "http://localhost:8080/categoria-servidor",
                new CategoriaServico()
        );

        Endpoint.publish(
                "http://localhost:8080/produto-servidor",
                new ProdutoServico()
        );

     Endpoint.publish(
              "http://localhost:8080/veiculo-servidor",
             new VeiculoServico()
  );

        Endpoint.publish(
                "http://localhost:8080/tipo-servidor",
                new TipoDeVeiculoServico()
        );
    }
}
