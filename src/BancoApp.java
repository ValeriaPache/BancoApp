
class  SaldoInsuficiente extends Exception{
    public SaldoInsuficiente(String mensaje){
        super(mensaje); //acceder o llamar funciones del mismo padre de un objeto
    }
}

class MontoNegativo extends Exception{
    public MontoNegativo(String mensaje){
        super(mensaje);
    }
}
class Cuenta{
    private String titular;
    private double saldo;

    //Constructor
    public Cuenta(String titular, double saldoInicial){
        this.titular = titular;
        this.saldo = saldoInicial;
    }
    //Getters
    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    //Método para retirar dinero
    public void retirarDinero(double cantidad) throws SaldoInsuficiente{
        if (cantidad > saldo){
            throw new SaldoInsuficiente("Saldo insuficiente para retirar.");
        }
        saldo -= cantidad;
        System.out.println("Se ha retirado $" + cantidad + " en la cuenta de " + titular);
    }
    //Método para depositar dinero
    public  void depositarDinero(double cantidad) throws MontoNegativo{
        if (cantidad < 0){
            throw new MontoNegativo("El monto a depositar no puede ser negativo.");
        }
        saldo += cantidad;
        System.out.println("Se ha depositado $" + cantidad + " en la cuenta de " + titular);
    }
    //Método para transferir dinero entre cuentas
    public void transferirDinero(Cuenta destino,double cantidad) throws SaldoInsuficiente{
        if (cantidad > saldo){
            throw new SaldoInsuficiente("Saldo insuficiente para realizar la transferencia.");
        }
        saldo -= cantidad;
        destino.saldo += cantidad;
        System.out.println("Se han transferido $" + cantidad + " de la cuenta de " + titular + " a la cuenta de " + destino.getTitular());
    }
}
public class BancoApp {
    public static void main(String[] args) {
        //Cuentas
        Cuenta cuenta1 = new Cuenta("Valery",700);
        Cuenta cuenta2 = new Cuenta("Sofia", 400);


        try {
            // Intentar retirar dinero
            cuenta1.retirarDinero(200);
            // Intentar depositar dinero
            cuenta2.depositarDinero(300);
            // Intentar transferir dinero
            cuenta1.transferirDinero(cuenta2, 150);
            // Intentar retirar una cantidad mayor al saldo
            cuenta2.retirarDinero(500);
        } catch (SaldoInsuficiente | MontoNegativo e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Mostrar los saldos finales de ambas cuentas
        System.out.println("Saldo final de la cuenta de Valery: $" + cuenta1.getSaldo());
        System.out.println("Saldo final de la cuenta de Sofia: $" + cuenta2.getSaldo());

    }
}