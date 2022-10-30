package org.capybara.capybarabackend.account.service.cryptoservice;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("cloud")
class CryptoServiceGCPImpl implements CryptoService {

    @Override
    public String encryptSymmetric(String plaintext) {
        // TODO: https://cloud.google.com/kms/docs/encrypt-decrypt
        return null;
    }

    @Override
    public String decryptSymmetric(byte[] ciphertext) {
        // TODO: https://cloud.google.com/kms/docs/encrypt-decrypt
        return null;
    }

}
