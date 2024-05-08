package sftp.integration.models;

import java.util.Map;

public class Bank {
    public String cardExpire;
    public String cardNumber;
    public String cardType;
    public String currency;
    public String iban;

    public Bank() { }

    public Bank(String cardExpire, String cardNumber, String cardType, String currency, String iban) {
        this.cardExpire = cardExpire;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.currency = currency;
        this.iban = iban;
    }

    public Bank(Map<String, Object> bankMapped) {
        this.cardExpire = (String) bankMapped.get("cardExpire");
        this.cardNumber = (String) bankMapped.get("cardNumber");
        this.cardType = (String) bankMapped.get("cardType");
        this.currency = (String) bankMapped.get("currency");
        this.iban = (String) bankMapped.get("iban");
    }
    public String getCardExpire() {
        return cardExpire;
    }
    public void setCardExpire(String cardExpire) {
        this.cardExpire = cardExpire;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getIban() {
        return iban;
    }
    public void setIban(String iban) {
        this.iban = iban;
    }
}
