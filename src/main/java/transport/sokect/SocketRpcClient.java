package transport.sokect;

import dto.RpcRequest;
import entity.RpcServiceProperties;
import exception.RpcException;
import extensions.ExtensionLoader;
import registry.ServiceDiscovery;
import transport.RpcRequestTransport;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;


public class SocketRpcClient implements RpcRequestTransport {
    private final ServiceDiscovery serviceDiscovery;

    public SocketRpcClient() {
        this.serviceDiscovery = ExtensionLoader.getExtensionLoader(ServiceDiscovery.class).getExtension("zk");
    }

    @Override
    public Object sendRequest(RpcRequest rpcRequest) {
        String rpcServiceName = RpcServiceProperties.builder().serviceName(rpcRequest.getInterfaceName())
                .group(rpcRequest.getGroup()).version(rpcRequest.getVersion()).build().toRpcServiceName();
        InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(rpcServiceName);
        try (Socket socket = new Socket()) {
            socket.connect(inetSocketAddress);
            // 通过 output stream 向服务器发送数据
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            // 从 input stream 中读取 RpcResponse
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RpcException("调用服务失败", e);
        }
    }
}
