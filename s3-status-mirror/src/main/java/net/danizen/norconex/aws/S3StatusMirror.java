package net.danizen.norconex.aws;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.norconex.collector.core.ICollector;
// import com.norconex.collector.core.ICollectorLifeCycleListener;
import com.norconex.commons.lang.config.IXMLConfigurable;
import com.norconex.commons.lang.config.XMLConfigurationUtil;
import com.norconex.commons.lang.xml.EnhancedXMLStreamWriter;

/**
 * S3StatusMirror - Copies collector status to S3
 * 
 * Wire this class as a collector lifecycle listener, and it will
 * copy the status from the work dir to a designated S3 bucket so that
 * a monitor application can be aware of the status and progress
 * of the crawler.
 */
public class S3StatusMirror implements IXMLConfigurable {

    private static final Logger LOG = LogManager.getLogger(S3StatusMirror.class);
    @Override
    public void loadFromXML(Reader reader) throws IOException {
        // Generate XML Configuration from reader
        XMLConfiguration xml = XMLConfigurationUtil.newXMLConfiguration(reader);
    }
    
    @Override
    public void saveToXML(Writer writer) throws IOException {
        try {
            // Generate XML Stream Writer from writer
            EnhancedXMLStreamWriter xmlwriter = new EnhancedXMLStreamWriter(writer);

            xmlwriter.writeStartElement("listener");
            xmlwriter.writeAttribute("class", getClass().getCanonicalName());

            xmlwriter.writeEndElement();
            xmlwriter.flush();
            xmlwriter.close();
        } catch (XMLStreamException e) {
            throw new IOException("Cannot save as XML", e);
        }
    }

    public void onCollectorStart(ICollector collector) {
        // JEF API listener
    }

    public void onCollectorFinish(ICollector collector) {
        // JEF API listener
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof S3StatusMirror)) {
            return false;
        }
        final S3StatusMirror othmirror = (S3StatusMirror) other;
        return new EqualsBuilder()
                .isEquals();
    }
}