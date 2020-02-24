package net.danizen.norconex.aws;

import static org.junit.Assert.assertThat;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;


import com.norconex.commons.lang.config.IXMLConfigurable;
import com.norconex.commons.lang.config.XMLConfigurationUtil;
import com.norconex.commons.lang.log.CountingConsoleAppender;

import com.norconex.jef4.job.IJobLifeCycleListener;

public class TestS3StatusListener {

    @Test
    public void testInstanceOf() {
        S3StatusListener listener = new S3StatusListener();
        assertThat(listener, instanceOf(IXMLConfigurable.class));
        assertThat(listener, instanceOf(IJobLifeCycleListener.class));
    }
}
