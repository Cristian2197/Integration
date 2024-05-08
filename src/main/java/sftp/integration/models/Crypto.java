package sftp.integration.models;

import java.util.Map;

public class Crypto {
    public String coin;
    public String wallet;
    public String network;

    public Crypto(String coin, String wallet, String network) {
        this.coin = coin;
        this.wallet = wallet;
        this.network = network;
    }

    public Crypto() { }

    public Crypto(Map<String, Object> cryptoMapped){
        this.coin = (String) cryptoMapped.get("coin");
        this.wallet = (String) cryptoMapped.get("wallet");
        this.network = (String) cryptoMapped.get("network");
    }
    public String getCoin() {
        return coin;
    }
    public void setCoin(String coin) {
        this.coin = coin;
    }
    public String getWallet() {
        return wallet;
    }
    public void setWallet(String wallet) {
        this.wallet = wallet;
    }
    public String getNetwork() {
        return network;
    }
    public void setNetwork(String network) {
        this.network = network;
    }
    
}
