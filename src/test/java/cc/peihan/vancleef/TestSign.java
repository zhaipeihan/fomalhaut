package cc.peihan.vancleef;

import cc.peihan.vancleef.model.Wallet;
import cc.peihan.vancleef.util.SignatureUtil;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;
import org.junit.Test;

import javax.swing.text.StyledEditorKit;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.util.Arrays;

public class TestSign {


    @Test
    public void testSignAndVerify() throws Exception {
/*
        byte[] source = "Hello,World".getBytes();
        byte[] source2 = "Hello World".getBytes();
        Wallet wallet = new Wallet();
        Wallet otherWallet = new Wallet();

        byte[] sig = SignatureUtil.sign(source,wallet.getPrivateKey());

        boolean result = SignatureUtil.verify(source,sig,otherWallet.getPublicKey());
        System.out.println(result);

        assert result;*/
    }
}
