package org.capybara.capybarabackend.account.service.cryptoservice;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Profile("default")
class CryptoServiceDummyImpl implements CryptoService {

    @Override
    public byte[] encryptSymmetric(String plaintext) {
        return "ENCRYPTED_DUMMY_TOKEN".getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String decryptSymmetric(byte[] ciphertext) {
        return "DECRYPTED_DUMMY_TOKEN";
    }

}
