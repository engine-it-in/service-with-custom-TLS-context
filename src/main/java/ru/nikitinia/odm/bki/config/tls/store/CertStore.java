package ru.nikitinia.odm.bki.config.tls.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import ru.nikitinia.odm.bki.exception.tls.CertException;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@Configuration
public class CertStore {

    /**
     * Назначение ->
     * Получение списка доверительных сертификатов из хранилища сертификатов caserts (Java);
     */
    public List<X509Certificate> getCertsFromCacerts() {
        TrustManagerFactory trustManagerFactory;
        try {
            trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
        } catch (NoSuchAlgorithmException | KeyStoreException exception) {
            log.error(
                    "CertConfig. Ошибка при инициализации trustManager для хранилища ключей: {}",
                    exception.getMessage()
            );
            throw new CertException(exception.getMessage());
        }

        List<TrustManager> trustManagers = Arrays.asList(trustManagerFactory.getTrustManagers());
        return trustManagers.stream()
                .filter(X509TrustManager.class::isInstance)
                .map(X509TrustManager.class::cast)
                .map(trustManager -> Arrays.asList(trustManager.getAcceptedIssuers()))
                .flatMap(Collection::stream)
                .toList();
    }

}