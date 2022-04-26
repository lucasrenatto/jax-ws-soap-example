package servicos;

import jakarta.jws.WebService;

@WebService(endpointInterface = "servicos.ICalculadora")
public class Calculadora implements ICalculadora {
    @Override
    public double somar(double x, double y) {
        return x + y;
    }

    @Override
    public double subtrair(double x, double y) {
        return x - y;
    }

    @Override
    public double multipicar(double x, double y) {
        return x * y;
    }

    @Override
    public double dividir(double x, double y) {
        return x / y;
    }
}
