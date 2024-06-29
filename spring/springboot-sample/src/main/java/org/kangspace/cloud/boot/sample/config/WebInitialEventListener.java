package org.kangspace.cloud.boot.sample.config;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author kango2gler@gmail.com
 * @date 2024/6/29
 * @since
 */
@Component
public class WebInitialEventListener implements ApplicationListener<WebServerInitializedEvent> {
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        System.out.println("WebInitialEventListener: "+ event);
    }
}
