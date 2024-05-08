package sftp.integration.processors;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import sftp.integration.models.User;

@Component
public class JsonToCsvProcessor implements Processor{

    Date nowDate = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    String formatedDate = format.format(nowDate);

    @Override
    public void process(Exchange exchange) throws Exception {
        String json = exchange.getIn().getBody().toString();
        
        Gson gson = new Gson();
        User[] allUsers = gson.fromJson(json, User[].class);

        String[] headerNames = {
            "id", "firstName", "lastName", "maidenName", "age", "gender", "email", "phone", "username",
            "password", "birthDate", "image", "bloodGroup", "height", "weight", "eyeColor", "hair",
            "color", "type", "domain", "ip", "address", "city", "coordinates", "lat",
            "lng", "postalCode", "state", "macAddress", "university", "bank", "cardExpire",
            "cardNumber", "cardType", "currency", "iban", "company", "department", "name", "title",
            "ein", "ssn", "userAgent", "crypto", "coin", "wallet"
        };

        List<String> headers = Arrays.asList(headerNames);
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.join(",", headers));

        for (int i = 0; i < allUsers.length; i++) {
            User user = allUsers[i];
            String[] rowData = {
                String.valueOf(user.getId()),
                user.getFirstName(),
                user.getLastName(),
                user.getMaidenName(),
                String.valueOf(user.getAge()),
                user.getGender(),
                user.getEmail(),
                user.getPhone(),
                user.getUsername(),
                user.getPassword(),
                user.getBirthDate(),
                user.getImage(),
                user.getBloodGroup(),
                String.valueOf(user.getHeight()),
                String.valueOf(user.getWeight()),
                user.getEyeColor(),
                user.getHair().getColor(),
                user.getHair().getType(),
                user.getDomain(),
                user.getIp(),
                user.getAddress().getAddress(),
                user.getAddress().getCity(),
                String.valueOf(user.getAddress().getCoordinates().getLat()),
                String.valueOf(user.getAddress().getCoordinates().getLng()),
                user.getAddress().getPostalCode(),
                user.getAddress().getState(),
                user.getMacAddress(),
                user.getUniversity(),
                user.getBank().getCardExpire(),
                user.getBank().getCardNumber(),
                user.getBank().getCardType(),
                user.getBank().getCurrency(),
                user.getBank().getIban(),
                user.getCompany().getAddress().getAddress(),
                user.getCompany().getAddress().getCity(),
                String.valueOf(user.getCompany().getAddress().getCoordinates().getLat()),
                String.valueOf(user.getCompany().getAddress().getCoordinates().getLng()),
                user.getCompany().getAddress().getPostalCode(),
                user.getCompany().getAddress().getState(),
                user.getCompany().getDepartment(),
                user.getCompany().getName(),
                user.getCompany().getTitle(),
                user.getEin(),
                user.getSsn(),
                user.getUserAgent(),
                user.getCrypto().getCoin(),
                user.getCrypto().getWallet(),
                user.getCrypto().getNetwork()
            };
            List<String> row = Arrays.asList(rowData);
            
            sb.append("\n");
            sb.append(String.join(",", row));
        }

        String csv = sb.toString();

        exchange.setProperty("ETLCsv", csv);
    }
    
}
