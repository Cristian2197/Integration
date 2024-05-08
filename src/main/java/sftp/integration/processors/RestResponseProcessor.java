package sftp.integration.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import sftp.integration.models.GenderAgeSummary;
import sftp.integration.models.GenderSummary;
import sftp.integration.models.User;
import sftp.integration.repository.GenderSummaryRepository;
import sftp.integration.services.GenderAgeSummaryServices;
import sftp.integration.services.GenderSummaryServices;

@Component
public class RestResponseProcessor implements Processor{
    private final GenderSummaryServices genderSummaryService;
    private final GenderAgeSummaryServices genderAgeSummaryService;

    @Autowired
    public RestResponseProcessor(GenderSummaryServices genderSummaryService,
                                 GenderAgeSummaryServices genderAgeSummaryService) {
        this.genderSummaryService = genderSummaryService;
        this.genderAgeSummaryService = genderAgeSummaryService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resUsers = (List<Map<String, Object>>)(gson.fromJson(exchange.getIn().getBody(String.class), Map.class)).get("users");
        
        String objectsArr = gson.toJson(resUsers);
        exchange.setProperty("json", objectsArr);

        List<User> users = new ArrayList<User>();
        for (Map<String, Object> user : resUsers) {
            @SuppressWarnings("unchecked")
            Map<String, Object> crypto = (Map<String, Object>) user.get("crypto");

            @SuppressWarnings("unchecked")
            Map<String, Object> hair = (Map<String, Object>) user.get("hair");

            @SuppressWarnings("unchecked")
            Map<String, Object> address = (Map<String, Object>) user.get("address");
            @SuppressWarnings("unchecked")
            Map<String, Object> addressCoordinates = (Map<String, Object>) address.get("coordinates");
            
            @SuppressWarnings("unchecked")
            Map<String, Object> bank = (Map<String, Object>) user.get("bank");

            @SuppressWarnings("unchecked")
            Map<String, Object> company = (Map<String, Object>) user.get("company");
            @SuppressWarnings("unchecked")
            Map<String, Object> companyAddress = (Map<String, Object>) company.get("address");
            @SuppressWarnings("unchecked")
            Map<String, Object> companyCoordinates = (Map<String, Object>) companyAddress.get("coordinates");

            User newUser = new User(user, crypto, hair, address, addressCoordinates, bank, company, companyAddress, companyCoordinates);
            users.add(newUser);
        }

        StringBuilder sb = new StringBuilder();
        
        String registred = "registred, " + users.size() + "\n\n";
        sb.append(registred);

        sb.append("gender, total\n");
        String maleTotal = String.valueOf(users.stream().filter(user -> user.getGender().equals("male")).count());
        String femaleTotal = String.valueOf(users.stream().filter(user -> user.getGender().equals("female")).count());
        String otherTotal = String.valueOf(users.stream().filter(user -> user.getGender().equals("other")).count());

        GenderSummary maleSummary = new GenderSummary("male", Integer.parseInt(maleTotal));
        GenderSummary femaleSummary = new GenderSummary("female", Integer.parseInt(femaleTotal));
        GenderSummary otherSummary = new GenderSummary("other", Integer.parseInt(otherTotal));
        genderSummaryService.save(maleSummary);
        genderSummaryService.save(femaleSummary);
        genderSummaryService.save(otherSummary);

        sb.append("male, "+ maleTotal + "\n");
        sb.append("female, "+ femaleTotal + "\n");
        sb.append("other, "+ otherTotal + "\n\n");

        sb.append("age, male, female, other\n");
        String zeroToTenMale = String.valueOf(users.stream().filter(user -> user.getGender().equals("male") && user.getAge() >= 0 && user.getAge() <= 10).count());
        String zeroToTenFemale = String.valueOf(users.stream().filter(user -> user.getGender().equals("female") && user.getAge() >= 0 && user.getAge() <= 10).count());
        String zeroToTenOther = String.valueOf(users.stream().filter(user -> user.getGender().equals("other") && user.getAge() >= 0 && user.getAge() <= 10).count());
        sb.append("00-10,"+zeroToTenMale+","+zeroToTenFemale+","+zeroToTenOther+"\n");
        
        GenderAgeSummary gas010Male = new GenderAgeSummary("00-10", Integer.parseInt(zeroToTenMale), Integer.parseInt(zeroToTenFemale), Integer.parseInt(zeroToTenOther));
        genderAgeSummaryService.save(gas010Male);

        String elevenToTwentyMale = String.valueOf(users.stream().filter(user -> user.getGender().equals("male") && user.getAge() >= 11 && user.getAge() <= 20).count());
        String elevenToTwentyFemale = String.valueOf(users.stream().filter(user -> user.getGender().equals("female") && user.getAge() >= 11 && user.getAge() <= 20).count());
        String elevenToTwentyOther = String.valueOf(users.stream().filter(user -> user.getGender().equals("other") && user.getAge() >= 11 && user.getAge() <= 20).count());
        sb.append("11-20,"+elevenToTwentyMale+","+elevenToTwentyFemale+","+elevenToTwentyOther+"\n");

        GenderAgeSummary gas1011Male = new GenderAgeSummary("11-20", Integer.parseInt(elevenToTwentyMale), Integer.parseInt(elevenToTwentyFemale), Integer.parseInt(elevenToTwentyOther));
        genderAgeSummaryService.save(gas1011Male);

        String twentyOneToThirtyMale = String.valueOf(users.stream().filter(user -> user.getGender().equals("male") && user.getAge() >= 21 && user.getAge() <= 30).count());
        String twentyOneToThirtyFemale = String.valueOf(users.stream().filter(user -> user.getGender().equals("female") && user.getAge() >= 21 && user.getAge() <= 30).count());
        String twentyOneToThirtyOther = String.valueOf(users.stream().filter(user -> user.getGender().equals("other") && user.getAge() >= 21 && user.getAge() <= 30).count());
        sb.append("21-30,"+twentyOneToThirtyMale+","+twentyOneToThirtyFemale+","+twentyOneToThirtyOther+"\n");

        GenderAgeSummary gas2130Male = new GenderAgeSummary("21-30", Integer.parseInt(twentyOneToThirtyMale), Integer.parseInt(twentyOneToThirtyFemale), Integer.parseInt(twentyOneToThirtyOther));
        genderAgeSummaryService.save(gas2130Male);

        String thirtyOneToFortyMale = String.valueOf(users.stream().filter(user -> user.getGender().equals("male") && user.getAge() >= 31 && user.getAge() <= 40).count());
        String thirtyOneToFortyFemale = String.valueOf(users.stream().filter(user -> user.getGender().equals("female") && user.getAge() >= 31 && user.getAge() <= 40).count());
        String thirtyOneToFortyOther = String.valueOf(users.stream().filter(user -> user.getGender().equals("other") && user.getAge() >= 31 && user.getAge() <= 40).count());
        sb.append("31-40,"+thirtyOneToFortyMale+","+thirtyOneToFortyFemale+","+thirtyOneToFortyOther+"\n");

        GenderAgeSummary gas3140Male = new GenderAgeSummary("31-40", Integer.parseInt(thirtyOneToFortyMale), Integer.parseInt(thirtyOneToFortyFemale), Integer.parseInt(thirtyOneToFortyOther));
        genderAgeSummaryService.save(gas3140Male);

        String fortyOneToFiftyMale = String.valueOf(users.stream().filter(user -> user.getGender().equals("male") && user.getAge() >= 41 && user.getAge() <= 50).count());
        String fortyOneToFiftyFemale = String.valueOf(users.stream().filter(user -> user.getGender().equals("female") && user.getAge() >= 41 && user.getAge() <= 50).count());
        String fortyOneToFiftyOther = String.valueOf(users.stream().filter(user -> user.getGender().equals("other") && user.getAge() >= 41 && user.getAge() <= 50).count());
        sb.append("41-50,"+fortyOneToFiftyMale+","+fortyOneToFiftyFemale+","+fortyOneToFiftyOther+"\n");

        GenderAgeSummary gas4150Male = new GenderAgeSummary("41-50", Integer.parseInt(fortyOneToFiftyMale), Integer.parseInt(fortyOneToFiftyFemale), Integer.parseInt(fortyOneToFiftyOther));
        genderAgeSummaryService.save(gas4150Male);

        String fiftyOneToSixtyMale = String.valueOf(users.stream().filter(user -> user.getGender().equals("male") && user.getAge() >= 51 && user.getAge() <= 60).count());
        String fiftyOneToSixtyFemale = String.valueOf(users.stream().filter(user -> user.getGender().equals("female") && user.getAge() >= 51 && user.getAge() <= 60).count());
        String fiftyOneToSixtyOther = String.valueOf(users.stream().filter(user -> user.getGender().equals("other") && user.getAge() >= 51 && user.getAge() <= 60).count());
        sb.append("51-60,"+fiftyOneToSixtyMale+","+fiftyOneToSixtyFemale+","+fiftyOneToSixtyOther+"\n");

        GenderAgeSummary gas5160Male = new GenderAgeSummary("51-60", Integer.parseInt(fiftyOneToSixtyMale), Integer.parseInt(fiftyOneToSixtyFemale), Integer.parseInt(fiftyOneToSixtyOther));
        genderAgeSummaryService.save(gas5160Male);

        String sixtyOneToSeventyMale = String.valueOf(users.stream().filter(user -> user.getGender().equals("male") && user.getAge() >= 61 && user.getAge() <= 70).count());
        String sixtyOneToSeventyFemale = String.valueOf(users.stream().filter(user -> user.getGender().equals("female") && user.getAge() >= 61 && user.getAge() <= 70).count());
        String sixtyOneToSeventyOther = String.valueOf(users.stream().filter(user -> user.getGender().equals("other") && user.getAge() >= 61 && user.getAge() <= 70).count());
        sb.append("61-70,"+sixtyOneToSeventyMale+","+sixtyOneToSeventyFemale+","+sixtyOneToSeventyOther+"\n");

        GenderAgeSummary gas6170Male = new GenderAgeSummary("61-70", Integer.parseInt(sixtyOneToSeventyMale), Integer.parseInt(sixtyOneToSeventyFemale), Integer.parseInt(sixtyOneToSeventyOther));
        genderAgeSummaryService.save(gas6170Male);

        String seventyOneToEightyMale = String.valueOf(users.stream().filter(user -> user.getGender().equals("male") && user.getAge() >= 71 && user.getAge() <= 80).count());
        String seventyOneToEightyFemale = String.valueOf(users.stream().filter(user -> user.getGender().equals("female") && user.getAge() >= 71 && user.getAge() <= 80).count());
        String seventyOneToEightyOther = String.valueOf(users.stream().filter(user -> user.getGender().equals("other") && user.getAge() >= 71 && user.getAge() <= 80).count());
        sb.append("71-80,"+seventyOneToEightyMale+","+seventyOneToEightyFemale+","+seventyOneToEightyOther+"\n");

        GenderAgeSummary gas7180Male = new GenderAgeSummary("71-80", Integer.parseInt(seventyOneToEightyMale), Integer.parseInt(seventyOneToEightyFemale), Integer.parseInt(seventyOneToEightyOther));
        genderAgeSummaryService.save(gas7180Male);

        String eightyOneToNinetyMale = String.valueOf(users.stream().filter(user -> user.getGender().equals("male") && user.getAge() >= 81 && user.getAge() <= 90).count());
        String eightyOneToNinetyFemale = String.valueOf(users.stream().filter(user -> user.getGender().equals("female") && user.getAge() >= 81 && user.getAge() <= 90).count());
        String eightyOneToNinetyOther = String.valueOf(users.stream().filter(user -> user.getGender().equals("other") && user.getAge() >= 81 && user.getAge() <= 90).count());
        sb.append("81-90,"+eightyOneToNinetyMale+","+eightyOneToNinetyFemale+","+eightyOneToNinetyOther+"\n");

        GenderAgeSummary gas8190Male = new GenderAgeSummary("81-90", Integer.parseInt(eightyOneToNinetyMale), Integer.parseInt(eightyOneToNinetyFemale), Integer.parseInt(eightyOneToNinetyOther));
        genderAgeSummaryService.save(gas8190Male);

        String ninetyOnePlusMale = String.valueOf(users.stream().filter(user -> user.getGender().equals("male") && user.getAge() >= 91).count());
        String ninetyOnePlusFemale = String.valueOf(users.stream().filter(user -> user.getGender().equals("female") && user.getAge() >= 91).count());
        String ninetyOnePlusOther = String.valueOf(users.stream().filter(user -> user.getGender().equals("other") && user.getAge() >= 91).count());
        sb.append("91+,"+ninetyOnePlusMale+","+ninetyOnePlusFemale+","+ninetyOnePlusOther+"\n\n");

        GenderAgeSummary gas90Male = new GenderAgeSummary("91+", Integer.parseInt(ninetyOnePlusMale), Integer.parseInt(ninetyOnePlusFemale), Integer.parseInt(ninetyOnePlusOther));
        genderAgeSummaryService.save(gas90Male);

        sb.append("City, male, female, other\n");
        String washingtonMaleCount = String.valueOf(users.stream().filter(user -> user.getGender().equals("male") && user.getAddress().getCity().equals("Washington")).count());
        String washingtonFemaleCount = String.valueOf(users.stream().filter(user -> user.getGender().equals("female") && user.getAddress().getCity().equals("Washington")).count());
        String washingtonOtherCount = String.valueOf(users.stream().filter(user -> user.getGender().equals("other") && user.getAddress().getCity().equals("Washington")).count());

        String anchorageMaleCount = String.valueOf(users.stream().filter(user -> user.getGender().equals("male") && user.getAddress().getCity().equals("Anchorage")).count());
        String anchorageFemaleCount = String.valueOf(users.stream().filter(user -> user.getGender().equals("female") && user.getAddress().getCity().equals("Anchorage")).count());
        String anchorageOtherCount = String.valueOf(users.stream().filter(user -> user.getGender().equals("other") && user.getAddress().getCity().equals("Anchorage")).count());

        sb.append("Washington, "+washingtonMaleCount+","+washingtonFemaleCount+","+washingtonOtherCount+"\n");
        sb.append("Anchorage, "+anchorageMaleCount+","+anchorageFemaleCount+","+anchorageOtherCount+"\n\n");

        sb.append("SO, total \n");
        String windowsUser = String.valueOf(users.stream().filter(user -> user.getUserAgent().contains("Windows")).count());
        String linuxUser = String.valueOf(users.stream().filter(user -> user.getUserAgent().contains("Linux")).count());
        String appleUser = String.valueOf(users.stream().filter(user -> user.getUserAgent().contains("Apple")).count());

        sb.append("Windows, "+windowsUser+"\n");
        sb.append("Linux, "+linuxUser+"\n");
        sb.append("Apple, "+appleUser+"\n\n");

        exchange.setProperty("SummaryCsv", sb.toString());
        exchange.setProperty("ResponseUsers", users);
    }
    
}