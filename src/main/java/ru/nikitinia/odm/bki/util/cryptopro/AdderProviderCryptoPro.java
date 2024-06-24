package ru.nikitinia.odm.bki.util.cryptopro;

import lombok.experimental.UtilityClass;
import ru.nikitinia.odm.bki.util.Constant;

import java.lang.reflect.InvocationTargetException;
import java.security.Provider;
import java.security.Security;

@UtilityClass
public class AdderProviderCryptoPro {

    public static void addProvideDataCryptoProForMoreOrEquals10JVM() {

        System.setProperty(Constant.Security.CHECK_RECALL_CERTIFICATE_LIST_IBM, String.valueOf(true));
        System.setProperty(Constant.Security.CHECK_RECALL_CERTIFICATE_LIST_SUN, String.valueOf(true));
        System.setProperty(Constant.Security.GET_ALL_CHAIN_CERTIFICATE_LIST_SUN, String.valueOf(true));
        System.setProperty(Constant.Security.GET_ALL_CHAIN_CERTIFICATE_LIST_CRYPTO_PRO, String.valueOf(true));
        System.setProperty(Constant.Security.INTERVAL_GET_VALID_INFORMATION_ABOUT_CERTIFICATE, Constant.Security.INTERVAL_SYNCHRONIZE);

        //For Java more 10
        boolean isJCSPExists = addProvider(Constant.Security.JCSP_CRYPTO_PRO_PACKAGE);
        if (isJCSPExists) {
            if (!addProvider(Constant.Security.SSPISSL_PACKAGE))
                addProvider(Constant.Security.SSL_PACKAGE);
        } else {
            addProvider(Constant.Security.JCP_CRYPTO_PRO_PACKAGE);
            addProvider(Constant.Security.CRYPTO_PROVIDER_PACKAGE);
            addProvider(Constant.Security.SSL_PACKAGE);
        }
        addProvider(Constant.Security.REPROV_CRYPTO_PRO_PACKAGE);
        //For Java more 10
    }

    public static boolean addProvider(String fullName) {
        try {
            Security.addProvider((Provider) Class.forName(fullName).getConstructor().newInstance());
            return true;
        } catch (InstantiationException |
                 ClassNotFoundException |
                 IllegalAccessException |
                 NoSuchMethodException |
                 InvocationTargetException exception) {
            return false;
        }
    }

}