package cc.peihan.vancleef.demo;

import cc.peihan.vancleef.cli.Cli;
import org.junit.Test;

public class TestDemo {

    public static final String A = "12EVqgu9X4f3DQzS8e3d5y81V16UYyzpN2";
    public static final String B = "14FAiBSmSrtAYa9z8x1JfZGC4a1rLYXPss";


    @Test
    public void testInitCli() throws Exception {
        String[] args = {"init", "-address", A};
        Cli cli = new Cli();
        cli.parse(args);
    }

    @Test
    public void testListCli() throws Exception {
        String[] args = {"list"};
        Cli cli = new Cli();
        cli.parse(args);
    }

    @Test
    public void testBalanceCli() throws Exception {
        String[] args = {"balance", "-address", B};
        Cli cli = new Cli();
        cli.parse(args);
    }

    @Test
    public void testSendCli() throws Exception {
        String[] args = {"send", "-from", A, "-to", B, "-amount", "5"};
        Cli cli = new Cli();
        cli.parse(args);
    }

    @Test
    public void testWalletCli() throws Exception {
        String[] args = {"wallet"};
        Cli cli = new Cli();
        cli.parse(args);
    }

    @Test
    public void testPullCli() throws Exception {
        String[] args = {"pull"};
        Cli cli = new Cli();
        cli.parse(args);
    }


    @Test
    public void testPushCli() throws Exception {
        String[] args = {"push"};
        Cli cli = new Cli();
        cli.parse(args);
    }
}
