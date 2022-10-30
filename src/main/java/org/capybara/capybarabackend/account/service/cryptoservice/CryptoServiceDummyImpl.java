package org.capybara.capybarabackend.account.service.cryptoservice;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("default")
class CryptoServiceDummyImpl implements CryptoService {

    @Override
    public String encryptSymmetric(String plaintext) {
        return "ENCRYPTED_DUMMY_TOKEN";
    }

    @Override
    public String decryptSymmetric(byte[] ciphertext) {
        return "DECRYPTED_DUMMY_TOKEN";
    }

}
