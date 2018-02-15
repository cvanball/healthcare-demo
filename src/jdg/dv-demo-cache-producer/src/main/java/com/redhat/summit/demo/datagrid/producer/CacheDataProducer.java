package com.redhat.summit.demo.datagrid.producer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller;
import org.infinispan.commons.api.BasicCache;
import org.infinispan.protostream.SerializationContext;
import org.infinispan.protostream.annotations.ProtoSchemaBuilder;
import org.infinispan.protostream.annotations.ProtoSchemaBuilderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.summit.demo.datagrid.domain.BloodPressureLevel;
import com.redhat.summit.demo.datagrid.domain.GlucoseLevel;
import com.redhat.summit.demo.datagrid.producer.cache.RemoteCacheManagerFactory;
import com.redhat.summit.demo.datagrid.producer.db.DbConnectionFactory;
import com.redhat.summit.demo.datagrid.producer.repository.BloodPressureLevelRepository;
import com.redhat.summit.demo.datagrid.producer.repository.GlucoseLevelRepository;

/**
 * Loads {@link BloodPressureLevel} and {@link GlucoseLevel} data from a <code>PostgreSQL</code> database and stores the data in
 * <code>ProtoBuf</code> format in <code>JBoss Data Grid</code>.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
public class CacheDataProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(CacheDataProducer.class);

	private static final String ISPN_CACHE_NAME;

	private static final DbConnectionFactory dbConnectionFactory = new DbConnectionFactory();

	private static RemoteCacheManager rcm = null;

	private static final RemoteCacheManagerFactory rcmFactory = new RemoteCacheManagerFactory();

	private static final BloodPressureLevelRepository bplRepo = new BloodPressureLevelRepository();

	private static final GlucoseLevelRepository glRepo = new GlucoseLevelRepository();

	// Read system properties.
	static {
		ISPN_CACHE_NAME=System.getProperty("dv.demo.cache.name");
	}
	

	public static void main(String[] args) {
		LOGGER.info("Loading data from database and storing in JBoss Data Grid.");

		Map<String, BloodPressureLevel> bpls = bplRepo.getBloodPressureLevel();
		LOGGER.info("Found '" + bpls.size() + "' Blood Pressure Levels.");
		LOGGER.info("Pushing data to Infinspan.");
		putInIspn(ISPN_CACHE_NAME, bpls);

		Map<String, GlucoseLevel> gls = glRepo.getGlucoseLevel();
		LOGGER.info("Found '" + gls.size() + "' Glucose Levels.");
		LOGGER.info("Pushing data to Infinspan.");
		putInIspn(ISPN_CACHE_NAME, gls);

		closeAndCleanUp();

		LOGGER.info("Data stored in JBoss Data Grid.");
	}

	/**
	 * Puts the data in the given {@link Map} in <code>JBoss Data Grid</code>
	 * 
	 * 
	 * @param cacheName
	 * 
	 * @param keyValues
	 */
	private static <T> void putInIspn(String cacheName, Map<String, T> keyValues) {
		RemoteCacheManager remoteCacheManager = getRemoteCacheManager();
		BasicCache<String, T> cache = remoteCacheManager.getCache(cacheName);

		keyValues.forEach((key, value) -> {
			cache.put(key, value);
		});
	}

	/**
	 * Get the {@link RemoteCacheManager}.
	 * 
	 * @return the {@link RemoteCacheManager}
	 */
	private static synchronized RemoteCacheManager getRemoteCacheManager() {
		if (CacheDataProducer.rcm == null) {
			CacheDataProducer.rcm = rcmFactory.getRemoteCacheFactory();
		}
		return CacheDataProducer.rcm;
	}

	/**
	 * Close and cleanup all resources.
	 */
	private static void closeAndCleanUp() {
		if (CacheDataProducer.rcm != null) {
			CacheDataProducer.rcm.stop();
		}
	}

}
