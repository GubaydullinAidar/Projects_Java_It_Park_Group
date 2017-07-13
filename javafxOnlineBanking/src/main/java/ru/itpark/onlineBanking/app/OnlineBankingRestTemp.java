package ru.itpark.onlineBanking.app;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.itpark.onlineBanking.models.AccountTransaction;
import ru.itpark.onlineBanking.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnlineBankingRestTemp {

    private RestTemplate restTemplate;

    public boolean tokenValid(String token) {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8090/tokenValid";

        HttpHeaders headers = new HttpHeaders();
        headers.add("token", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Boolean> tokenValid = restTemplate.postForEntity(url, entity, Boolean.class);

        return tokenValid.getBody();
    }

    public String login(String username, String password) {
        List<HttpMessageConverter<?>> converters = Arrays.asList(new MappingJackson2HttpMessageConverter());
        restTemplate = new RestTemplate(converters);

        String url = "http://localhost:8090/signin";

        HttpHeaders headers = new HttpHeaders();
        headers.add("username", username);
        headers.add("password", password);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<User> user = restTemplate.postForEntity(url, entity, User.class);
            headers = user.getHeaders();
            return headers.getFirst("Auth-token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(String token) {
        String url = "http://localhost:8090/user";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-token", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<User> primaryTransList = restTemplate.postForEntity(url, entity, User.class);

        return primaryTransList.getBody();
    }

    public String signup(User user) {
        restTemplate = new RestTemplate();

        String url = "http://localhost:8090/signup";

        HttpEntity<User> entity = new HttpEntity<>(user);
        ResponseEntity<String> responseSignUp = restTemplate.postForEntity(url, entity, String.class);

        return responseSignUp.getBody();
    }

    public List<AccountTransaction> primaryTransactionList(User user) {
        restTemplate = new RestTemplate();

        String url = "http://localhost:8090/user/" + user.getUserId() + "/primaryAccountTransaction";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-token", user.getToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<AccountTransaction[]> responseEntity = restTemplate.postForEntity(url, entity, AccountTransaction[].class);
        AccountTransaction[] objects = responseEntity.getBody();
        return new ArrayList<>(Arrays.asList(objects));
    }

    public List<AccountTransaction> savingsTransactionList(User user) {
        restTemplate = new RestTemplate();

        String url = "http://localhost:8090/user/" + user.getUserId() + "/savingsAccountTransaction";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-token", user.getToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<AccountTransaction[]> responseEntity = restTemplate.postForEntity(url, entity, AccountTransaction[].class);
        AccountTransaction[] object = responseEntity.getBody();
        return new ArrayList<>(Arrays.asList(object));
    }

    public String refillPrimaryAccount(String token, String amount) {
        restTemplate = new RestTemplate();

        String url = "http://localhost:8090/refillPrimaryAccount";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-token", token);
        headers.add("amount", amount);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseRefillPrimaryAccount = restTemplate.postForEntity(url, entity, String.class);
        return responseRefillPrimaryAccount.getBody();

    }

    public String refillSavingsAccount(String token, String amount) {
        restTemplate = new RestTemplate();

        String url = "http://localhost:8090/refillSavingsAccount";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-token", token);
        headers.add("amount", amount);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseRefillSavingsAccount = restTemplate.postForEntity(url, entity, String.class);
        return responseRefillSavingsAccount.getBody();
    }

    public String debitPrimaryAccount(String token, String amount) {
        restTemplate = new RestTemplate();

        String url = "http://localhost:8090/debitPrimaryAccount";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-token", token);
        headers.add("amount", amount);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseDebitPrimaryAccount = restTemplate.postForEntity(url, entity, String.class);
        return responseDebitPrimaryAccount.getBody();

    }

    public String debitSavingsAccount(String token, String amount) {
        restTemplate = new RestTemplate();

        String url = "http://localhost:8090/debitSavingsAccount";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-token", token);
        headers.add("amount", amount);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseDebitSavingsAccount = restTemplate.postForEntity(url, entity, String.class);
        return responseDebitSavingsAccount.getBody();
    }

    public String getPrimaryBalance(String token) {
        restTemplate = new RestTemplate();

        String url = "http://localhost:8090/getBalance";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-token", token);
        headers.add("TypeAccount", "primary");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseGetPrimaryBalance = restTemplate.postForEntity(url, entity, String.class);
        return responseGetPrimaryBalance.getBody();
    }

    public String getSavingsBalance(String token) {
        restTemplate = new RestTemplate();

        String url = "http://localhost:8090/getBalance";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-token", token);
        headers.add("TypeAccount", "savings");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseGetSavingsBalance = restTemplate.postForEntity(url, entity, String.class);
        return responseGetSavingsBalance.getBody();
    }

    public User saveShange(User user) {
        restTemplate = new RestTemplate();

        String url = "http://localhost:8090/changeUser";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-token", user.getToken());

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<User> savedChange = restTemplate.postForEntity(url, entity, User.class);
        return savedChange.getBody();
    }
}