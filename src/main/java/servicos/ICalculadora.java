package servicos;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface ICalculadora {
    @WebMethod
    double somar (
            @WebParam(name = "x") double x,
            @WebParam(name = "y") double y
    );

    @WebMethod
    double subtrair (
            @WebParam(name = "x") double x,
            @WebParam(name = "y") double y
    );

    @WebMethod
    double multipicar (
            @WebParam(name = "x") double x,
            @WebParam(name = "y") double y
    );

    @WebMethod
    double dividir (
            @WebParam(name = "x") double x,
            @WebParam(name = "y") double y
    );
}
