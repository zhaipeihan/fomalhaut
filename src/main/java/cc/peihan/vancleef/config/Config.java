package cc.peihan.vancleef.config;

import lombok.Data;

import java.util.List;

@Data
public class Config {

    private int port;

    private List<String> ipPorts;

}
