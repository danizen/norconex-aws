package net.danizen.norconex.aws;

import com.norconex.collector.core.ICollector;
// import com.norconex.collector.core.ICollectorLifeCycleListener;
// import com.norconex.commons.lang.config.IXMLConfigurable;
// import com.norconex.commons.lang.config.XMLConfigurationUtil;
// import com.norconex.commons.lang.xml.EnhancedXMLStreamWriter;

/**
 * S3StatusMirror - Copies collector status to S3
 * 
 * Wire this class as a collector lifecycle listener, and it will
 * copy the status from the work dir to a designated S3 bucket so that
 * a monitor application can be aware of the status and progress
 * of the crawler.
 */
public class S3StatusMirror {
    
    public void onCollectorStart(ICollector collector) {
        // JEF API listener
    }

    public void onCollectorFinish(ICollector collector) {
        // JEF API listener
    }

}