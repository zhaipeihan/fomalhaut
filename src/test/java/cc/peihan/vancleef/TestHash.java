package cc.peihan.vancleef;

import cc.peihan.vancleef.model.Transaction;
import cc.peihan.vancleef.model.TxInput;
import cc.peihan.vancleef.model.Wallet;
import cc.peihan.vancleef.util.HashUtil;
import cc.peihan.vancleef.util.WalletUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;

public class TestHash {


    @Test
    public void name() throws Exception {
/*
        Wallet wallet = new Wallet();
        String address = WalletUtil.getAddress(wallet);

        Transaction transaction = Transaction.makeCoinbaseTx(address,"hello");


        Transaction dup1 = transaction.duplicate();
        Transaction dup2 = transaction.duplicate();

        System.out.println(dup1);
        System.out.println(dup2);
        assert dup1 == dup2;
        assert Objects.equals(dup1,dup2);

        byte[] hash1 = HashUtil.hash(dup1);
        byte[] hash2 = HashUtil.hash(dup2);

        System.out.println(Arrays.toString(hash1));
        System.out.println(Arrays.toString(hash2));

        assert Arrays.equals(hash1,hash2);*/


    }


    @Test
    public void name1() throws Exception {
        System.out.println(DigestUtils.sha256Hex("Hello World"));
        System.out.println(DigestUtils.sha256Hex("hello world"));

    }
}
