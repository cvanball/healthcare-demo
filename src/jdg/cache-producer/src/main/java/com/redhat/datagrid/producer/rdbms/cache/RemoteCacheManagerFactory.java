package com.redhat.datagrid.producer.rdbms.cache;

import java.io.IOException;

import com.redhat.datagrid.domain.BPGLevel;
import com.redhat.datagrid.domain.BloodPressureLevel;
import com.redhat.datagrid.domain.GlucoseLevel;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller;
import org.infinispan.protostream.SerializationContext;
import org.infinispan.protostream.annotations.ProtoSchemaBuilder;
import org.infinispan.protostream.annotations.ProtoSchemaBuilderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory for {@link RemoteCacheManager RemoteCacheManagers}.
 * 
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 *
 */
public class RemoteCacheManagerFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoteCacheManagerFactory.class);

	private static final String JDG_HOSTNAME;

	private static final int JDG_PORT;

	// Read system properties.
	static {
		JDG_HOSTNAME = System.getProperty("jdg.hostname");
		JDG_PORT = Integer.parseInt(System.getProperty("jdg.port"));
	}

	public RemoteCacheManager getRemoteCacheFactory() {
		
		// HotRod ConfigurationBuilder.
		ConfigurationBuilder cb = new ConfigurationBuilder();
		// Make sure to register the ProtoStreamMarshaller.
		cb.addServer().host(JDG_HOSTNAME).port(JDG_PORT).marshaller(new ProtoStreamMarshaller());

		RemoteCacheManager rcm = new RemoteCacheManager(cb.build());

		SerializationContext serCtx = ProtoStreamMarshaller.getSerializationContext(rcm);

		ProtoSchemaBuilder protoSchemaBuilder = new ProtoSchemaBuilder();
		try {
			String generatedSchema = protoSchemaBuilder
					.fileName("producer_rdbms.proto")
					.packageName("com.redhat.datagrid.domain")
					.addClass(BloodPressureLevel.class)
					.addClass(GlucoseLevel.class)
					.addClass(BPGLevel.class)
					.build(serCtx);
		} catch (ProtoSchemaBuilderException psbe) {
			String message = "Error building ProtoBuf Schema.";
			LOGGER.error(message, psbe);
			throw new RuntimeException(message, psbe);
		} catch (IOException ioe) {
			String message = "I/O error while registering ProtoBufs.";
			LOGGER.error(message, ioe);
			throw new RuntimeException(message, ioe);
		}

		return rcm;
	}

}
