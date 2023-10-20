package com.reasunta.websocketstest.websockets;

import com.reasunta.websocketstest.model.Message;
import com.reasunta.websocketstest.persistent.RefSessionHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebSocketController {
    private final RefSessionHolder refSessionHolder;

    @MessageMapping("/payment/{reference}")
    public void initPaymentStatusAwait(@DestinationVariable String reference, SimpMessageHeaderAccessor accessor) {
        log.info("reference: " + reference);
        log.info("session id:" + accessor.getSessionId());
        refSessionHolder.addRefSessionLink(reference, accessor.getSessionId());
    }
}
