package org.sprit.config;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import de.codecentric.boot.admin.server.notify.LoggingNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Administrator
 */
@Component
public class CustomNotifier  extends AbstractStatusChangeNotifier {
    private static final Logger LOGGER = LoggerFactory.getLogger( LoggingNotifier.class);

    private JavaMailSender mailSender;
    public CustomNotifier(InstanceRepository repository,JavaMailSender mailSender) {
        super(repository);
        this.mailSender = mailSender;
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        return Mono.fromRunnable(() -> {
            if (event instanceof InstanceStatusChangedEvent) {
                LOGGER.info("Instance {} ({}) is {}", instance.getRegistration().getName(), event.getInstance(),
                        ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus());

                String status = ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus();

                switch (status) {
                    // 健康检查没通过
                    case "DOWN":
                        createMail(instance.getRegistration().getName(),event.getInstance().getValue(),"健康检查没通过");
                        break;
                    // 服务离线
                    case "OFFLINE":
                        createMail(instance.getRegistration().getName(),event.getInstance().getValue(),"服务离线");
                        break;
                    //服务上线
                    case "UP":
                        createMail(instance.getRegistration().getName(),event.getInstance().getValue(),"服务上线");
                        break;
                    // 服务未知异常
                    case "UNKNOWN":
                        createMail(instance.getRegistration().getName(),event.getInstance().getValue(),"服务未知异常");
                        break;
                    default:
                        break;
                }

            } else {
                LOGGER.info("Instance {} ({}) {}", instance.getRegistration().getName(), event.getInstance(),
                        event.getType());
            }
        });
    }

    private void createMail(String appName,String instance,String event){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("445623551@qq.com");
        msg.setTo("445623551@qq.com");
        msg.setCc("505799691@qq.com","monsterxiaohuaxin@gmail.com");
        msg.setSubject("Gallopway 应用监控通知");
        msg.setText("应用："+appName+"("+instance+")  "+event);
        mailSender.send(msg);
    }
}
