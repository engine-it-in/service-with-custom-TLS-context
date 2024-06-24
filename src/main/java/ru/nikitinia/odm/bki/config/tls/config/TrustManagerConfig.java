package ru.nikitinia.odm.bki.config.tls.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import ru.nikitinia.odm.bki.config.tls.store.JcpStore;
import ru.nikitinia.odm.bki.exception.tls.TrustManagerException;
import ru.nikitinia.odm.bki.util.Constant;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TrustManagerConfig {

    private final JcpStore jcpKeyStore;

    /**
     * Назначение ->
     * Массив доверительных сертификатов гост;
     */
    public TrustManager[] getGostTrustManager() {

        KeyStore keyStore =
                jcpKeyStore.prepareKeyStoreWithJcpCert();

        TrustManagerFactory factory;
        try {
            factory = TrustManagerFactory.getInstance(Constant.Security.GOST_CERTIFICATE_VALUE);
            factory.init(keyStore);
        } catch (NoSuchAlgorithmException | KeyStoreException exception) {
            log.error(
                    "TrustManager. Ошибка при инициализации trustManager с гост сертификатами : {}",
                    exception.getMessage()
            );
            throw new TrustManagerException(exception.getMessage());
        }
        return factory.getTrustManagers();
    }
}