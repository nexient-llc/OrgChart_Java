package com.systemsinmotion.orgchart.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response) {
		String targetUrl = request.getParameter("page");

		if (StringUtils.hasText(targetUrl)) {
			logger.debug("Redirecting to Url: " + targetUrl);
			return targetUrl;
		}
		return super.determineTargetUrl(request, response);
	}
}
