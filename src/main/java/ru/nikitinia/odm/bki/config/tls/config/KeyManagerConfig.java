package ru.nikitinia.odm.bki.config.tls.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import ru.CryptoPro.ssl.JavaTLSCertPathManagerParameters;
import ru.nikitinia.odm.bki.config.tls.store.CertStore;
import ru.nikitinia.odm.bki.config.tls.store.JcpStore;
import ru.nikitinia.odm.bki.config.tls.store.PFXStore;
import ru.nikitinia.odm.bki.exception.tls.KeyManagerException;
import ru.nikitinia.odm.bki.util.Constant;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.X509CertSelector;
import java.util.Collections;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KeyManagerConfig {

    private final CertStore certStore;
    private final PFXStore pfxStore;
    private final JcpStore jcpKeyStore;

    /**
     * Назначение ->
     * Формирование списка доступных ключей по гост сертификатам;
     */
    public KeyManager[] getGostKeyManagers() {

        KeyManagerFactory factory;
        try {
            factory = KeyManagerFactory.getInstance(Constant.Security.GOST_CERTIFICATE_VALUE);
        } catch (NoSuchAlgorithmException exception) {
            log.error(
                    "KeyManager. Ошибка при попытке подготовить контейнер для гост сертификатов: {}",
                    exception.getMessage()
            );
            throw new KeyManagerException(exception.getMessage());
        }

        PKIXBuilderParameters parameters;
        try {
            parameters = new PKIXBuilderParameters(
                    jcpKeyStore.prepareKeyStoreWithJcpCert(),
                    new X509CertSelector());
        } catch (KeyStoreException | InvalidAlgorithmParameterException exception) {
            log.error(
                    "KeyManager. Ошибка при построении цепочек сертификации в соответствии с протоколом X.509: {}",
                    exception.getMessage());
            throw new KeyManagerException(exception.getMessage());
        }

        parameters.setRevocationEnabled(true);
        try {
            parameters.setCertStores(
                    Collections.singletonList(
                            java.security.cert.CertStore.getInstance(
                                    Constant.MAIN.TYPE_COLLECTION,
                                    new CollectionCertStoreParameters(
                                            certStore.getCertsFromCacerts()))));
        } catch (
                InvalidAlgorithmParameterException | NoSuchAlgorithmException exception) {
            log.error(
                    "KeyManager. Ошибка при проверки всех сертификатов в цепочке X.509 на предмет отзыва: {}",
                    exception.getMessage());
            throw new KeyManagerException(exception.getMessage());
        }

        JavaTLSCertPathManagerParameters managerParameters =
                new JavaTLSCertPathManagerParameters(pfxStore.getKeyStore(), EMPTY.toCharArray());

        managerParameters.setParameters(parameters);
        try {
            factory.init(managerParameters);
        } catch (InvalidAlgorithmParameterException exception) {
            log.error(
                    "KeyManager. Ошибка при проверки доверия сертификатам TLS: {}",
                    exception.getMessage());
            throw new KeyManagerException(exception.getMessage());
        }
        return factory.getKeyManagers();
    }
}