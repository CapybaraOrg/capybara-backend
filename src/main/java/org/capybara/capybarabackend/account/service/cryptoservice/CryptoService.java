package org.capybara.capybarabackend.account.service.cryptoservice;

public interface CryptoService {

    String encryptSymmetric(String plaintext);

    String decryptSymmetric(byte[] ciphertext);

}
