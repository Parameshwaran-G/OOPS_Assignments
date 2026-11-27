
import java.util.Scanner;

public class PaymentSystem {
    static Payment getPayment(int paymentType){
        if(paymentType > 3 || paymentType < 1){
            throw new IllegalArgumentException("Invalid Payment Type");
        }
        if(paymentType == 1){
            return new CreditCardPayment();
        }
        else if(paymentType == 2){
            return new UpiPayment();
        }

        return new WalletPayment();
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int paymentType = sc.nextInt();
            double amount = sc.nextDouble();
            Payment payment;

            payment = getPayment(paymentType);

            double fee = payment.calculateFee(amount);

            System.out.printf("Payment Amount: %.2f%n",amount);
            System.out.printf("Transaction Fee: %.2f%n",fee);
            System.out.printf("Final Amount: %.2f%n",amount+fee);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

interface Payment{

     static void validatePaymentAmount(double paymentAmount) {
        if (!Double.isFinite(paymentAmount) ||
            paymentAmount <= 0.0 ||
            paymentAmount > 1000000.0) {

            throw new IllegalArgumentException("Invalid payment amount");
        }
    }

    public double calculateFee(double amoount);
}

class CreditCardPayment implements Payment{
    @Override 
    public double calculateFee(double amount){
        Payment.validatePaymentAmount(amount);
        return amount * 0.2;
    }
}

class UpiPayment implements Payment{
    @Override 
    public double calculateFee(double amount){
        Payment.validatePaymentAmount(amount);
        return amount * 0.05;
    }
}

class WalletPayment implements Payment{
    @Override 
    public double calculateFee(double amount){
        Payment.validatePaymentAmount(amount);
        return amount * 0.1;
    }
}

