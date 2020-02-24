package net.danizen.norconex.aws;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.containsString;

import com.norconex.commons.lang.config.IXMLConfigurable;
import com.norconex.commons.lang.config.XMLConfigurationUtil;

import com.norconex.jef4.job.IJobLifeCycleListener;

public class TestS3StatusListener {

    @Test
    public void testInstanceOf() {
        S3StatusListener listener = new S3StatusListener();
        assertThat(listener, instanceOf(IXMLConfigurable.class));
        assertThat(listener, instanceOf(IJobLifeCycleListener.class));
    }

    private S3StatusListener createListener() {
        S3StatusListener listener = new S3StatusListener();
        listener.setBucket("bucket1");
        listener.setPrefix("prefix1");
        listener.setProfile("default1");
        return listener;
    }

    @Test
    public void testToString() {
        S3StatusListener listener = createListener();

        final String value = listener.toString();
        System.out.println(value);

        assertThat(value, containsString("=bucket1"));
        assertThat(value, containsString("=prefix1"));
        assertThat(value, containsString("=default1"));
    }

    @Test
    public void testEquals() {
        S3StatusListener listener1 = createListener();
        S3StatusListener listener2 = createListener();

        assertThat(listener1, equalTo(listener2));
    }

    @Test
    public void testHashCode() {
        S3StatusListener listener1 = createListener();
        S3StatusListener listener2 = createListener();

        assertThat(listener1.hashCode(), equalTo(listener2.hashCode()));     
    }

    @Test
    public void testIncludesBucket() {
        S3StatusListener listener1 = new S3StatusListener();
        S3StatusListener listener2 = new S3StatusListener();

        listener1.setBucket("1");
        listener2.setBucket("2");

        assertFalse(listener1.equals(listener2));
        assertFalse(listener1.hashCode() == listener2.hashCode());
    }

    @Test
    public void testIncludesPrefix() {
        S3StatusListener listener1 = new S3StatusListener();
        S3StatusListener listener2 = new S3StatusListener();

        listener1.setPrefix("1");
        listener2.setPrefix("2");

        assertFalse(listener1.equals(listener2));
        assertFalse(listener1.hashCode() == listener2.hashCode());
    }

    @Test
    public void testIncludesProfile() {
        S3StatusListener listener1 = new S3StatusListener();
        S3StatusListener listener2 = new S3StatusListener();

        listener1.setProfile("1");
        listener2.setProfile("2");

        assertFalse(listener1.equals(listener2));
        assertFalse(listener1.hashCode() == listener2.hashCode());
    }

    @Test
    public void testWriteRead() throws IOException {
        S3StatusListener listener = new S3StatusListener();
        listener.setBucket("bucket1");
        listener.setPrefix("prefix1");
        listener.setProfile("profile1");

        StringWriter writer = new StringWriter();
        listener.saveToXML(writer);
        writer.close();
        System.out.println("Wrote this: " + writer.toString());

        XMLConfigurationUtil.assertWriteRead(listener);
    }

    @Test
    public void testLoadXML() {

    }

    @Test
    public void testSaveXML() {

    }
}
