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

import com.norconex.commons.lang.config.IXMLConfigurable;
import com.norconex.commons.lang.config.XMLConfigurationUtil;
import com.norconex.commons.lang.xml.EnhancedXMLStreamWriter;

import com.norconex.jef4.job.AbstractJobStateChangeListener;
import com.norconex.jef4.status.IJobStatus;

/**
 * S3StatusMirror - Copies collector status to S3
 * 
 * Wire this class as a collector lifecycle listener, and it will
 * copy the status from the work dir to a designated S3 bucket so that
 * a monitor application can be aware of the status and progress
 * of the crawler.
 */
public class S3StatusListener extends AbstractJobStateChangeListener implements IXMLConfigurable {

    private static final Logger LOG = LogManager.getLogger(S3StatusListener.class);

    private String bucketName;
    private String profileName;
    private String folderPrefix;

    @Override
    public void loadFromXML(Reader reader) throws IOException {
        // Generate XML Configuration from reader
        XMLConfiguration xml = XMLConfigurationUtil.newXMLConfiguration(reader);

        setBucketName(xml.getString("bucket", getBucketName()));
        setProfileName(xml.getString("profile", getProfileName()));
        setFolderPrefix(xml.getString("prefix", getFolderPrefix()));
    }
    
    @Override
    public void saveToXML(Writer writer) throws IOException {
        try {
            // Generate XML Stream Writer from writer
            final EnhancedXMLStreamWriter xmlwriter = new EnhancedXMLStreamWriter(writer);

            xmlwriter.writeStartElement("listener");
            xmlwriter.writeAttribute("class", getClass().getCanonicalName());

            xmlwriter.writeAttribute("bucket", getBucketName());

            final String profileName = getProfileName();
            if (profileName != null) {
                xmlwriter.writeAttribute("profile", profileName);
            }

            final String prefix = getFolderPrefix();
            if (prefix != null) {
                xmlwriter.writeAttribute("prefix", prefix);
            }

            xmlwriter.writeEndElement();
            xmlwriter.flush();
            xmlwriter.close();
        } catch (XMLStreamException e) {
            throw new IOException("Cannot save as XML", e);
        }
    }

    @Override
    public void jobStateChanged(IJobStatus progress) {
        // TODO Auto-generated method stub
        // Write state change to amazon S3
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

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getFolderPrefix() {
        return folderPrefix;
    }

    public void setFolderPrefix(String folderPrefix) {
        this.folderPrefix = folderPrefix;
    }
}