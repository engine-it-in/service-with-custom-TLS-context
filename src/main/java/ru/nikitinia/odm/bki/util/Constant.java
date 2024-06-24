package ru.nikitinia.odm.bki.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {

    @UtilityClass
    public class MAIN {
        public static final String TYPE_COLLECTION = "Collection";
    }

    @UtilityClass
    public class Security {
        public static final String CHECK_RECALL_CERTIFICATE_LIST_IBM = "com.ibm.security.enableCRLDP";
        public static final String CHECK_RECALL_CERTIFICATE_LIST_SUN = "com.sun.security.enableCRLDP";
        public static final String GET_ALL_CHAIN_CERTIFICATE_LIST_SUN = "com.sun.security.enableAIAcaIssuers";
        public static final String GET_ALL_CHAIN_CERTIFICATE_LIST_CRYPTO_PRO = "ru.CryptoPro.reprov.enableAIAcaIssuers";
        public static final String INTERVAL_GET_VALID_INFORMATION_ABOUT_CERTIFICATE = "java.util.prefs.syncInterval";
        public static final String INTERVAL_SYNCHRONIZE = "99999";
        public static final String JCSP_CRYPTO_PRO_PACKAGE = "ru.CryptoPro.JCSP.JCSP";
        public static final String JCP_CRYPTO_PRO_PACKAGE = "ru.CryptoPro.JCP.JCP";
        public static final String REPROV_CRYPTO_PRO_PACKAGE = "ru.CryptoPro.reprov.RevCheck";
        public static final String SSPISSL_PACKAGE = "ru.CryptoPro.sspiSSL.SSPISSL";
        public static final String SSL_PACKAGE = "ru.CryptoPro.ssl.Provider";
        public static final String CRYPTO_PROVIDER_PACKAGE = "ru.CryptoPro.Crypto.CryptoProvider";
        public static final String GOST_CERTIFICATE_VALUE = "GostX509";
        public static final String GOST_TLS_PROTOCOL = "GostTLSv1.3";
        public static final String PFX_STORE = "PFXSTORE";

    }


    @UtilityClass
    public class SystemAndComponent {
        public static final String BKI_NAME = "BKI";
        public static final String SSL_PROCESSING = "SSLProcessing";
    }


}