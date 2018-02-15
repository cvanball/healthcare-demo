package com.redhat.summit.demo.datagrid.producer.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.summit.demo.datagrid.domain.BloodPressureLevel;
import com.redhat.summit.demo.datagrid.producer.db.DbConnectionFactory;

/**
 * Repository for {@link BloodPressureLevel BloodPressureLevels.}.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
public class BloodPressureLevelRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BloodPressureLevelRepository.class);
	
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final String BPL_KEY_PREFIX = "bpl-";
	
	private static final DbConnectionFactory dbConnectionFactory = new DbConnectionFactory();
	
	/**
	 * Retrieves the {@link BloodPressureLevel} from the database.
	 * 
	 * @param connection
	 *            the {@link Connection} to use.
	 * @return a {@link Map} of {@link BloodPressureLevel BloodPressureLevels}.
	 */
	public Map<String, BloodPressureLevel> getBloodPressureLevel() {
		Map<String, BloodPressureLevel> bpls = new HashMap<>();

		String selectBpl = "SELECT * FROM bp_level";

		try (Connection connection = dbConnectionFactory.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(selectBpl);
			try (ResultSet rs = statement.executeQuery()) {

				while (rs.next()) {
					BloodPressureLevel bpl = new BloodPressureLevel();

					int id = rs.getInt("id");
					bpl.setId(id);

					bpl.setPatientId(rs.getInt("personId"));
					Date dateRead = rs.getDate("dateREad");
					bpl.setDateRead(df.format(dateRead));
					bpl.setMam(rs.getBoolean("mam"));
					bpl.setSystolic(rs.getInt("sys"));
					bpl.setDiastolic(rs.getInt("dia"));
					bpl.setPulse(rs.getInt("pulse"));
					bpl.setMam(rs.getBoolean("map"));
					bpl.setComments(rs.getString("comments"));

					bpls.put(BPL_KEY_PREFIX + Integer.toString(id), bpl);
				}
			}

		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			String message = "Error while fetching Blood Pressure Level data.";
			LOGGER.error(message, sqle);
			throw new RuntimeException(message, sqle);
		}

		return bpls;
	}
}
