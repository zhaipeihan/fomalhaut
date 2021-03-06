package cc.peihan.vancleef.facade;

import cc.peihan.vancleef.model.WalletManager;

public class WalletFacadeImpl implements WalletFacade {

    private static final WalletManager walletManager = WalletManager.getInstance();

    @Override
    public String createWallet() {
        return walletManager.createWallet();
    }
}
