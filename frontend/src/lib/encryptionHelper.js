import CryptoES from "crypto-es";

export function getEncryptionKey(variable) {
    return CryptoES.SHA256(variable + "---" + localStorage.getItem("auth")).toString()
}

export function decryptToText(variable, text) {
    return CryptoES.AES.decrypt(text, getEncryptionKey(variable)).toString(CryptoES.enc.Utf8);
}

export function decryptToTextWithKey(key, text) {
    return CryptoES.AES.decrypt(text, key).toString(CryptoES.enc.Utf8);
}