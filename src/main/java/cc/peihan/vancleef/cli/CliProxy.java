package cc.peihan.vancleef.cli;


import cc.peihan.vancleef.facade.BlockChainFacade;
import cc.peihan.vancleef.facade.WalletFacadeImpl;
import cc.peihan.vancleef.model.Block;
import cc.peihan.vancleef.exception.OperateFailedException;
import cc.peihan.vancleef.exception.base.ServiceException;
import cc.peihan.vancleef.facade.BlockChainFacadeImpl;
import cc.peihan.vancleef.facade.WalletFacade;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class CliProxy {

    private BlockChainFacade blockChainFacade;

    private WalletFacade walletFacade;

    public CliProxy() {
        blockChainFacade = new BlockChainFacadeImpl();
        walletFacade = new WalletFacadeImpl();
    }


    public void showAllBlocks() throws ServiceException {
        List<Block> blocks = blockChainFacade.getAllBlocks();
        if (CollectionUtils.isEmpty(blocks)) {
            System.out.println("has no block!");
        } else {
            for (Block block : blocks) {
                System.out.println(block);
            }
        }
    }

    public void initBlockChain(String address) throws ServiceException {
        blockChainFacade.initBlockChain(address);
    }

    public void addBlock(String data) throws ServiceException {
        blockChainFacade.addBlock(data);
    }

    public void showBalance(String address) {
        System.out.println(String.format("Balance of %s : %s", address, blockChainFacade.getBalance(address)));
    }

    public void transfer(String from, String to, int amount) throws ServiceException {
        blockChainFacade.transfer(from, to, amount);
    }

    public void createWallet() {
        String address = walletFacade.createWallet();
        System.out.println(String.format("wallet address : %s",address));
    }

    public void pull() throws ServiceException {
        blockChainFacade.pull();
    }

    public void push() throws OperateFailedException {
        blockChainFacade.push();
    }
}
