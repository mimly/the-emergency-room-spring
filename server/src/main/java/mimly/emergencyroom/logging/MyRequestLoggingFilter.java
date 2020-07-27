package mimly.emergencyroom.logging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyRequestLoggingFilter extends AbstractRequestLoggingFilter {

    @Value("${mimly.emergencyroom.request.logging}")
    private boolean shouldLog;

    public MyRequestLoggingFilter() {
        setIncludeClientInfo(true);
        setIncludeHeaders(true);
        setIncludePayload(true);
        setIncludeQueryString(true);
        setAfterMessagePrefix("  => ");
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return this.shouldLog;
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {

    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        this.logger.info(String.format("%n%s", message));
    }
}

