package dto;

import entity.RpcServiceProperties;
import lombok.*;

import java.io.Serializable;

/**
 * RPC 请求实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;
    private String requestId;
    private String interfaceName;
    private String methodName;
    private Object[] params;
    private Class<?>[] paramTypes;
    private RpcMessage msg;
    private String version;  // 服务版本，为后续版本不兼容升级提供可能
    private String group;  // 用于处理一个接口有多个类实现的情况

    public RpcServiceProperties toRpcProperties() {
        return RpcServiceProperties.builder().serviceName(this.getInterfaceName())
                .version(this.getVersion())
                .group(this.getGroup()).build();
    }
}
