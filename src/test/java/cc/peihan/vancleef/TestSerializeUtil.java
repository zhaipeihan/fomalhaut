package cc.peihan.vancleef;

import cc.peihan.vancleef.model.Block;
import cc.peihan.vancleef.util.HashUtil;
import cc.peihan.vancleef.util.MagicUtil;
import cc.peihan.vancleef.util.SerializeUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestSerializeUtil {

    @Test
    public void testSerializeAndDeSerialize2() throws Exception {
        Block block = new Block();
        block.setPreviousHash(MagicUtil.makeEmptyHashStr());
        block.setTimeStamp(MagicUtil.getNowTimeStamp());
        block.setTransactions(new ArrayList<>());
        block.setNonce(0);
        block.setHash(HashUtil.hash(block));

        byte[] bytes = SerializeUtil.serialize(block);
        System.out.println(Arrays.toString(bytes));
        System.out.println(SerializeUtil.deSerialize(bytes));
    }


    @Test
    public void testSerializeAndDeSerialize() throws Exception {
        String test = "BlockChain";
        byte[] bytes = SerializeUtil.serialize(test);
        String result = (String) SerializeUtil.deSerialize(bytes);
        assert result.equals(test);
    }
}
