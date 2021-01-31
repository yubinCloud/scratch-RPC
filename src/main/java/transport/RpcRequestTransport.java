package transport;

import dto.RpcRequest;

@SPI
public interface RpcRequestTransport {
    /**
     * 将 RPC 请求发送给服务器并得到结果的接口
     *
     * @param rpcRequest 消息体
     * @return 来自服务器的数据
     */
    Object sendRequest(RpcRequest rpcRequest);
}
