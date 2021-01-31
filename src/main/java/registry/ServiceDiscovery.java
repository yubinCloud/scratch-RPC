package registry;

import java.net.InetSocketAddress;

public interface ServiceDiscovery {
    /**
     * 根据给定的 service name 寻找响应的 rpc service
     * @param rpcServiceName rpc service name
     * @return service address
     */
    InetSocketAddress lookupService(String rpcServiceName);
}
