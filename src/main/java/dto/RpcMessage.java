package dto;

import lombok.*;

/**
  RPC 传输的消息类型
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcMessage {
    private byte msgType;  // RPC message type
    private byte codec;  // serialization type
    private byte compress;  // compress type
    private int requestId;  // request ID
    private Object data;  // request data
}
