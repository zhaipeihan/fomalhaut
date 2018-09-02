package cc.peihan.vancleef;

import cc.peihan.vancleef.model.Block;
import cc.peihan.vancleef.model.BlockChain;
import cc.peihan.vancleef.model.TxOutput;
import cc.peihan.vancleef.model.Wallet;
import cc.peihan.vancleef.util.SerializeUtil;
import cc.peihan.vancleef.util.StorageUtil;
import cc.peihan.vancleef.util.WalletUtil;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestStorge {

    @Test
    public void name() throws Exception {
/*
        StorageUtil storageUtil = StorageUtil.getInstance();


        BlockChain blockChain = BlockChain.getInstance();


        List<Block> blocks = blockChain.getAllBlocks();


        Block block = blocks.get(1);


        String txId = block.getTransactions().get(0).getTxId();
        List<TxOutput> txOutputs = block.getTransactions().get(0).getTxOutputs();
        storageUtil.putUXTOs(txId,txOutputs);

        List<TxOutput> getUXTOs = storageUtil.getUXTOs(txId);

        assert CollectionUtils.isEqualCollection(txOutputs,getUXTOs);*/
    }
}
