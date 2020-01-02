package kr.co.lunasoft.config;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ZuulFilterConfig extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		log.info("shouldFilter");
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		log.info("run");
		
		RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.addZuulRequestHeader("starting", "EurekaServer");
		
		return null;
	}

	@Override
	public String filterType() {
		log.info("filterType");
		return "pre";
	}

	@Override
	public int filterOrder() {
		log.info("filterOrder");
		return 0;
	}

}
