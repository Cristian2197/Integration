package sftp.integration.models;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

public class User {
    private Double id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private Double age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String birthDate;
    private String image;
    private String bloodGroup;
    private Double height;
    private Double weight;
    private String eyeColor;
    private Hair hair;
    private String domain;
    private String ip;
    private Address address;
    private String macAddress;
    private String university;
    private Bank bank;
    private Company company;
    private String ein;
    private String ssn;
    private String userAgent;
    private Crypto crypto;

    public User(Double id, String firstName, String lastName, String maidenName, Double age, String gender, String email,
            String phone, String username, String password, String birthDate, String image, String bloodGroup,
            Double height, Double weight, String eyeColor, Hair hair, String domain, String ip, Address address,
            String macAddress, String university, Bank bank, Company company, String ein, String ssn, String userAgent,
            Crypto crypto) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maidenName = maidenName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.image = image;
        this.bloodGroup = bloodGroup;
        this.height = height;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.hair = hair;
        this.domain = domain;
        this.ip = ip;
        this.address = address;
        this.macAddress = macAddress;
        this.university = university;
        this.bank = bank;
        this.company = company;
        this.ein = ein;
        this.ssn = ssn;
        this.userAgent = userAgent;
        this.crypto = crypto;
    }

    public User() { }

    public User(Map<String, Object> userMapped, Map<String, Object> cryptoMapped,
    Map<String, Object> hairMappped, Map<String, Object> addressMapped, 
    Map<String, Object> coordinateUserAddressMapped, Map<String, Object> bankMapped,
    Map<String, Object> companyMapped, Map<String, Object> companyAddressMapped,
    Map<String, Object> companyAddressCoordinatesMapped){

        this.id = (Double) userMapped.get("id");
        this.firstName = (String) userMapped.get("firstName");
        this.lastName = (String) userMapped.get("lastName");
        this.maidenName = (String) userMapped.get("maidenName");
        this.age = (Double) userMapped.get("age");
        this.gender = (String) userMapped.get("gender");
        this.email = (String) userMapped.get("email");
        this.phone = (String) userMapped.get("phone");
        this.username = (String) userMapped.get("username");
        this.password = (String) userMapped.get("password");
        this.birthDate = (String) userMapped.get("birthDate");
        this.image = (String) userMapped.get("image");
        this.bloodGroup = (String) userMapped.get("bloodGroup");
        this.height = (Double) userMapped.get("height");
        this.weight = (Double) userMapped.get("weight");
        this.eyeColor = (String) userMapped.get("eyeColor");
        this.domain = (String) userMapped.get("domain");
        this.ip = (String) userMapped.get("ip");
        this.macAddress = (String) userMapped.get("macAddress");
        this.university = (String) userMapped.get("university");
        this.ein = (String) userMapped.get("ein");
        this.ssn = (String) userMapped.get("ssn");
        this.userAgent = (String) userMapped.get("userAgent");
        
        this.bank = new Bank(bankMapped);
        this.address = new Address(addressMapped, coordinateUserAddressMapped);
        this.hair = new Hair(hairMappped);
        this.crypto = new Crypto(cryptoMapped);
        this.company = new Company(companyMapped, companyAddressMapped, companyAddressCoordinatesMapped);
    }

    public Double getId() {
        return id;
    }
    public void setId(Double id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getMaidenName() {
        return maidenName;
    }
    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }
    public Double getAge() {
        return age;
    }
    public void setAge(Double age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public Double getHeight() {
        return height;
    }
    public void setHeight(Double height) {
        this.height = height;
    }
    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    public String getEyeColor() {
        return eyeColor;
    }
    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }
    public Hair getHair() {
        return hair;
    }
    public void setHair(Hair hair) {
        this.hair = hair;
    }
    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getMacAddress() {
        return macAddress;
    }
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
    public String getUniversity() {
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }
    public Bank getBank() {
        return bank;
    }
    public void setBank(Bank bank) {
        this.bank = bank;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public String getEin() {
        return ein;
    }
    public void setEin(String ein) {
        this.ein = ein;
    }
    public String getSsn() {
        return ssn;
    }
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    public String getUserAgent() {
        return userAgent;
    }
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    public Crypto getCrypto() {
        return crypto;
    }
    public void setCrypto(Crypto crypto) {
        this.crypto = crypto;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Iterate through declared fields (excluding static fields)
        for (Field field : getClass().getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(this);

                    // Handle nested objects recursively (without names or brackets)
                    if (value instanceof Map) {
                        sb.append(mapToStringCSV((Map) value) + ", ");
                    } else {
                        sb.append(value + ", ");
                    }
                } catch (IllegalAccessException e) {
                    // Handle potential access exceptions gracefully
                    sb.append("(access error), ");
                }
            }
        }

        // Remove trailing comma and space if present
        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    // Helper method for recursive handling of nested Maps (without names or brackets)
    private String mapToStringCSV(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map) {
                sb.append(mapToStringCSV((Map) value) + ", ");
            } else {
                sb.append(value + ", ");
            }
        }

        // Remove trailing comma and space if present
        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2);
        }
        String res = sb.toString().replace("[", "");
        res = res.replace("]", "");
        return res;
    }

}
