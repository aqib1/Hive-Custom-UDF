package com.custom.udf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

public class CustomUDF extends GenericUDTF {

	private static final List<String> fieldHeaders = Arrays.asList("BidID", "Timestamp", "LongType", "iPinYouID",
			"UserAgent", "IP", "RegionID", "CityID", "AdExchange", "Domain", "URL", "AnonymousURL", "AdSlotID",
			"AdSlotWidth", "AdSlotHeight", "AdSlotVisibility", "AdSlotFormat", "AdSlotFloorPrice", "CreativeID",
			"BiddingPrice", "PayingPrice", "LandingPageURL", "AdvertiserID", "UserProfileIDs");

	@Override
	public StructObjectInspector initialize(ObjectInspector[] objInsp) throws UDFArgumentException {
		if (objInsp.length != 1) {
			throw new UDFArgumentException("splitBid() takes exactly one argument");
		}
		if (objInsp[0].getCategory() != ObjectInspector.Category.PRIMITIVE) {
			throw new UDFArgumentException("splitBid() takes only string type argument");
		}
		ArrayList<String> fields = new ArrayList<>(fieldHeaders);
		List<ObjectInspector> fieldIO = new ArrayList<>(fields.size());
		Collections.fill(fieldIO, PrimitiveObjectInspectorFactory.javaStringObjectInspector);
		return ObjectInspectorFactory.getStandardStructObjectInspector(fields, fieldIO);
	}

	@Override
	public void process(Object[] args) throws HiveException {
		String input = args[0].toString();
		String[] fields = input.split("\\t");
		for (String field : fields) {
			try {
				forward(field);
			} catch (Exception e) {
				continue;
			}
		}
	}

	@Override
	public void close() throws HiveException {

	}

}
